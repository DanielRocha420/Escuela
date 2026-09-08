package com.daniel.escuela.dto.cursos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record CursoRequest(
        @Schema(description = "Nombre del curso", example = "Matematicas 1")
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 5, max = 100, message = "El nombre debe tener entre 5 y 100 caracteres")
        String nombre,

        @Schema(description = "Descripcion del curso", example = "Calculo integral")
        @Size(max = 200, message = "La descripcion debe tener maximo 200 caracteres")
        String descripcion,

        @Schema(description = "creditos del curso", example = "1")
        @NotNull(message = "Los creditos son requeridos")
        @Min(value = 1, message = "Los creditos minimos son 1")
        @Max(value = 1, message = "Los creditos minimos son 1")
        Integer creditos
) {
}
