package com.daniel.escuela.dto.cursos;

import io.swagger.v3.oas.annotations.media.Schema;

public record CursoResponse(
        @Schema(description = "ID único del curso", example = "1")
        Long id,

        @Schema(description = "Nombre del curso", example = "Matemáticas I")
        String nombre,

        @Schema(description = "Descripción del curso", example = "Cálculo integral y diferencial básico")
        String descripcion,

        @Schema(description = "Créditos del curso", example = "6")
        Integer creditos
) {
}