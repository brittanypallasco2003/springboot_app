package com.brittany.technical_test.springboot_app.DTOs.Response;

import java.math.BigDecimal;
import java.util.UUID;

import com.brittany.technical_test.springboot_app.models.TipoCuentaEnum;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "numCuenta", "tipoCuenta", "saldoInicial", "estado", "clienteResponseDTO" })
public record CuentaResponseDTO(UUID id, String numCuenta, TipoCuentaEnum tipoCuenta, BigDecimal saldoInicial,
        Boolean estado, ClienteResponseDTO cliente) {

}
