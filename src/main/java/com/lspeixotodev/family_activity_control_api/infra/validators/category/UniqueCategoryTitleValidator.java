package com.lspeixotodev.family_activity_control_api.infra.validators.category;

import com.lspeixotodev.family_activity_control_api.repository.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCategoryTitleValidator implements ConstraintValidator<UniqueCategoryTitle, String> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        return !categoryRepository.existsByTitle(title);
    }
}