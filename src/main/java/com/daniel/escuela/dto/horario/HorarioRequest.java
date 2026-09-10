package com.daniel.escuela.dto.horario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record HorarioRequest(
        @Schema(description = "ID del grupo", example = "1")
        @NotNull(message = "El ID del grupo es requerido")
        Long idGrupo,

        @Schema(description = "Día de la semana", example = "Lunes")
        @NotBlank(message = "El día es requerido")
        String dia,

        @Schema(description = "Hora de inicio en formato HH:mm", example = "08:00")
        @NotBlank(message = "La hora de inicio es requerida")
        @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "La hora de inicio debe tener el formato HH:mm")
        String horaInicio,

        @Schema(description = "Hora de fin en formato HH:mm", example = "10:00")
        @NotBlank(message = "La hora de fin es requerida")
        @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "La hora de fin debe tener el formato HH:mm")
        String horaFin
) {
}