package com.daniel.escuela.entities;

import com.daniel.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ALUMNOS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALUMNO")
    private Long id;

    @Column(name = "NOMBRE", length = 50, nullable = false)
    private String nombre;

    @Column(name = "APELLIDO_PATERNO", length = 50, nullable = false)
    private String apPaterno;

    @Column(name = "APELLIDO_MATERNO", length = 50, nullable = false)
    private String apMaterno;

    @Column(name = "EMAIL", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "MATRICULA", length = 10, nullable = false, unique = true)
    private String matricula;

    @Builder.Default
    @Column(name = "FECHA_INGRESO")
    private LocalDate fechaIngreso = LocalDate.now();

    @Builder.Default
    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<Inscripcion> Inscripciones = new ArrayList<>();


    public void validarDatos(String nombre, String apPaterno, String apMaterno, String email, String matricula, LocalDate fecha) {
        StringCustomUtils.validarTamanio("El nombre es requerido y debe tener entre 5 y 50 caracteres", 5, 50, nombre);
        StringCustomUtils.validarTamanio("El apellido paterno es requerido y debe tener entre 5 y 50 caracteres", 5, 50, apPaterno);
        StringCustomUtils.validarTamanio("El apellido materno es requerido y debe tener entre 5 y 50 caracteres", 5, 50, apMaterno);
        StringCustomUtils.validarTamanio("El email es requerido y debe tener entre 5 y 100 caracteres", 5, 100, email);
        StringCustomUtils.validarTamanio("La matrícula es requerida y debe tener 10 caracteres", 10, 10, matricula);

        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de ingreso es requerida");
        }
    }


    public void actualizar(String nombre, String apPaterno, String apMaterno, String email, String matricula, LocalDate fechaIngreso) {
        validarDatos(nombre, apPaterno, apMaterno, email, matricula, fechaIngreso);

        this.nombre = nombre.trim();
        this.apPaterno = apPaterno.trim();
        this.apMaterno = apMaterno.trim();
        this.email = email.trim();
        this.matricula = matricula.trim();
        this.fechaIngreso = fechaIngreso;
    }
}