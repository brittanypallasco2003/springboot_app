package com.brittany.technical_test.springboot_app.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.brittany.technical_test.springboot_app.validators.NotBlankIfPresentValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Valida que si el campo está presente (no es null), no esté en blanco.
 * Permite valores nulos (por ejemplo, en actualizaciones parciales tipo PATCH),
 * pero no permite cadenas vacías o solo espacios si el campo es enviado.
 */

@Constraint(validatedBy = NotBlankIfPresentValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlankIfPresent {
    String message() default "El campo no puede estar en blanco si está presente";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
