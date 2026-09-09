package com.daniel.escuela.mappers;

import com.daniel.escuela.dto.aula.AulaRequest;
import com.daniel.escuela.dto.aula.AulaResponse;
import com.daniel.escuela.dto.datos.DatosAula;
import com.daniel.escuela.entities.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper implements CommonMapper<AulaRequest, AulaResponse, Aula>{

    @Override
    public Aula requestAEntidad(AulaRequest request){
        if (request == null) return null;

        return Aula.builder()
                .nombre(request.nombre().trim())
                .capacidad(request.capacidad())
                .build();
    }

    public AulaResponse entidadAResponse(Aula entidad) {
        if (entidad == null) return null;

        return new AulaResponse(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getCapacidad()
        );
    }

    public DatosAula entidadADatosAula(Aula entidad){
        if (entidad == null) return null;

        return new DatosAula(
                entidad.getNombre(),
                entidad.getCapacidad());
    }
}
