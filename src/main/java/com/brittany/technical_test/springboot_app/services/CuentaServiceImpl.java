package com.brittany.technical_test.springboot_app.services;

import java.util.List;
import java.util.stream.Collectors;

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
                        throw new IllegalStateException(String.format("El cliente ya tiene una cuenta de tipo: %s",
                                        dto.tipoCuenta().name()));

                Cuenta cuenta = Cuenta.builder()
                                .tipoCuenta(dto.tipoCuenta())
                                .saldoInicial(dto.saldoInicial())
                                .saldoDisponible(dto.saldoInicial())
                                .estado(true)
                                .build();

                clienteDb.addCuenta(cuenta);

                ClienteResponseDTO clienteResponseDTO = mapParcialClienteResponseDTO(clienteDb);
                Cuenta savedCuenta = cuentaRepository.save(cuenta);

                return mapCuentaResponseDTO(savedCuenta, clienteResponseDTO);
        }

        @Transactional(readOnly = true)
        @Override
        public List<CuentaResponseDTO> listAllCuentas() {
                List<Cuenta> cuentas = cuentaRepository.findAll();
                return cuentas.stream()
                                .map(cta -> new CuentaResponseDTO(
                                                cta.getId(), cta.getNumCuenta(), cta.getTipoCuenta(),
                                                cta.getSaldoInicial(), cta.getSaldoDisponible(), cta.getEstado(),
                                                mapParcialClienteResponseDTO(cta.getCliente())))
                                .collect(Collectors.toList());

        }

        @Transactional(readOnly = true)
        @Override
        public CuentaResponseDTO getSpecificCuenta(String id) {
                Cuenta cuentaStored = cuentaRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFound("Cuenta no encontrada"));

                ClienteResponseDTO clienteResponseDTO = mapParcialClienteResponseDTO(cuentaStored.getCliente());

                return mapCuentaResponseDTO(cuentaStored, clienteResponseDTO);
        }

        @Transactional
        @Override
        public CuentaResponseDTO updateCuentaState(String id) {
                Cuenta cuentaStored = cuentaRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFound("Cuenta no encontrada"));

                cuentaStored.setEstado(!cuentaStored.getEstado());

                // Consistencia en memoria
                Cliente cliente = cuentaStored.getCliente();
                cliente.addCuenta(cuentaStored);

                Cuenta cuentaUpdated = cuentaRepository.save(cuentaStored);

                ClienteResponseDTO clienteResponseDTO = mapParcialClienteResponseDTO(cliente);

                return mapCuentaResponseDTO(cuentaUpdated, clienteResponseDTO);
        }

        @Transactional
        @Override
        public void deleteCuenta(String id) {
                Cuenta cuentaDb = cuentaRepository
                                .findById(id)
                                .orElseThrow(() -> new ResourceNotFound("Cuenta no encontrada"));
                Cliente cliente = cuentaDb.getCliente();
                if (cliente != null) {
                        cliente.removeCuenta(cuentaDb);
                }
                cuentaRepository.delete(cuentaDb);
        }

        private CuentaResponseDTO mapCuentaResponseDTO(Cuenta cuenta, ClienteResponseDTO clienteResponseDTO) {
                return new CuentaResponseDTO(cuenta.getId(),
                                cuenta.getNumCuenta(),
                                cuenta.getTipoCuenta(),
                                cuenta.getSaldoInicial(),
                                cuenta.getSaldoDisponible(),
                                cuenta.getEstado(), clienteResponseDTO);

        }

        private ClienteResponseDTO mapParcialClienteResponseDTO(Cliente cliente) {
                return new ClienteResponseDTO(null,
                                cliente.getId(),
                                cliente.getNumCedula(),
                                cliente.getNombre(),
                                null,
                                null,
                                null,
                                null);
        }

}
