package com.daniel.escuela.entities;

import com.daniel.escuela.utils.StringCustomUtils;
import com.daniel.escuela.utils.ValoresNumericosUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CURSOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CURSO")
    private Long id;

    @Column(name = "NOMBRE", length = 50, nullable = false, unique = true)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 200)
    private  String descripcion;

    @Column(name = "CREDITOS", nullable = false)
    private Integer creditos;

    private void validarDatos(String nombre, String descripcion, Integer creditos){
        StringCustomUtils.validarTamanio(nombre,1,100,"El nombre del curso es requerido y debe tener entre 1 y 100");
        StringCustomUtils.validarTamanio(descripcion,1,200,"La descripcion del curso es requerido y debe tener entre 1 a 200 caracteres");
        ValoresNumericosUtils.validarEnteroMayorAZero(creditos, "Los créditos son requeridos y deben ser mayores a cero");
    }

    public void actualizar(String nombre, String descripcion, Integer creditos) {
        validarDatos(nombre, descripcion, creditos);

        this.nombre = nombre.trim();
        this.descripcion = (descripcion != null) ? descripcion.trim() : null;
        this.creditos = creditos;
    }

}


