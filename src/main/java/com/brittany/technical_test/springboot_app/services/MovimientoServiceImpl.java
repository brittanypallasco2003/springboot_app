package com.brittany.technical_test.springboot_app.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brittany.technical_test.springboot_app.DTOs.Projections.RegistroMovimientoProjection;
import com.brittany.technical_test.springboot_app.DTOs.Request.RegistroMovimientoDTO;
import com.brittany.technical_test.springboot_app.DTOs.Response.RegistroMovimientoResponseDTO;
import com.brittany.technical_test.springboot_app.exceptions.ResourceNotFound;
import com.brittany.technical_test.springboot_app.exceptions.SaldoInsuficienteException;
import com.brittany.technical_test.springboot_app.models.Cliente;
import com.brittany.technical_test.springboot_app.models.Cuenta;
import com.brittany.technical_test.springboot_app.models.Movimiento;
import com.brittany.technical_test.springboot_app.repositories.ClienteRepository;
import com.brittany.technical_test.springboot_app.repositories.CuentaRepository;
import com.brittany.technical_test.springboot_app.repositories.MovimientoRepository;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public MovimientoServiceImpl(ClienteRepository clienteRepository, CuentaRepository cuentaRepository,
            MovimientoRepository movimientoRepository) {
        this.clienteRepository = clienteRepository;
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @Transactional
    @Override
    public RegistroMovimientoResponseDTO registrarMovimiento(RegistroMovimientoDTO dto) {
        Cuenta cuentaStored = cuentaRepository.findById(dto.cuentaId())
                .orElseThrow(() -> new ResourceNotFound("Cuenta no encontrada"));

        BigDecimal saldoResultante = cuentaStored.getSaldoDisponible().add(dto.valor());
        if (saldoResultante.compareTo(BigDecimal.ZERO) < 0)
            throw new SaldoInsuficienteException("Saldo no disponible");

        Movimiento newMovimiento = Movimiento.builder()
                .tipoMovimiento(dto.tipoMovimiento())
                .valor(dto.valor())
                .saldo(saldoResultante)
                .build();

        cuentaStored.addMovimiento(newMovimiento);

        cuentaStored.setSaldoDisponible(saldoResultante);

        // Guardar Movimiento
        Movimiento movimientoCreated = movimientoRepository.save(newMovimiento);

        // Actualizar saldo de la cuenta en la bd
        Cuenta cuentaUpdated = cuentaRepository.save(cuentaStored);

        Cliente clienteAsociado = cuentaUpdated.getCliente();

        return mapRegistroMovimientoResponse(movimientoCreated, cuentaUpdated, clienteAsociado);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RegistroMovimientoResponseDTO> listAllMovimientos() {

        return movimientoRepository.findAllMovimientosProyeccion()
                .stream()
                .map(p -> new RegistroMovimientoResponseDTO(p.getFecha(),
                        p.getNombreCliente(),
                        p.getNumCuenta(),
                        p.getTipoCuenta(),
                        p.getEstado(),
                        p.getSaldoInicial(),
                        p.getMovimiento(),
                        p.getSaldo()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public RegistroMovimientoResponseDTO getSpecificMovimiento(Long id) {
        RegistroMovimientoProjection mProjection = movimientoRepository.findProyeccionById(id).orElseThrow(() -> new ResourceNotFound("Movimiento no encontrado"));

        return new RegistroMovimientoResponseDTO(
                mProjection.getFecha(),
                mProjection.getNombreCliente(),
                mProjection.getNumCuenta(),
                mProjection.getTipoCuenta(),
                mProjection.getEstado(),
                mProjection.getSaldoInicial(),
                mProjection.getMovimiento(),
                mProjection.getSaldo());

    }

    private RegistroMovimientoResponseDTO mapRegistroMovimientoResponse(Movimiento movimiento, Cuenta cuenta,
            Cliente cliente) {
        return new RegistroMovimientoResponseDTO(movimiento.getFecha(),
                cliente.getNombre(),
                cuenta.getNumCuenta(),
                cuenta.getTipoCuenta(),
                cuenta.getEstado(),
                cuenta.getSaldoInicial(),
                movimiento.getValor(),
                movimiento.getSaldo());

    }

}
