package com.lspeixotodev.family_activity_control_api.__mocks__;

import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CreateCategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryUsageDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.UpdateCategoryDTO;
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

    public CreateCategoryDTO getCreateCategoryDTO() throws ParseException {
        return mockCreateCategoryDTO();
    }

    public UpdateCategoryDTO getUpdateCategoryDTO() throws ParseException {
        return mockUpdateCategoryDTO();
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
        CategoryDTO dto = new CategoryDTO();
        dto.setId(String.valueOf(UUID.randomUUID()));
        dto.setTitle("Academia/Ginástica");
        dto.setDescription("Academia/Ginástica para os filhos");

        return dto;
    }

    private CreateCategoryDTO mockCreateCategoryDTO() {
        CreateCategoryDTO createCategoryDto = new CreateCategoryDTO();
        createCategoryDto.setId(String.valueOf(UUID.randomUUID()));
        createCategoryDto.setTitle("Academia/Ginástica");
        createCategoryDto.setDescription("Academia/Ginástica para os filhos");

        return createCategoryDto;
    }

    private UpdateCategoryDTO mockUpdateCategoryDTO() {
        UpdateCategoryDTO updateCategoryDto = new UpdateCategoryDTO();
        updateCategoryDto.setId(String.valueOf(UUID.randomUUID()));
        updateCategoryDto.setTitle("Academia/Ginástica");
        updateCategoryDto.setDescription("Academia/Ginástica para os filhos");

        return updateCategoryDto;
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
