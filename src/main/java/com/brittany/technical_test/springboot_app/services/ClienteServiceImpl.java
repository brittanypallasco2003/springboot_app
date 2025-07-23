package com.brittany.technical_test.springboot_app.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brittany.technical_test.springboot_app.DTOs.Request.ClienteCreateDTO;
import com.brittany.technical_test.springboot_app.DTOs.Response.ClienteResponseDTO;
import com.brittany.technical_test.springboot_app.models.Cliente;
import com.brittany.technical_test.springboot_app.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return clienteRepository.existsByTelefono(phoneNumber);

    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByDni(String dni) {
        return clienteRepository.existsByNumCedula(dni);
    }

    @Transactional
    @Override
    public ClienteResponseDTO createCliente(ClienteCreateDTO dto) {

        String passwordEncriptada=BCrypt.hashpw(dto.password(), BCrypt.gensalt());
        Cliente cliente= Cliente.builder()
        .numCedula(dto.numCedula())
        .nombre(dto.nombres())
        .password(passwordEncriptada)
        .direccion(dto.direccion())
        .telefono(dto.telefono())
        .genero(dto.genero())
        .edad(dto.edad())
        .estado(true)
        .build(); 

        Cliente clienteCreated=clienteRepository.save(cliente);
        return mapClienteResposeDTO(clienteCreated, "Usuario creado exitosamente");
    }


    private ClienteResponseDTO mapClienteResposeDTO(Cliente cliente, String message){
        return new ClienteResponseDTO(message, 
            cliente.getClientId(), 
            cliente.getNumCedula(), 
            cliente.getNombre(), 
            cliente.getDireccion(), 
            cliente.getTelefono(), 
            cliente.getGenero());
    }

}
