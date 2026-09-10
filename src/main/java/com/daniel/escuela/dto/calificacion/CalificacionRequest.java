package com.daniel.escuela.dto.calificacion;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CalificacionRequest(
        @Schema(description = "ID de la inscripción", example = "15")
        @NotNull(message = "El ID de la inscripción es requerido")
        Long idInscripcion,

        @Schema(description = "Calificación obtenida", example = "8.5")
        @NotNull(message = "La calificación es requerida")
        @DecimalMin(value = "0.0", message = "La calificación mínima es 0.0")
        @DecimalMax(value = "10.0", message = "La calificación máxima es 10.0")
        BigDecimal calificacion
) {
}