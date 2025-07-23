package com.brittany.technical_test.springboot_app.models;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cuentas")
@NoArgsConstructor
@Getter
@Setter
public class Cuenta {

    @Id
    @Column(name = "num_cuenta", length = 10, unique = true)
    private String numCuenta;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo_cuenta")
    private TipoCuentaEnum tipoCuenta;

    @Column(name = "saldo_inicial", nullable = false, precision = 10, scale = 2)
    private BigDecimal saldoInicial;

    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Movimiento> movimientos = new HashSet<>();

    @Builder
    public Cuenta(TipoCuentaEnum tipoCuenta, BigDecimal saldoInicial, Boolean estado, Cliente cliente,
            Set<Movimiento> movimientos) {
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.cliente = cliente;
        this.movimientos = movimientos;
    }

    @PrePersist
    public void generarNumCuenta() {
        if (this.numCuenta == null) {
            this.numCuenta = String.format("%010d", (int) (Math.random() * 1_000_000_000));
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numCuenta == null) ? 0 : numCuenta.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cuenta other = (Cuenta) obj;
        if (numCuenta == null) {
            if (other.numCuenta != null)
                return false;
        } else if (!numCuenta.equals(other.numCuenta))
            return false;
        return true;
    }
    

}
