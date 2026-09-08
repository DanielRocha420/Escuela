package com.daniel.escuela.dto.maestro;

import com.daniel.escuela.dto.datos.DatosCurso;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Objects;

public record MaestroResponse(
        @Schema(description = "Identificador del maestro",example = "1")
        Long id,
        @Schema(description = "Nombre del maestro",example = "Jose")
        String nombre,
        @Schema(description = "Email del maestro",example = "tr.2026.jose.hernandez.juarez.pegoju2601@escuela.com.mx")
        String email,
        @Schema(description = "Telefono del maestro",example = "1234567890")
        String telefono,
        @Schema(description = "Datos de los cursos del maestro")
        List<DatosCurso> cursos
) {
}
