package com.daniel.escuela.entities;

import com.daniel.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO", length = 50, nullable = false)
    private String apellidoMaterno;

    @Column(name = "EMAIL", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "MATRICULA", length = 10, nullable = false, unique = true)
    private String matricula;

    @Builder.Default
    @Column(name = "FECHA_INGRESO")
    private LocalDate fechaIngreso = LocalDate.now();

    @Builder.Default
    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones = new ArrayList<>();


    public void validarDatos(String nombre, String apellidoPaterno, String apellidoMaterno) {
        StringCustomUtils.validarTamanio("El nombre es requerido y debe tener entre 1 y 50 caracteres", 1, 50, nombre);
        StringCustomUtils.validarTamanio("El apellido paterno es requerido y debe tener entre 1 y 50 caracteres", 1, 50, apellidoPaterno);
        StringCustomUtils.validarTamanio("El apellido materno es requerido y debe tener entre 1 y 50 caracteres", 1, 50, apellidoMaterno);
    }


    public boolean cambioEnDatos(String nombre, String apellidoPaterno, String apellidoMaterno) {
        return !this.nombre.equals(nombre) ||
                !this.apellidoPaterno.equals(apellidoPaterno) ||
                !this.apellidoMaterno.equals(apellidoMaterno);
    }

    public void asiganarDatosACademicos(String email, String matricula){

        StringCustomUtils.validarTamanio("El email es requerido y debe tener entre 1 y 100 caracteres", 1, 100, email);
        StringCustomUtils.validarTamanio("El matricula es requerido y debe tener exactamente 10 caracteres", 10, 10, matricula);

        this.email = email.toLowerCase().trim();
        this.matricula = matricula.trim();

    }

    public void actualizar(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String matricula){

        validarDatos(nombre, apellidoPaterno, apellidoMaterno);
        asiganarDatosACademicos(email,matricula);

        this.nombre = nombre.trim();
        this.apellidoPaterno = apellidoPaterno.trim();
        this.apellidoMaterno = apellidoMaterno.trim();
    }

    public BigDecimal calcularPromedio() {

        List<BigDecimal> calificaciones = inscripciones.stream()
                .map(Inscripcion::getCalificacion)
                .filter(Objects::nonNull)
                .map(Calificacion::getCalificacion)
                .filter(Objects::nonNull).toList();

        if (calificaciones.isEmpty())
            return BigDecimal.ZERO;

        BigDecimal suma = calificaciones.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return suma.divide(
                BigDecimal.valueOf(calificaciones.size()),
                2, RoundingMode.HALF_UP);
    }
}