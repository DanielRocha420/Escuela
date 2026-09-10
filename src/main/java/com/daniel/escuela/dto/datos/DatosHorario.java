package com.daniel.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

public record DatosHorario(
        @Schema(description = "Nombre del curso", example = "Matemáticas I")
        String curso,

        @Schema(description = "Nombre completo del maestro", example = "Laura Martínez Martínez")
        String maestro,

        @Schema(description = "Nombre del aula", example = "Aula 101")
        String aula,

        @Schema(description = "Periodo académico", example = "2025-1")
        String periodo
) {
}