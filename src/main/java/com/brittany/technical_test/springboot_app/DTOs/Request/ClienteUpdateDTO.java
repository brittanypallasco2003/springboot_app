package com.brittany.technical_test.springboot_app.DTOs.Request;

import com.brittany.technical_test.springboot_app.validators.annotations.IsExistPhoneNumber;
import com.brittany.technical_test.springboot_app.validators.annotations.NotBlankIfPresent;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteUpdateDTO(

        @NotBlankIfPresent @Size(min = 3, max = 50)
        String nombres,

        @NotBlankIfPresent @Size(min = 8) String password,

        @NotBlankIfPresent @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres")
        String direccion,
        
        @NotBlankIfPresent @IsExistPhoneNumber @Pattern(regexp = "^\\+593\\d{9}$", message = "Número de teléfono inválido") String telefono,
        
        @NotBlankIfPresent @Pattern(regexp = "^(M|F|Otro|No especificado)$", message = "Género inválido")
        String genero) {
}
