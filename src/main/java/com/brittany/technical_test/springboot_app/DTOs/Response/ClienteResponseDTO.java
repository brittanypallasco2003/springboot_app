package com.brittany.technical_test.springboot_app.DTOs.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "message", "id", "numCedula", "nombres", "direccion", "telefono", "genero", "estado" })
public record ClienteResponseDTO(
                String message,
                Long id,
                String numCedula,
                String nombres,
                String direccion,
                String telefono,
                String genero,
                Boolean estado
                ) {

}
