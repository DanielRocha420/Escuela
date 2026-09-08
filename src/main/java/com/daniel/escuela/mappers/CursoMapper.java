package com.daniel.escuela.mappers;

import com.daniel.escuela.dto.cursos.CursoRequest;
import com.daniel.escuela.dto.cursos.CursoResponse;
import com.daniel.escuela.dto.datos.DatosCurso;
import com.daniel.escuela.entities.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper implements CommonMapper<CursoRequest, CursoResponse, Curso> {

    @Override
    public Curso requestAEntidad(CursoRequest request) {

        if (request == null) return null;

        return Curso.builder()
                .nombre(request.nombre().trim())
                .descripcion(request.descripcion() != null ? request.descripcion().trim() : null)
                .creditos(request.creditos())
                .build();
    }

    @Override
    public CursoResponse entidadAResponse(Curso entidad) {
        if (entidad == null) return null;

        return new CursoResponse(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getDescripcion() != null ?
                        entidad.getDescripcion() : "Sin Descripcion",
                entidad.getCreditos());
    }

    // Cambiado de CursoResponse a DatosCurso en el tipo de retorno
    public DatosCurso entidadADatosCurso(Curso entidad) {
        if (entidad == null) return null;

        return new DatosCurso(
                entidad.getNombre(),
                entidad.getDescripcion() != null ?
                        entidad.getDescripcion() : "Sin Descripcion",
                entidad.getCreditos());
    }
}