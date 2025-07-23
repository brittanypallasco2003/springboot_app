package com.brittany.technical_test.springboot_app.DTOs.Response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "message", "id", "numCedula", "nombres", "direccion", "telefono", "genero" })
public record ClienteResponseDTO(String message, String id, String numCedula, String nombres, String direccion,
        String telefono, String genero) {

}
