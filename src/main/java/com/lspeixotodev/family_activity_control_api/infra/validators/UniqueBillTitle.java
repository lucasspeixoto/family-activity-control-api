package com.lspeixotodev.family_activity_control_api.infra.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueBillTitleValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueBillTitle {
    String message() default "The title must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
