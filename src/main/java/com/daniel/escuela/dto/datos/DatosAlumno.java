package com.daniel.escuela.dto.datos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record DatosAlumno(
        @Schema(description = "Nombre completo del alumno", example = "Juan Pérez López")
        String nombre,

        @Schema(description = "Matrícula del alumno", example = "A2025001")
        String matricula,

        @Schema(description = "Correo electrónico del alumno", example = "juan.perez@alumnos.com")
        String email,

        @Schema(description = "Fecha de ingreso", example = "10/01/2025")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate fechaIngreso
) {
}