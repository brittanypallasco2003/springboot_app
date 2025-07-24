package com.brittany.technical_test.springboot_app.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.brittany.technical_test.springboot_app.DTOs.Response.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationErrors(MethodArgumentNotValidException exception) {

        Map<String, String> mapErrors = new LinkedHashMap<>();

        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        fieldErrors.forEach(error -> mapErrors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponseDTO response = mapErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
                "Error de validación", mapErrors);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> manejarRecursoNoEncontrado(ResourceNotFound exception) {
        ErrorResponseDTO response = mapErrorResponseDTO(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
                exception.getMessage(), null);
                
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    private ErrorResponseDTO mapErrorResponseDTO(int status, String error, String message, Map<String, String> errors) {
        ErrorResponseDTO.ErrorResponseDTOBuilder builder = ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .error(error)
                .message(message);

        if (errors != null && !errors.isEmpty()) {
            builder.errors(errors);
        }

        return builder.build();
    }

}
