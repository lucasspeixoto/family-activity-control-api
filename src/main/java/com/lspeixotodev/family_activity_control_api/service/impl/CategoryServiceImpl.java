package com.lspeixotodev.family_activity_control_api.service.impl;

import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;

import com.lspeixotodev.family_activity_control_api.dto.category.CategoryUsageDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;
import com.lspeixotodev.family_activity_control_api.entity.category.Category;

import com.lspeixotodev.family_activity_control_api.infra.exceptions.ResourceNotFoundException;
import com.lspeixotodev.family_activity_control_api.mapper.CategoryMapper;
import com.lspeixotodev.family_activity_control_api.repository.CategoryRepository;
import com.lspeixotodev.family_activity_control_api.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        logger.info("Start creating category at: {}", LocalDateTime.now());

        Category bill = this.categoryMapper.dtoToEntity(categoryDTO);

        Category savedCategory = categoryRepository.save(bill);

        return this.categoryMapper.entityToDTO(savedCategory);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAllByOrderByTitleAsc();

        return this.categoryMapper.entitiesToDtos(categories);
    }

    @Override
    public CategoryDTO findCategoryById(String id) {
        logger.info("Start finding category by id at: {}", LocalDateTime.now());

        UUID uuid = UUID.fromString(id);

        Optional<Category> entity = categoryRepository.findById(uuid);

        if (entity.isEmpty()) {
            logger.info("Fail to find a category with id {}", id);
            throw new ResourceNotFoundException("Category", "id", id);
        }

        return this.categoryMapper.entityToDTO(entity.get());
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, String id) {
        logger.info("Start update a category at: {}", LocalDateTime.now());

        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString(id));

        if (optionalCategory.isEmpty()) {
            logger.info("Fail to update a category with id {}", id);
            throw new ResourceNotFoundException("Category", "id", id);
        }

        Category existingCategory = optionalCategory.get();

        Category changedCategory = setCategoryFieldsHandler(categoryDTO, existingCategory);

        Category updatedCategory = categoryRepository.save(changedCategory);

        return this.categoryMapper.entityToDTO(updatedCategory);
    }

    @Override
    public CategoryDTO deleteCategory(String id) {
        logger.info("Start delete a category at: {}", LocalDateTime.now());

        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString(id));

        if (optionalCategory.isEmpty()) {
            logger.info("Fail to delete a category with id {}", id);
            throw new ResourceNotFoundException("Category", "id", id);
        }

        Category existingCategory = optionalCategory.get();

        try {
            categoryRepository.deleteById(existingCategory.getId());
        } catch (DataIntegrityViolationException e) {
            logger.info("This category can not be deleted because is used in one or more bill(s).");
            throw new DataIntegrityViolationException(
                    "This category can not be deleted because is used in one or more bill(s)."
            );
        }

        return this.categoryMapper.entityToDTO(existingCategory);
    }

    @Override
    public List<CategoryUsageDTO> getCategoryUsages() {
        List<Category> categories = categoryRepository.findAllByOrderByTitleAsc();

        return this.categoryMapper.entitiesToCategoryUsages(categories);
    }

    private Category setCategoryFieldsHandler(CategoryDTO categoryDTO, Category existingCategory) {
        existingCategory.setTitle(categoryDTO.getTitle());
        existingCategory.setDescription(categoryDTO.getDescription());
        return existingCategory;
    }

    public Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        return this.categoryMapper.dtoToEntity(categoryDTO);
    }

}
