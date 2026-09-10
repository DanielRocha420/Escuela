package com.daniel.escuela.dto.inscripcion;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record InscripcionRequest(
        @Schema(description = "ID del alumno", example = "10")
        @NotNull(message = "El ID del alumno es requerido")
        Long idAlumno,

        @Schema(description = "ID del grupo", example = "5")
        @NotNull(message = "El ID del grupo es requerido")
        Long idGrupo
) {
}