package com.brittany.technical_test.springboot_app.validators.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.brittany.technical_test.springboot_app.validators.ExistPhoneNumberValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistPhoneNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsExistPhoneNumber {

    String message() default "ya se encuentra registrado!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
