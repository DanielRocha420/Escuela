package com.daniel.escuela.dto.horario;

import com.daniel.escuela.dto.datos.DatosHorario;
import io.swagger.v3.oas.annotations.media.Schema;

public record HorarioResponse(
        @Schema(description = "ID del horario", example = "1")
        Long id,

        DatosHorario grupo,

        @Schema(description = "Representación legible del horario", example = "Lunes 08:00 10:00")
        String horario
) {
}