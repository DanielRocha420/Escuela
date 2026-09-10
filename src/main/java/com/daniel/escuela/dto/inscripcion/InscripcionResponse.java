package com.daniel.escuela.dto.inscripcion;

import com.daniel.escuela.dto.datos.DatosAlumno;
import com.daniel.escuela.dto.datos.DatosHorario;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record InscripcionResponse(
        @Schema(description = "ID de la inscripción", example = "1")
        Long id,

        DatosAlumno alumno,

        DatosHorario grupo,

        @Schema(description = "Calificación obtenida", nullable = true, example = "null")
        Double calificacion,

        @Schema(description = "Fecha de inscripción", example = "11/02/2026")
        LocalDate fechaInscripcion
) {
}