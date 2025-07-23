package com.brittany.technical_test.springboot_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brittany.technical_test.springboot_app.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByTelefono(String telefono);
    boolean existsByNumCedula(String dni);

}
