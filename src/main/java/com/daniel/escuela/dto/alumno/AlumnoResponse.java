package com.daniel.escuela.dto.alumno;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public record AlumnoResponse(
        @Schema(description = "Identificador del alumno",example = "1")
        Long id,
        @Schema(description = "Nombre del alumno",example = "Juan")
        String nombre,
        @Schema(description = "Apellido paterno del alumno",example = "Perez")
        String apPaterno,
        @Schema(description = "Apellido materno del alumno",example = "Gozales")
        String apMaterno,
        @Schema(description = "Email del alumno",example = "tr.2026.juan.perez.gomez.pegoju2601@escuela.com.mx")
        String email,
        @Schema(description = "Matricula del alumno",example = "1234")
        String matricula,
        @Schema(description = "Fecha de ingreso del alumno", example = "2026-09-08") // Corregida la descripción
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate fecha
) {
}
