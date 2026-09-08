package com.daniel.escuela.dto.alumno;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AlumnoRequestt(
        @Schema(description = "Nombre del alumno", example = "Juan")
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 5, max = 50, message = "El nombre debe tener entre 5 y 50 caracteres")
        String nombre,

        @Schema(description = "Apellido paterno del alumno", example = "Pérez")
        @NotBlank(message = "El apellido paterno es requerido")
        @Size(min = 5, max = 50, message = "El apellido paterno debe tener entre 5 y 50 caracteres")
        String apPaterno,

        @Schema(description = "Apellido materno del alumno", example = "González")
        @NotBlank(message = "El apellido materno es requerido")
        @Size(min = 5, max = 50, message = "El apellido materno debe tener entre 5 y 50 caracteres") // Corregido el mensaje
        String apMaterno,

        @Schema(description = "El Email del alumno", example = "juan.perez@escuela.com.mx")
        @NotBlank(message = "El email es requerido")
        @Email(message = "El formato del email no es válido") // Agregado @Email
        @Size(min = 5, max = 100, message = "El email debe tener entre 5 y 100 caracteres") // Corregido el mensaje
        String email,

        @Schema(description = "La matrícula del alumno", example = "1234567890")
        @NotBlank(message = "La matrícula es requerida")
        @Size(min = 10, max = 10, message = "La matrícula debe tener exactamente 10 caracteres") // Corregidos min y max
        String matricula,

        @Schema(description = "Fecha de ingreso del alumno", example = "2026-09-08", type = "string")
        @NotNull(message = "La fecha de ingreso es requerida")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate fecha
) {
}
