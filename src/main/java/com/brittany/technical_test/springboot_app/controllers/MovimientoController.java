package com.brittany.technical_test.springboot_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brittany.technical_test.springboot_app.DTOs.Request.RegistroMovimientoDTO;
import com.brittany.technical_test.springboot_app.DTOs.Response.RegistroMovimientoResponseDTO;
import com.brittany.technical_test.springboot_app.services.MovimientoService;

import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<?> postMovimiento(@Valid @RequestBody RegistroMovimientoDTO dto) {
        RegistroMovimientoResponseDTO response=movimientoService.registrarMovimiento(dto);
         URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("movimientos/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllMovimientos() {
        return ResponseEntity.ok().body(movimientoService.listAllMovimientos());
    }
    
    
    

}
