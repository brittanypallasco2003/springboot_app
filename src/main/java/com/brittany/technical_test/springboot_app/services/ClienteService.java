package com.brittany.technical_test.springboot_app.services;

import java.util.List;

import com.brittany.technical_test.springboot_app.DTOs.Request.ClienteCreateDTO;
import com.brittany.technical_test.springboot_app.DTOs.Request.ClienteUpdateDTO;
import com.brittany.technical_test.springboot_app.DTOs.Response.ClienteResponseDTO;

public interface ClienteService {

    List<ClienteResponseDTO> listAllClients();
    ClienteResponseDTO getSpecificClient(Long id);
    ClienteResponseDTO createCliente(ClienteCreateDTO dto);
    ClienteResponseDTO updateClient(ClienteUpdateDTO dto,Long id);
    ClienteResponseDTO parcialUpdateCliente(ClienteUpdateDTO dto, Long id);
    void deleteClient(Long id);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByDni(String dni);
}
