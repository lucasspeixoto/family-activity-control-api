package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryUsageDTO;
import com.lspeixotodev.family_activity_control_api.entity.category.Category;


import java.util.List;

public interface CategoryService {

    CategoryDTO create(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();

    List<CategoryUsageDTO> getCategoryUsages();

    CategoryDTO findCategoryById(String id);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, String id);

    CategoryDTO deleteCategory(String id);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
