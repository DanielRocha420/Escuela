package com.daniel.escuela.mappers;

import com.daniel.escuela.dto.datos.DatosAula;
import com.daniel.escuela.dto.datos.DatosCurso;
import com.daniel.escuela.dto.datos.DatosMaestro;
import com.daniel.escuela.dto.grupo.GrupoRequest;
import com.daniel.escuela.dto.grupo.GrupoResponse;
import com.daniel.escuela.entities.Aula;
import com.daniel.escuela.entities.Curso;
import com.daniel.escuela.entities.Grupo;
import com.daniel.escuela.entities.Horario;
import com.daniel.escuela.entities.Maestro;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GrupoMapper implements CommonMapper<GrupoRequest, GrupoResponse, Grupo> {

    @Override
    public Grupo requestAEntidad(GrupoRequest request) {
        if (request == null) return null;

        return Grupo.builder().build();
    }

    @Override
    public GrupoResponse entidadAResponse(Grupo entidad) {
        if (entidad == null) return null;

        // Validaciones contra NullPointerException
        DatosCurso datosCurso = null;
        if (entidad.getCurso() != null) {
            Curso c = entidad.getCurso();
            datosCurso = new DatosCurso(c.getNombre(), c.getDescripcion(), c.getCreditos());
        }

        DatosMaestro datosMaestro = null;
        if (entidad.getMaestro() != null) {
            Maestro m = entidad.getMaestro();
            String nombreMaestro = String.format("%s %s %s",
                    m.getNombre() != null ? m.getNombre() : "",
                    m.getApellidoPaterno() != null ? m.getApellidoPaterno() : "",
                    m.getApellidoMaterno() != null ? m.getApellidoMaterno() : "").trim();
            datosMaestro = new DatosMaestro(nombreMaestro, m.getEmail(), m.getTelefono());
        }

        DatosAula datosAula = null;
        if (entidad.getAula() != null) {
            Aula a = entidad.getAula();
            datosAula = new DatosAula(a.getNombre(), a.getCapacidad());
        }

        List<String> horarios = new ArrayList<>();
        if (entidad.getHorarios() != null) {
            for (Horario h : entidad.getHorarios()) {
                if (h.getDiaSemana() != null) {
                    String diaCapitalizado = h.getDiaSemana().name().charAt(0) +
                            h.getDiaSemana().name().substring(1).toLowerCase();
                    horarios.add(diaCapitalizado + " " + h.getHoraInicio() + " - " + h.getHoraFin());
                }
            }
        }

        return new GrupoResponse(
                entidad.getId(),
                datosCurso,
                datosMaestro,
                datosAula,
                horarios,
                entidad.getPeriodo()
        );
    }
}