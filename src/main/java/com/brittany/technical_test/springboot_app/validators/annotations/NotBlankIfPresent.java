package com.brittany.technical_test.springboot_app.validators.annotations;

import com.brittany.technical_test.springboot_app.validators.NotBlankIfPresentValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = NotBlankIfPresentValidator.class)
public @interface NotBlankIfPresent {
    String message() default "El campo no puede estar en blanco si está presente";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
