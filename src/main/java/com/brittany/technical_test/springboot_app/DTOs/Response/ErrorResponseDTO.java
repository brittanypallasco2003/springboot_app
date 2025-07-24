package com.brittany.technical_test.springboot_app.DTOs.Response;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponseDTO {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> errors;

    @Builder
    public ErrorResponseDTO(LocalDateTime timestamp, int status, String error, String message,
            Map<String, String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.errors = errors;
    }



}
