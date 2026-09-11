package com.daniel.escuela.dto.grupo;

import com.daniel.escuela.dto.datos.DatosAula;
import com.daniel.escuela.dto.datos.DatosCurso;
import com.daniel.escuela.dto.datos.DatosMaestro;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record GrupoResponse(
        @Schema(description = "ID del grupo", example = "1")
        Long id,

        DatosCurso curso,

        DatosMaestro maestro,

        DatosAula aula,

        @Schema(description = "Lista de horarios formateados", example = "[\"Lunes 08:00 - 10:00\", \"Miércoles 08:00 - 10:00\"]")
        List<String> horarios,

        @Schema(description = "Periodo académico", example = "2026-01")
        String periodo
) {
}