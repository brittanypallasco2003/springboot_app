package com.brittany.technical_test.springboot_app.DTOs.Request;


import java.math.BigDecimal;

import com.brittany.technical_test.springboot_app.models.TipoCuentaEnum;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public record CuentaCreateDTO(

    @NotNull(message = "El tipo de cuenta es obligatorio") 
    TipoCuentaEnum tipoCuenta, 
    @NotNull(message = "El saldo inicial es obligatorio") 
    @DecimalMin(value = "0.00", message = "El saldo inicial no puede ser negativo")
    @Digits(integer = 10, fraction = 2, message = "El saldo inicial solo puede tener hasta dos decimales")
    BigDecimal saldoInicial) {
    
}
