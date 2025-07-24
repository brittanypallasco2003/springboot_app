package com.brittany.technical_test.springboot_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brittany.technical_test.springboot_app.DTOs.Request.ClienteCreateDTO;
import com.brittany.technical_test.springboot_app.DTOs.Request.ClienteUpdateDTO;
import com.brittany.technical_test.springboot_app.DTOs.Response.ClienteResponseDTO;
import com.brittany.technical_test.springboot_app.repositories.ClienteRepository;
import com.brittany.technical_test.springboot_app.services.ClienteService;

import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService, ClienteRepository clienteRepository) {
        this.clienteService = clienteService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> postCliente(@RequestBody @Valid ClienteCreateDTO dto) {
        ClienteResponseDTO response = clienteService.createCliente(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllClients() {
        return ResponseEntity.ok().body(clienteService.listAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecificClient(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.getSpecificClient(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> replaceClient(@PathVariable Long id, @Valid @RequestBody ClienteUpdateDTO dto) {

        return ResponseEntity.ok().body(clienteService.updateClient(dto, id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> patchCLient(@PathVariable Long id,
            @Valid @RequestBody ClienteUpdateDTO dto) {
        return ResponseEntity.ok().body(clienteService.parcialUpdateCliente(dto, id));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clienteService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
