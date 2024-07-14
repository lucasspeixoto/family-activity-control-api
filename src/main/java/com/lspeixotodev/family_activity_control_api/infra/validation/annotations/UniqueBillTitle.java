package com.lspeixotodev.family_activity_control_api.infra.validation.annotations;

import com.lspeixotodev.family_activity_control_api.infra.validation.validators.UniqueBillTitleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UniqueBillTitleValidator.class })
public @interface UniqueBillTitle {
    String message() default "The title must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
