package com.brittany.technical_test.springboot_app.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Persona {
    private String nombre;
    private String genero;
    private Integer edad;
    @Column(unique = true, nullable = false)
    private String numCedula;
    private String direccion;
    @Column(unique = true, nullable = false)
    private String telefono;

}
