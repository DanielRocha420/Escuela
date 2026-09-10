package com.daniel.escuela.dto.calificacion;

import com.daniel.escuela.dto.datos.DatosInscripcionCalificacion;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record CalificacionResponse(
        @Schema(description = "ID de la calificación", example = "2")
        Long id,

        DatosInscripcionCalificacion inscripcion,

        @Schema(description = "Calificación obtenida", example = "9.0")
        Double calificacion,

        @Schema(description = "Fecha de registro", example = "11/02/2026")
        LocalDate fechaRegistro
) {
}