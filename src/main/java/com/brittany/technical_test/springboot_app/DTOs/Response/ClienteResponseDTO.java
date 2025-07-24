package com.brittany.technical_test.springboot_app.DTOs.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "message", "id", "numCedula", "nombres", "direccion", "telefono", "genero", "estado" })
public record ClienteResponseDTO(
                @JsonInclude(JsonInclude.Include.NON_NULL) @JsonProperty("message") String message,
                Long id,
                String numCedula,
                String nombres,
                String direccion,
                String telefono,
                String genero,
                Boolean estado
                ) {

}
