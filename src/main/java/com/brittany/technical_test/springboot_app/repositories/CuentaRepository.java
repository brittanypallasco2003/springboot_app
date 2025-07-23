package com.brittany.technical_test.springboot_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brittany.technical_test.springboot_app.models.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {

}
