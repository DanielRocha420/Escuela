package com.daniel.escuela.dto.cursos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record CursoRequest(
        @Schema(description = "Nombre del curso", example = "Matemáticas I")
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
        String nombre,

        @Schema(description = "Descripción del curso", example = "Cálculo integral y diferencial básico")
        @Size(max = 200, message = "La descripción debe tener máximo 200 caracteres")
        String descripcion,

        @Schema(description = "Créditos del curso", example = "6")
        @NotNull(message = "Los créditos son requeridos")
        @Min(value = 1, message = "Los créditos mínimos son 1")
        @Max(value = 99, message = "Los créditos máximos son 99")
        Integer creditos
) {
}