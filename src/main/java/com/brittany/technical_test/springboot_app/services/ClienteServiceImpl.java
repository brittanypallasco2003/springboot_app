package com.brittany.technical_test.springboot_app.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
