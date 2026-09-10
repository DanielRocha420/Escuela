package com.daniel.escuela.mappers;

import com.daniel.escuela.dto.calificacion.CalificacionRequest;
import com.daniel.escuela.dto.calificacion.CalificacionResponse;
import com.daniel.escuela.dto.datos.DatosAlumno;
import com.daniel.escuela.dto.datos.DatosHorario;
import com.daniel.escuela.dto.datos.DatosInscripcionCalificacion;
import com.daniel.escuela.entities.Alumno;
import com.daniel.escuela.entities.Calificacion;
import com.daniel.escuela.entities.Grupo;
import com.daniel.escuela.entities.Inscripcion;
import org.springframework.stereotype.Component;

@Component
public class CalificacionMapper implements CommonMapper<CalificacionRequest, CalificacionResponse, Calificacion> {

    @Override
    public Calificacion requestAEntidad(CalificacionRequest request) {
        if (request == null) return null;

        return Calificacion.builder().build();
    }

    @Override
    public CalificacionResponse entidadAResponse(Calificacion entidad) {
        if (entidad == null) return null;

        Inscripcion ins = entidad.getInscripcion();

        Alumno a = ins.getAlumno();
        String nombreAlumno = a.getNombre() + " " + a.getApellidoPaterno() + " " + a.getApellidoMaterno();
        DatosAlumno datosAlumno = new DatosAlumno(nombreAlumno, a.getMatricula(), a.getEmail(), a.getFechaIngreso());

        Grupo g = ins.getGrupo();
        String nombreMaestro = g.getMaestro().getNombre() + " " + g.getMaestro().getApellidoPaterno() + " " + g.getMaestro().getApellidoMaterno();
        DatosHorario datosGrupo = new DatosHorario(g.getCurso().getNombre(), nombreMaestro, g.getAula().getNombre(), g.getPeriodo());

        DatosInscripcionCalificacion datosInscripcion = new DatosInscripcionCalificacion(
                datosAlumno,
                datosGrupo,
                ins.getFechaInscripcion()
        );

        Double calificacionValor = null;
        if (entidad.getCalificacion() != null) {
            calificacionValor = entidad.getCalificacion().doubleValue();
        }

        return new CalificacionResponse(
                entidad.getId(),
                datosInscripcion,
                calificacionValor,
                entidad.getFechaRegistro()
        );
    }
}