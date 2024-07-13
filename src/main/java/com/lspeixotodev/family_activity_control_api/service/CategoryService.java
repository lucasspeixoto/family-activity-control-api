package com.lspeixotodev.family_activity_control_api.service;

import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CreateCategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryUsageDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.UpdateCategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO create(CreateCategoryDTO bill);

    List<CategoryDTO> getAllCategories();

    List<CategoryUsageDTO> getCategoryUsages();

    CategoryDTO findCategoryById(String id);

    CategoryDTO updateCategory(UpdateCategoryDTO updateCategoryDTO, String id);

    CategoryDTO deleteCategory(String id);
}
