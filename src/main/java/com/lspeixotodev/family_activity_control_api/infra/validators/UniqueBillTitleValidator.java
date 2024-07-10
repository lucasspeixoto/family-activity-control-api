package com.lspeixotodev.family_activity_control_api.infra.validators;

import com.lspeixotodev.family_activity_control_api.repository.BillRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueBillTitleValidator implements ConstraintValidator<UniqueBillTitle, String> {

    @Autowired
    private BillRepository userRepository;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        return !userRepository.existsByTitle(title);
    }
}