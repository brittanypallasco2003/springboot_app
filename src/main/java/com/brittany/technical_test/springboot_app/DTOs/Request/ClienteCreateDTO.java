package com.brittany.technical_test.springboot_app.DTOs.Request;

import com.brittany.technical_test.springboot_app.validators.annotations.IsCedulaEcuatoriana;
import com.brittany.technical_test.springboot_app.validators.annotations.IsExistDni;
import com.brittany.technical_test.springboot_app.validators.annotations.IsExistPhoneNumber;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteCreateDTO(
        @NotBlank @Size(max = 10, message = "Número de cédula debe tener solamente 10 dígitos")
        @IsExistDni
        @IsCedulaEcuatoriana
        String numCedula,

        @NotBlank @Size(min = 3, max = 50) String nombres,
        @NotBlank @Size(min = 8) String password,

        @NotBlank @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres") String direccion,

        @NotBlank @IsExistPhoneNumber @Pattern(regexp = "^\\+593\\d{9}$", message = "Número de teléfono inválido") String telefono,

        @NotBlank @Pattern(regexp = "^(M|F|Otro|No especificado)$", message = "Género inválido") String genero,

        @NotNull
        @Min(value = 18, message = "Debe ser mayor de edad para abrir una cuenta") @Max(value = 100, message = "Edad máxima permitida es 100 años") Integer edad) {

}
