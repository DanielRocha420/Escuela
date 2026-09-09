package com.daniel.escuela.entities;

import com.daniel.escuela.utils.StringCustomUtils;
import com.daniel.escuela.utils.ValoresNumericosUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AULAS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AULA")
    private Long id;

    @Column(name = "NOMBRE", length = 50, nullable = false, unique = true)
    private String nombre;

    @Column(name = "CAPACIDAD", nullable = false)
    private Integer capacidad;

    @Builder.Default
    @OneToMany(mappedBy = "aula", fetch = FetchType.LAZY)
    private List<Grupo> grupos = new ArrayList<>();


    private void validarDatos(String nombre, Integer capacidad){
        StringCustomUtils.validarTamanio(nombre,1, 30, "El nombre del aula es requerido y debe tener entre 1 a 30 caracterest");
        ValoresNumericosUtils.validarEnteroMayorAZero(capacidad,"La capacidad es requerida y debe ser mayor a cero");
    }

    public void actualizar(String nombre, Integer capacidad) {
        validarDatos(nombre, capacidad);
        this.nombre = nombre.trim();
        this.capacidad = capacidad;
    }

}
