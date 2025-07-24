package com.brittany.technical_test.springboot_app.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.brittany.technical_test.springboot_app.validators.ExistDniValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistDniValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsExistDni {

    String message() default "ya se encuentra registrado!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
