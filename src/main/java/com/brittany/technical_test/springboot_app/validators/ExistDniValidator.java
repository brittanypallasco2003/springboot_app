package com.brittany.technical_test.springboot_app.validators;

import com.brittany.technical_test.springboot_app.services.ClienteService;
import com.brittany.technical_test.springboot_app.validators.annotations.IsExistDni;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistDniValidator implements ConstraintValidator<IsExistDni, String>{
    private final ClienteService clienteService;

    public ExistDniValidator(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public boolean isValid(String dni, ConstraintValidatorContext context) {
          if (clienteService != null) {
            return !clienteService.existsByDni(dni);
        }
        return true;
    }

}
