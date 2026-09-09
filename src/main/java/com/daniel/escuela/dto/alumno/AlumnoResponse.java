package com.daniel.escuela.dto.alumno;

import com.daniel.escuela.dto.datos.DatosCalificacion;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "Informacion de un alumno")
public record AlumnoResponse(
        @Schema(description = "Identificador del alumno",example = "1")
        Long id,

        @Schema(description = "Nombre del alumno",example = "Juan")
        String nombre,

        @Schema(description = "Email del alumno",example = "tr.2026.juan.perez.gomez.pegoju2601@escuela.com.mx")
        String email,

        @Schema(description = "Matricula del alumno",example = "1234")
        String matricula,

        @Schema(description = "Fecha de ingreso del alumno", example = "2026-09-08")
        String fechaIngreso,

        @Schema(description = "Datos de las calificaciones del alumno")
        List<DatosCalificacion> calificaciones,

        @Schema(description = "promedio del alumno", example = "9.9")
        BigDecimal promedio
) {
}
