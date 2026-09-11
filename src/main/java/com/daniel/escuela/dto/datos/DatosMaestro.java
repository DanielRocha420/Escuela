package com.daniel.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

public record DatosMaestro(
        @Schema(description = "Nombre completo del maestro", example = "Laura Martínez Martínez")
        String nombre,

        @Schema(description = "Correo electrónico del maestro", example = "laura.martinez@escuela.com")
        String email,

        @Schema(description = "Teléfono de contacto", example = "5551010789")
        String telefono
) {
}