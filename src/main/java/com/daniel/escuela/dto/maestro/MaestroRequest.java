package com.daniel.escuela.dto.maestro;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para crear o actualizar un Maestro")
public record MaestroRequest(
        @Schema(
                description = "Nombre del maestro",
                example = "JOSE"
        )
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 5, max = 50, message = "El nombre debe tener 5 y 50 caracteres")
        String nombre,

        @Schema(
                description = "Apellido paterno del maestro",
                example = "Hernandez"
        )
        @NotBlank(message = "El apellido paterno es requerido")
        @Size(min = 5, max = 50, message = "El apellido paterno debe tener 5 y 50 caracteres")
        String apPaterno,

        @Schema(
                description = "Apellido materno del maestro",
                example = "Juarez"
        )
        @NotBlank(message = "El apellido materno es requerido")
        @Size(min = 5, max = 50, message = "El apellido paterno debe tener 5 y 50 caracteres")
        String apMaterno,

        @Schema(
                description = "El Email del maestro",
                example = "tr.2026.jose.hernandez.juarez.pegoju2601@escuela.com.mx"
        )
        @NotBlank(message = "El email es requerido")
        @Size(min = 5, max = 50, message = "El email debe tener 5 y 50 caracteres")
        String email,

        @Schema(
                description = "El telefono del maestro",
                example = "1234567890"
        )
        @NotBlank(message = "El telefono es requerido")
        @Size(min = 5, max = 50, message = "El telefono debe tener 5 y 50 caracteres")
        String telefono


) {
}
