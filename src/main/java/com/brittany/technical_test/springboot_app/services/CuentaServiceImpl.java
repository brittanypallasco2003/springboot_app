package com.brittany.technical_test.springboot_app.services;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brittany.technical_test.springboot_app.DTOs.Request.CuentaCreateDTO;
import com.brittany.technical_test.springboot_app.DTOs.Response.ClienteResponseDTO;
import com.brittany.technical_test.springboot_app.DTOs.Response.CuentaResponseDTO;
import com.brittany.technical_test.springboot_app.exceptions.ResourceNotFound;
import com.brittany.technical_test.springboot_app.models.Cliente;
import com.brittany.technical_test.springboot_app.models.Cuenta;
import com.brittany.technical_test.springboot_app.repositories.ClienteRepository;
import com.brittany.technical_test.springboot_app.repositories.CuentaRepository;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    @Override
    public CuentaResponseDTO crearCuenta(Long clientId, CuentaCreateDTO dto) {
        Cliente clienteDb = clienteRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFound("Cliente no encontrado"));

        if (cuentaRepository.existsByClienteIdAndTipoCuenta(clientId, dto.tipoCuenta()))
            throw new IllegalStateException(
                    String.format("El cliente ya tiene una cuenta de tipo: %s", dto.tipoCuenta().name()));

        Cuenta cuenta = Cuenta.builder()
                .tipoCuenta(dto.tipoCuenta())
                .saldoInicial(dto.saldoInicial())
                .estado(true)
                .build();

        clienteDb.addCuenta(cuenta);

        Cliente clienteUpdated = clienteRepository.save(clienteDb);

        Cuenta savedCuenta = clienteUpdated.getCuentas()
                .stream()
                .filter(cta -> cta.getTipoCuenta() == dto.tipoCuenta())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No se pudo recuperar la cuenta creada"));
        ClienteResponseDTO clienteResponseDTO = mapParcialClienteResponseDTO(clienteUpdated);

        return mapCuentaResponseDTO(savedCuenta, clienteResponseDTO);
    }

    
    @Override
    public CuentaResponseDTO getSpecificCuenta(UUID id) {
        Cuenta cuentaStored=cuentaRepository.findById(id).orElseThrow(()->new ResourceNotFound("Cuenta no encontrada"));

        ClienteResponseDTO clienteResponseDTO=mapParcialClienteResponseDTO(cuentaStored.getCliente());

        return mapCuentaResponseDTO(cuentaStored, clienteResponseDTO);
    }

    private CuentaResponseDTO mapCuentaResponseDTO(Cuenta cuenta, ClienteResponseDTO clienteResponseDTO) {
        return new CuentaResponseDTO(cuenta.getId(), cuenta.getNumCuenta(), cuenta.getTipoCuenta(),
                cuenta.getSaldoInicial(),
                cuenta.getEstado(), clienteResponseDTO);

    }

    private ClienteResponseDTO mapParcialClienteResponseDTO(Cliente cliente) {
        return new ClienteResponseDTO(null, cliente.getId(), cliente.getNumCedula(), cliente.getNombre(), null, null,
                null, null);
    }


}
