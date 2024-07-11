package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryUsageDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO create(CategoryDTO bill);

    List<CategoryDTO> getAllCategories();

    List<CategoryUsageDTO> getCategoryUsages();

    CategoryDTO findCategoryById(String id);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, String id);

    CategoryDTO deleteCategory(String id);
}
