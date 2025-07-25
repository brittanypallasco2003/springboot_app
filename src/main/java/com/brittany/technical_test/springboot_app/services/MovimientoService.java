package com.brittany.technical_test.springboot_app.services;

import java.util.List;

import com.brittany.technical_test.springboot_app.DTOs.Request.RegistroMovimientoDTO;
import com.brittany.technical_test.springboot_app.DTOs.Response.RegistroMovimientoResponseDTO;

public interface MovimientoService {

    List<RegistroMovimientoResponseDTO>listAllMovimientos();
    RegistroMovimientoResponseDTO getSpecificMovimiento(Long id);
    RegistroMovimientoResponseDTO registrarMovimiento(RegistroMovimientoDTO dto);


}
