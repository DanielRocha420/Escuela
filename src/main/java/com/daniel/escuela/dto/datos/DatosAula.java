package com.daniel.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

public record DatosAula(
        @Schema(description = "Nombre del aula", example = "Aula 101")
        String nombre,
        @Schema(description = "Capacidad del aula", example = "1")
        Integer capacidad
) {
}
