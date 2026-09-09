package com.daniel.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record DatosCalificacion(

        @Schema(description = "Nombre del curso", example = "Matematicas 1")
        String curso,
        @Schema(description = "Periodo")
        String periodo,
        @Schema(description = "Calificacion", example = "9.9")
        BigDecimal calificacion
) {
}
