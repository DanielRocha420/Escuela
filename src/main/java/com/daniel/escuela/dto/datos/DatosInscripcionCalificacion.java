package com.daniel.escuela.dto.datos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record DatosInscripcionCalificacion(
        DatosAlumno alumno,

        DatosHorario grupo,

        @Schema(description = "Fecha de inscripción", example = "11/02/2026")
        LocalDate fechaInscripcion
) {
}