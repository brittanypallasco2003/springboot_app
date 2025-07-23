package com.brittany.technical_test.springboot_app.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.brittany.technical_test.springboot_app.validators.CedulaEcuatorianaValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CedulaEcuatorianaValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsCedulaEcuatoriana {

    String message() default "Cédula ecuatoriana inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
