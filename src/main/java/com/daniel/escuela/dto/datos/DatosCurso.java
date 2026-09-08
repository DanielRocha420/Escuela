package com.daniel.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Nombre del curso")
public record DatosCurso(
        @Schema(description = "Nombre del curso", example = "Matematicas 1")
        String nombre,
        @Schema(description = "Descripcion del curso", example = "nolose")
        String descripcion,
        @Schema(description = "creditos del curso", example = "1")
        Integer creditos

) {
}
