package com.brittany.technical_test.springboot_app.services;

import com.brittany.technical_test.springboot_app.DTOs.Request.CuentaCreateDTO;
import com.brittany.technical_test.springboot_app.DTOs.Response.CuentaResponseDTO;

public interface CuentaService {

    CuentaResponseDTO crearCuenta(Long clientId, CuentaCreateDTO dto);

    
}
