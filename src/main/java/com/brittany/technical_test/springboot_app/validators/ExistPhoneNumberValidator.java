package com.brittany.technical_test.springboot_app.validators;

import com.brittany.technical_test.springboot_app.services.ClienteService;
import com.brittany.technical_test.springboot_app.validators.annotations.IsExistPhoneNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistPhoneNumberValidator implements ConstraintValidator<IsExistPhoneNumber, String> {

    private final ClienteService clienteService;

    public ExistPhoneNumberValidator(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (clienteService != null) {
            return !clienteService.existsByPhoneNumber(value);
        }
        return true;
    }

}
