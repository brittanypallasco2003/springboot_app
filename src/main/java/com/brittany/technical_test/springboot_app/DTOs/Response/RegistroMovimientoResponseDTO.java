package com.brittany.technical_test.springboot_app.DTOs.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.brittany.technical_test.springboot_app.models.TipoCuentaEnum;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "fecha", "cliente", "numCuenta", "tipoCuenta", "estado",
        "saldoInicial",
        "movimiento",
        "saldoDisponible" })
public record RegistroMovimientoResponseDTO(
        Long id,
        LocalDateTime fecha, String cliente, String numCuenta, TipoCuentaEnum tipoCuenta,
        Boolean estado,
        BigDecimal saldoInicial,
        BigDecimal movimiento,
        BigDecimal saldoDisponible) {
}
