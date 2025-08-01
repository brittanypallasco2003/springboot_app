package com.brittany.technical_test.springboot_app.DTOs.Response;

import java.math.BigDecimal;

import com.brittany.technical_test.springboot_app.models.TipoCuentaEnum;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "numCuenta", "tipoCuenta", "saldoInicial", "saldoDisponible", "estado", "clienteResponseDTO" })
public record CuentaResponseDTO(String id, String numCuenta, TipoCuentaEnum tipoCuenta, BigDecimal saldoInicial,
                BigDecimal saldoDisponible,
                Boolean estado, ClienteResponseDTO cliente) {

}
