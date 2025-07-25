package com.brittany.technical_test.springboot_app.validators;

import java.math.BigDecimal;

import com.brittany.technical_test.springboot_app.DTOs.Request.RegistroMovimientoDTO;
import com.brittany.technical_test.springboot_app.validators.annotations.ValidMovimiento;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MovimientoValidator implements ConstraintValidator<ValidMovimiento, RegistroMovimientoDTO> {

    @Override
    public boolean isValid(RegistroMovimientoDTO dto, ConstraintValidatorContext context) {
        String tipo = dto.tipoMovimiento().trim().toUpperCase();
        BigDecimal valor = dto.valor();

        boolean esDeposito = tipo.startsWith("DEPOSITO") || tipo.startsWith("DEPÓSITO");
        boolean esRetiro = tipo.startsWith("RETIRO");

        if (!esDeposito && !esRetiro)
            return false;

        if (esDeposito && valor.compareTo(BigDecimal.ZERO) <= 0)
            return false;
        if (esRetiro && valor.compareTo(BigDecimal.ZERO) >= 0)
            return false;

        return true;
    }

}
