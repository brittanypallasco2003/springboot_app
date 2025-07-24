package com.brittany.technical_test.springboot_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brittany.technical_test.springboot_app.DTOs.Request.CuentaCreateDTO;
import com.brittany.technical_test.springboot_app.DTOs.Response.CuentaResponseDTO;
import com.brittany.technical_test.springboot_app.services.CuentaService;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> postCuenta(@RequestParam Long clientId, @Valid @RequestBody CuentaCreateDTO dto) {

        CuentaResponseDTO response = cuentaService.crearCuenta(clientId, dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("cuentas/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllCuentas(@PathVariable UUID id) {
        return ResponseEntity.ok().body(cuentaService.getSpecificCuenta(id));
    }

}
