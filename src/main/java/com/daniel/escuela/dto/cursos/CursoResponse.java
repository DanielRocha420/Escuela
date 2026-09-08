package com.daniel.escuela.dto.cursos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record CursoResponse(
        @Schema(description = "ID del curso", example = "1")
        Long id,

        @Schema(description = "Nombre del curso", example = "Matematicas 1")
        String nombre,

        @Schema(description = "Descripcion del curso", example = "Calculo integral")

        String descripcion,

        @Schema(description = "creditos del curso", example = "1")
        Integer creditos
) {
}
