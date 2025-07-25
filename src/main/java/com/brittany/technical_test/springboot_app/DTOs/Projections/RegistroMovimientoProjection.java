package com.brittany.technical_test.springboot_app.DTOs.Projections;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.brittany.technical_test.springboot_app.models.TipoCuentaEnum;

public interface RegistroMovimientoProjection {

    Long getId();

    LocalDateTime getFecha();

    String getNombreCliente();

    String getNumCuenta();

    TipoCuentaEnum getTipoCuenta();

    Boolean getEstado();

    BigDecimal getSaldoInicial();

    BigDecimal getMovimiento();

    BigDecimal getSaldo();

}
