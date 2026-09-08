package com.daniel.escuela.dto.maestro;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Datos necesarios para registrar o actualizar un maestro")
public record MaestroRequest(

        @Schema(description = "Nombre del maestro", example = "Miguel")
        @NotNull(message = "El nombre es requerido")
        @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
        String nombre,

        @Schema(description = "Apellido Paterno del maestro", example = "Romero")
        @NotBlank(message = "El apellido paterno es requerido")
        @Size(max = 50, message = "El apellido paterno no puede exceder 50 caracteres")
        String apellidoPaterno,

        @Schema(description = "Apellido Materno del maestro", example = "Alcantara")
        @NotBlank(message = "El apellido materno es requerido")
        @Size(max = 50, message = "El apellido materno no puede exceder 50 caracteres")
        String apellidoMaterno,

        @Schema(description = "Email del maestro", example = "test@test.com")
        @NotBlank(message = "El email es requerido")
        @Email(message = "El email debe tener un formato valido")
        @Size(max = 100, message = "El email no puede exceder 100 caracteres")
        String email,

        @Schema(description = "Telefono del maestro", example = "1234567890")
        @NotBlank(message = "El telefono es requerido")
        @Size(max = 10, message = "El telefono no puede exceder 10 caracteres")
        String telefono
) {
}
