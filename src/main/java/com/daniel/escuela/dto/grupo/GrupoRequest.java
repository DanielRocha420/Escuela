package com.daniel.escuela.dto.grupo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GrupoRequest(
        @Schema(description = "ID del curso", example = "2")
        @NotNull(message = "El ID del curso es requerido")
        Long idCurso,

        @Schema(description = "ID del maestro", example = "7")
        @NotNull(message = "El ID del maestro es requerido")
        Long idMaestro,

        @Schema(description = "ID del aula", example = "3")
        @NotNull(message = "El ID del aula es requerido")
        Long idAula,

        @Schema(description = "Periodo académico", example = "2026-01")
        @NotBlank(message = "El periodo es requerido")
        String periodo
) {
}