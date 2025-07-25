package com.brittany.technical_test.springboot_app.DTOs.Request;

import java.math.BigDecimal;

import com.brittany.technical_test.springboot_app.validators.annotations.ValidMovimiento;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@ValidMovimiento
public record RegistroMovimientoDTO(
        @NotBlank String cuentaId,
        @NotBlank String tipoMovimiento,
        @NotNull(message = "El valor ingresado es obligatorio") 
        @Digits(integer = 10, fraction = 2, message = "El valor ingresado solo puede tener hasta dos decimales") 
        BigDecimal valor) {
}
