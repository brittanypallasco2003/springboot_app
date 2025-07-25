package com.brittany.technical_test.springboot_app.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.brittany.technical_test.springboot_app.validators.MovimientoValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = MovimientoValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMovimiento {
    String message() default "Movimiento inválido: el tipo no coincide con el signo del valor";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
