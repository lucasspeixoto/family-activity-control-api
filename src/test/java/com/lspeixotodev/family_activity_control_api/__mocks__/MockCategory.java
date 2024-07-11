package com.lspeixotodev.family_activity_control_api.__mocks__;

import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryUsageDTO;
import com.lspeixotodev.family_activity_control_api.entity.category.Category;

import java.text.ParseException;
import java.util.UUID;

public class MockCategory {

    public Category getCategory() throws ParseException {
        return mockCategory();
    }

    public Category getSecondCategory() throws ParseException {
        return mockSecondCategory();
    }

    public CategoryDTO getCategoryDTO() throws ParseException {
        return mockCategoryDTO();
    }

    public CategoryUsageDTO getCategoryUsageDTO() throws ParseException {
        return mockCategoryUsageDTO();
    }


    private Category mockCategory() throws ParseException {
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setTitle("Academia/Ginástica");
        category.setDescription("Academia/Ginástica para os filhos");

        return category;
    }


    private CategoryDTO mockCategoryDTO() {
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setId(String.valueOf(UUID.randomUUID()));
        categoryDto.setTitle("Academia/Ginástica");
        categoryDto.setDescription("Academia/Ginástica para os filhos");

        return categoryDto;
    }

    private CategoryUsageDTO mockCategoryUsageDTO() {
        CategoryUsageDTO categoryDto = new CategoryUsageDTO();
        categoryDto.setValue("Academia/Ginástica");
        categoryDto.setViewValue("Academia/Ginástica para os filhos");

        return categoryDto;
    }

    private Category mockSecondCategory() throws ParseException {
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setTitle("Assinaturas");
        category.setDescription("Assinaturas (Revistas, Softwares)");
        
        return category;
    }
}
