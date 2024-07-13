package com.lspeixotodev.family_activity_control_api.controller.impl;

import com.lspeixotodev.family_activity_control_api.controller.CategoryController;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CreateCategoryDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.CategoryUsageDTO;
import com.lspeixotodev.family_activity_control_api.dto.category.UpdateCategoryDTO;
import com.lspeixotodev.family_activity_control_api.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryControllerImpl implements CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Override
    public ResponseEntity<CategoryDTO> create(CreateCategoryDTO createCategoryDTO) {
        return new ResponseEntity<>(this.categoryService.create(createCategoryDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
    }

    @Override
    @RequestMapping("/usages")
    public ResponseEntity<List<CategoryUsageDTO>> getCategoryUsages() {
        return new ResponseEntity<>(this.categoryService.getCategoryUsages(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDTO> findCategoryById(String id) {
        return new ResponseEntity<>(this.categoryService.findCategoryById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDTO> updateCategory(UpdateCategoryDTO updateCategoryDTO, String id) {
        return new ResponseEntity<>(this.categoryService.updateCategory(updateCategoryDTO, id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDTO> deleteCategory(String id) {
        return new ResponseEntity<>(this.categoryService.deleteCategory(id), HttpStatus.OK);
    }
}
