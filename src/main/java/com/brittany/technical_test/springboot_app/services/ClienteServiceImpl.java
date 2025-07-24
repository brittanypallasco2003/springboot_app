package com.brittany.technical_test.springboot_app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brittany.technical_test.springboot_app.DTOs.Request.ClienteCreateDTO;
import com.brittany.technical_test.springboot_app.DTOs.Request.ClienteUpdateDTO;
import com.brittany.technical_test.springboot_app.DTOs.Response.ClienteResponseDTO;
import com.brittany.technical_test.springboot_app.exceptions.ResourceNotFound;
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

        String passwordEncriptada = BCrypt.hashpw(dto.password(), BCrypt.gensalt());
        Cliente cliente = Cliente.builder()
                .numCedula(dto.numCedula())
                .nombre(dto.nombres())
                .password(passwordEncriptada)
                .direccion(dto.direccion())
                .telefono(dto.telefono())
                .genero(dto.genero())
                .edad(dto.edad())
                .estado(true)
                .build();

        Cliente clienteCreated = clienteRepository.save(cliente);
        return mapClienteResposeDTO(clienteCreated, "Usuario creado exitosamente");
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClienteResponseDTO> listAllClients() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> new ClienteResponseDTO(null, cliente.getId(), cliente.getNumCedula(),
                        cliente.getNombre(), cliente.getDireccion(), cliente.getTelefono(), cliente.getGenero(),
                        cliente.getEstado()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ClienteResponseDTO getSpecificClient(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Cliente no encontrado"));

        return mapClienteResposeDTO(cliente, null);
    }

    @Transactional
    @Override
    public ClienteResponseDTO updateClient(ClienteUpdateDTO dto, Long id) {
        Cliente clienteStored = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Cliente no encontrado"));

        if (dto.nombres() == null || dto.password() == null || dto.direccion() == null || dto.telefono() == null
                || dto.genero() == null)
            throw new IllegalArgumentException(
                    "Todos los campos son obligatorios para una actualización completa (PUT)");

        Cliente clientUpdated = clienteRepository.save(validateClientData(dto, clienteStored));

        return mapClienteResposeDTO(clientUpdated,
                String.format("Datos del cliente: %s actualizados exitosamente", clienteStored.getNombre()));
    }

    @Transactional
    @Override
    public ClienteResponseDTO parcialUpdateCliente(ClienteUpdateDTO dto, Long id) {
        Cliente clienteStored = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Cliente no encontrado"));

        Cliente clientUpdated = clienteRepository.save(validateClientData(dto, clienteStored));

        return mapClienteResposeDTO(clientUpdated,
                String.format("Datos del cliente: %s actualizados exitosamente", clienteStored.getNombre()));

    }

    private Cliente validateClientData(ClienteUpdateDTO dto, Cliente clienteStored) {
        if (dto.nombres() != null)
            clienteStored.setNombre(dto.nombres());

        if (dto.password() != null)
            clienteStored.setPassword(BCrypt.hashpw(dto.password(), BCrypt.gensalt()));

        if (dto.direccion() != null)
            clienteStored.setDireccion(dto.direccion());

        if (dto.telefono() != null)
            clienteStored.setTelefono(dto.telefono());

        if (dto.genero() != null)
            clienteStored.setGenero(dto.genero());

        return clienteStored;

    }

    @Transactional
    @Override
    public void deleteClient(Long id) {
        Cliente clienteStored=clienteRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Cliente no encontrado"));
        clienteRepository.delete(clienteStored);
    }

    private ClienteResponseDTO mapClienteResposeDTO(Cliente cliente, String message) {
        return new ClienteResponseDTO(message,
                cliente.getId(),
                cliente.getNumCedula(),
                cliente.getNombre(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getGenero(),
                cliente.getEstado());
    }

}
