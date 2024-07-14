package com.lspeixotodev.family_activity_control_api.infra.validation.validators;

import com.lspeixotodev.family_activity_control_api.infra.validation.annotations.UniqueBillTitle;
import com.lspeixotodev.family_activity_control_api.repository.BillRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueBillTitleValidator implements ConstraintValidator<UniqueBillTitle, String> {

    @Override
    public void initialize(UniqueBillTitle constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Autowired
    private BillRepository billRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        return !billRepository.existsByTitle(title);
    }
}