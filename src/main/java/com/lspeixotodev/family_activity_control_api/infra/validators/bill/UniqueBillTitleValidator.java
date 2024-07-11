package com.lspeixotodev.family_activity_control_api.infra.validators.bill;

import com.lspeixotodev.family_activity_control_api.repository.BillRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueBillTitleValidator implements ConstraintValidator<UniqueBillTitle, String> {

    @Autowired
    private BillRepository billRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        return !billRepository.existsByTitle(title);
    }
}