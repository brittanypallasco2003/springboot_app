package com.brittany.technical_test.springboot_app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.brittany.technical_test.springboot_app.DTOs.Projections.RegistroMovimientoProjection;
import com.brittany.technical_test.springboot_app.models.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
        @Query("""
                SELECT m.id AS id,
                        m.fecha AS fecha,
                        cl.nombre AS nombreCliente,
                        c.numCuenta AS numCuenta,
                        c.tipoCuenta AS tipoCuenta,
                        c.estado AS estado,
                        c.saldoInicial AS saldoInicial,
                        m.valor AS movimiento,
                        m.saldo AS saldo
                FROM Movimiento m
                JOIN m.cuenta c
                JOIN c.cliente cl
                """)
        List<RegistroMovimientoProjection> findAllMovimientosProyeccion();

        @Query("""
                SELECT m.id AS id,
                        m.fecha AS fecha,
                        cl.nombre AS nombreCliente,
                        c.numCuenta AS numCuenta,
                        c.tipoCuenta AS tipoCuenta,
                        c.saldoInicial AS saldoInicial,
                        c.estado AS estado,
                        m.saldo AS saldo
                FROM Movimiento m
                JOIN m.cuenta c
                JOIN c.cliente cl
                WHERE m.id = :id
                        """)
        Optional<RegistroMovimientoProjection> findProyeccionById(@Param("id") Long id);

}
