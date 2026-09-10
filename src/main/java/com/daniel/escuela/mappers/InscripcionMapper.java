package com.daniel.escuela.mappers;

import com.daniel.escuela.dto.datos.DatosAlumno;
import com.daniel.escuela.dto.datos.DatosHorario;
import com.daniel.escuela.dto.inscripcion.InscripcionRequest;
import com.daniel.escuela.dto.inscripcion.InscripcionResponse;
import com.daniel.escuela.entities.Alumno;
import com.daniel.escuela.entities.Grupo;
import com.daniel.escuela.entities.Inscripcion;
import org.springframework.stereotype.Component;

@Component
public class InscripcionMapper implements CommonMapper<InscripcionRequest, InscripcionResponse, Inscripcion> {

    @Override
    public Inscripcion requestAEntidad(InscripcionRequest request) {
        if (request == null) return null;

        return Inscripcion.builder().build();
    }

    @Override
    public InscripcionResponse entidadAResponse(Inscripcion entidad) {
        if (entidad == null) return null;

        Alumno a = entidad.getAlumno();
        String nombreAlumno = a.getNombre() + " " + a.getApellidoPaterno() + " " + a.getApellidoMaterno();
        DatosAlumno datosAlumno = new DatosAlumno(nombreAlumno, a.getMatricula(), a.getEmail(), a.getFechaIngreso());

        Grupo g = entidad.getGrupo();
        String nombreMaestro = g.getMaestro().getNombre() + " " + g.getMaestro().getApellidoPaterno() + " " + g.getMaestro().getApellidoMaterno();
        DatosHorario datosGrupo = new DatosHorario(g.getCurso().getNombre(), nombreMaestro, g.getAula().getNombre(), g.getPeriodo());

        Double calificacion = null;
        if (entidad.getCalificacion() != null && entidad.getCalificacion().getCalificacion() != null) {
            calificacion = entidad.getCalificacion().getCalificacion().doubleValue();
        }

        return new InscripcionResponse(
                entidad.getId(),
                datosAlumno,
                datosGrupo,
                calificacion,
                entidad.getFechaInscripcion()
        );
    }
}