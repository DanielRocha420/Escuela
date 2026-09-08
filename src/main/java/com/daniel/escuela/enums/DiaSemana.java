package com.daniel.escuela.enums;

import com.daniel.escuela.utils.StringCustomUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DiaSemana {
    LUNES("Lunes"),
    MARTES("Martes"),
    MIERCOLES("Miércoles"),
    JUEVES("Jueves"),
    VIERNES("Viernes"),
    SABADO("Sábado");

    private final String descripcion;


    public static DiaSemana obtenerPorDescripcion(String descripcion) {

        StringCustomUtils.validarNoVacio(descripcion, "La descripción es requerida");

        String descripcionNormalizada = StringCustomUtils.quitarAcentos(descripcion);

        for (DiaSemana diaSemana : values()) {
            if (StringCustomUtils.quitarAcentos(diaSemana.getDescripcion()).equalsIgnoreCase(descripcionNormalizada)) {
                return diaSemana;
            }
        }

        throw new IllegalArgumentException("No existe un día de la semana con la descripción: " + descripcion);
    }
}