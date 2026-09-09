package com.daniel.escuela.dto.aula;

import io.swagger.v3.oas.annotations.media.Schema;

public record AulaResponse(
        @Schema(description = "ID del aula", example = "1")
        Long id,

        @Schema(description = "Nombre del aula", example = "Aula 101")
        String nombre,

        @Schema(description = "Capacidad de alumnos", example = "30")
        Integer capacidad
) {
}