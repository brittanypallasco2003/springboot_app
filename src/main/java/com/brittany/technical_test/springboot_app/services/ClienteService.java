package com.brittany.technical_test.springboot_app.services;

import java.util.List;

import com.brittany.technical_test.springboot_app.DTOs.Request.ClienteCreateDTO;
import com.brittany.technical_test.springboot_app.DTOs.Response.ClienteResponseDTO;

public interface ClienteService {
    
    ClienteResponseDTO createCliente(ClienteCreateDTO dto);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByDni(String dni);
}
