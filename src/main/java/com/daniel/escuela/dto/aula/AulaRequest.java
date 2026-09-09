package com.daniel.escuela.dto.aula;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record AulaRequest(
        @Schema(description = "Nombre o identificador del aula", example = "Aula 101")
        @NotBlank(message = "El nombre del aula es requerido")
        @Size(min = 1, max = 100, message = "El nombre del aula debe tener entre 1 y 100 caracteres")
        String nombre,

        @Schema(description = "Capacidad de alumnos del aula", example = "30")
        @NotNull(message = "La capacidad es requerida")
        @Min(value = 1, message = "La capacidad mínima debe ser 1")
        @Max(value = 9999, message = "La capacidad no puede superar los 9999 alumnos")
        Integer capacidad
) {
}
