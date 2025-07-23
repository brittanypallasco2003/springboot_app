package com.brittany.technical_test.springboot_app.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    private String password;
    private Boolean estado;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cuenta> cuentas = new HashSet<>();

    public Cliente(String nombre, String genero, Integer edad, Long numCedula, String direccion, String telefono) {
        super(nombre, genero, edad, numCedula, direccion, telefono);
    }

    @Builder
    public Cliente(String nombre, String genero, Integer edad, Long numCedula, String direccion, String telefono,
            String password, Boolean estado) {
        super(nombre, genero, edad, numCedula, direccion, telefono);
        this.password = password;
        this.estado = estado;
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente other = (Cliente) o;
        return this.getNumCedula() != null && this.getNumCedula().equals(other.getNumCedula());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumCedula());
    }

}
