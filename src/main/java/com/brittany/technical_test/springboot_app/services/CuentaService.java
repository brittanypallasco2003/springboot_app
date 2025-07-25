package com.brittany.technical_test.springboot_app.services;

import java.util.List;

import com.brittany.technical_test.springboot_app.DTOs.Request.CuentaCreateDTO;
import com.brittany.technical_test.springboot_app.DTOs.Response.CuentaResponseDTO;

public interface CuentaService {
    List<CuentaResponseDTO> listAllCuentas();
    CuentaResponseDTO getSpecificCuenta(String id);
    CuentaResponseDTO crearCuenta(Long clientId, CuentaCreateDTO dto);
    CuentaResponseDTO updateCuentaState(String id);
    void deleteCuenta(String id);
    
}
