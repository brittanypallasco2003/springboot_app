package com.brittany.technical_test.springboot_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brittany.technical_test.springboot_app.models.Cuenta;
import com.brittany.technical_test.springboot_app.models.TipoCuentaEnum;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {

    boolean existsByClienteIdAndTipoCuenta(Long clienteId, TipoCuentaEnum tipoCuentaEnum);

}
