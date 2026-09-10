package com.daniel.escuela.mappers;

import com.daniel.escuela.dto.datos.DatosHorario;
import com.daniel.escuela.dto.horario.HorarioRequest;
import com.daniel.escuela.dto.horario.HorarioResponse;
import com.daniel.escuela.entities.Grupo;
import com.daniel.escuela.entities.Horario;
import com.daniel.escuela.enums.DiaSemana;
import org.springframework.stereotype.Component;

@Component
public class HorarioMapper implements CommonMapper<HorarioRequest, HorarioResponse, Horario> {

    @Override
    public Horario requestAEntidad(HorarioRequest request) {
        if (request == null) return null;

        DiaSemana diaEnum = request.dia() != null
                ? DiaSemana.valueOf(request.dia().toUpperCase().trim())
                : null;

        return Horario.builder()
                .diaSemana(diaEnum)
                .horaInicio(request.horaInicio() != null ? request.horaInicio().trim() : null)
                .horaFin(request.horaFin() != null ? request.horaFin().trim() : null)
                .build();
    }

    @Override
    public HorarioResponse entidadAResponse(Horario entidad) {
        if (entidad == null) return null;

        Grupo grupo = entidad.getGrupo();
        DatosHorario datosGrupo = null;

        if (grupo != null) {
            String nombreMaestro = (grupo.getMaestro() != null)
                    ? (grupo.getMaestro().getNombre() + " " + grupo.getMaestro().getApellidoPaterno() + " " + grupo.getMaestro().getApellidoMaterno()).trim()
                    : null;

            datosGrupo = new DatosHorario(
                    grupo.getCurso() != null ? grupo.getCurso().getNombre() : null,
                    nombreMaestro,
                    grupo.getAula() != null ? grupo.getAula().getNombre() : null,
                    grupo.getPeriodo()
            );
        }

        String diaNombre = entidad.getDiaSemana() != null ? entidad.getDiaSemana().name() : "";
        String horarioString = (diaNombre + " " + entidad.getHoraInicio() + " " + entidad.getHoraFin()).trim();

        return new HorarioResponse(
                entidad.getId(),
                datosGrupo,
                horarioString
        );
    }
}