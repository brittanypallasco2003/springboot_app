package com.brittany.technical_test.springboot_app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brittany.technical_test.springboot_app.models.Cuenta;
import com.brittany.technical_test.springboot_app.models.TipoCuentaEnum;

public interface CuentaRepository extends JpaRepository<Cuenta, UUID> {

    boolean existsByClienteIdAndTipoCuenta(Long clienteId, TipoCuentaEnum tipoCuentaEnum);

}
