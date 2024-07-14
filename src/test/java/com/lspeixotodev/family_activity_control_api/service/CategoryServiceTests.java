package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.__mocks__.MockCategory;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryUsageDTO;
import com.lspeixotodev.family_activity_control_api.entity.category.Category;
import com.lspeixotodev.family_activity_control_api.infra.exceptions.ResourceNotFoundException;

import com.lspeixotodev.family_activity_control_api.mapper.CategoryMapper;
import com.lspeixotodev.family_activity_control_api.repository.CategoryRepository;
import com.lspeixotodev.family_activity_control_api.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Category Service (Unit Tests)")
public class CategoryServiceTests {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Spy
    private CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    private Category category;

    private CategoryDTO categoryDTO;

    private CategoryUsageDTO categoryUsageDTO;

    @InjectMocks
    public MockCategory mockCategory;


    @BeforeEach
    public void config() throws ParseException {
        this.category = mockCategory.getCategory();
        this.categoryDTO = mockCategory.getCategoryDTO();
        this.categoryUsageDTO = mockCategory.getCategoryUsageDTO();
    }

    @Test
    @Order(1)
    @DisplayName("Category Service: Create Category then return created CategoryDTO")
    public void categoryService_WhenCreateACategory_ThenReturnCreateCategoryDto() {
        when(categoryRepository.save(any(Category.class))).thenReturn(this.category);

        CategoryDTO mappedCategoryDTOFromCreateCategory = this.categoryMapper.entityToDTO(this.category);

        CategoryDTO categoryDTO = this.categoryService.create(this.categoryDTO);

        assertThat(categoryDTO).isNotNull();
        assertThat(categoryDTO.getTitle()).isEqualTo(mappedCategoryDTOFromCreateCategory.getTitle());
        assertThat(categoryDTO.getDescription()).isEqualTo(mappedCategoryDTOFromCreateCategory.getDescription());

    }

    @Test
    @Order(2)
    @DisplayName("Category Service: When Get All Category then return a List of CategoryDTO")
    public void categoryService_WhenGetAllCategories_ThenReturnListOfCategoryDto() {
        List<Category> categories = Collections.singletonList(this.category);
        when(categoryRepository.findAllByOrderByTitleAsc()).thenReturn(categories);

        List<CategoryDTO> mappedCategoriesDTO = this.categoryMapper.entitiesToDtos(categories);

        List<CategoryDTO> categoriesDDTO = this.categoryService.getAllCategories();

        assertThat(categoriesDDTO).isNotNull();
        assertThat(categoriesDDTO.size()).isEqualTo(mappedCategoriesDTO.size());
    }

    @Test
    @Order(3)
    @DisplayName("Category Service: When Find Category by id then return CategoryDTO")
    public void categoryService_WhenFindCategoryById_ThenReturnCategoryDto() {

        when(categoryRepository.findById(any())).thenReturn((Optional.ofNullable(this.category)));

        CategoryDTO categoryDTO = this.categoryService.findCategoryById(this.category.getId().toString());

        CategoryDTO mappedCategoryDTO = this.categoryMapper.entityToDTO(this.category);

        assertThat(categoryDTO).isNotNull();
        assertThat(categoryDTO).isEqualTo(mappedCategoryDTO);
    }

    @Test
    @Order(4)
    @DisplayName("Category Service: When Find Category by id then throws ResourceNotFoundException")
    public void categoryService_WhenFindCategoryById_ThenThrowsResourceNotFoundException() {
        when(categoryRepository.findById(any())).thenReturn((Optional.empty()));

        assertThrows(ResourceNotFoundException.class, () ->
                this.categoryService.findCategoryById(this.category.getId().toString())
        );
    }

    @Test
    @Order(5)
    @DisplayName("Category Service: When Update Category then return CategoryDTO")
    public void categoryService_WhenUpdateCategory_ThenReturnsCategoryDTO() {
        String newTitle = "New Title";
        this.category.setTitle(newTitle);

        CategoryDTO categoryDTO = this.categoryMapper.entityToDTO(this.category);

        when(categoryRepository.findById(any())).thenReturn((Optional.of(this.category)));
        when(categoryRepository.save(any(Category.class))).thenReturn(this.category);

        CategoryDTO updatedCategory = this.categoryService.updateCategory(categoryDTO, this.category.getId().toString());

        CategoryDTO mappedCategoryDTO = this.categoryMapper.entityToDTO(this.category);

        assertThat(updatedCategory).isNotNull();
        assertThat(updatedCategory.getTitle()).isEqualTo(mappedCategoryDTO.getTitle());
        assertThat(updatedCategory.getDescription()).isEqualTo(mappedCategoryDTO.getDescription());
    }

    @Test
    @Order(6)
    @DisplayName("Category Service: When Update Category then throws ResourceNotFoundException")
    public void categoryService_WhenUpdateCategory_ThenThrowsResourceNotFoundException() {
        when(categoryRepository.findById(any())).thenReturn((Optional.empty()));

        assertThrows(ResourceNotFoundException.class, () -> this.categoryService.updateCategory(this.categoryDTO, this.category.getId().toString()));
    }

    @Test
    @Order(7)
    @DisplayName("Category Service: When Delete Category then return CategoryDTO")
    public void categoryService_WhenDeleteCategory_ThenReturnsCategoryDTO() {

        when(categoryRepository.findById(any())).thenReturn((Optional.of(this.category)));

        CategoryDTO deletedCategory = this.categoryService.deleteCategory(this.category.getId().toString());

        assertThat(deletedCategory).isNotNull();
        assertThat(deletedCategory.getTitle()).isEqualTo(this.categoryDTO.getTitle());
        assertThat(deletedCategory.getDescription()).isEqualTo(this.categoryDTO.getDescription());
    }

    @Test
    @Order(8)
    @DisplayName("Category Service: When Delete Category then throws ResourceNotFoundException")
    public void categoryService_WhenDeleteCategory_ThenThrowsResourceNotFoundException() {
        when(categoryRepository.findById(any())).thenReturn((Optional.empty()));

        assertThrows(ResourceNotFoundException.class, () ->
                this.categoryService.deleteCategory(this.category.getId().toString())
        );
    }

    @Test
    @Order(9)
    @DisplayName("Category Service: When Get All CategoryUsages then return a List of CategoryUsageDTO")
    public void categoryService_WhenGetAllCategoryUsages_ThenReturnListOfCategoryUsageDTO() {
        List<Category> categoryUsages = Collections.singletonList(this.category);
        when(categoryRepository.findAllByOrderByTitleAsc()).thenReturn(categoryUsages);

        List<CategoryUsageDTO> mappedCategoryUsagesDTO = this.categoryMapper.entitiesToCategoryUsages(categoryUsages);

        List<CategoryUsageDTO> categoryUsagesDDTO = this.categoryService.getCategoryUsages();

        assertThat(categoryUsagesDDTO).isNotNull();
        assertThat(categoryUsagesDDTO.size()).isEqualTo(mappedCategoryUsagesDTO.size());

    }


}