package com.daniel.escuela.services.inscripciones;

import com.daniel.escuela.dto.inscripcion.InscripcionRequest;
import com.daniel.escuela.dto.inscripcion.InscripcionResponse;
import com.daniel.escuela.entities.Alumno;
import com.daniel.escuela.entities.Grupo;
import com.daniel.escuela.entities.Inscripcion;
import com.daniel.escuela.mappers.InscripcionMapper;
import com.daniel.escuela.repositories.AlumnoRepository;
import com.daniel.escuela.repositories.GrupoRepository;
import com.daniel.escuela.repositories.InscripcionRepository;
import com.daniel.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final AlumnoRepository alumnoRepository;
    private final GrupoRepository grupoRepository;
    private final InscripcionMapper inscripcionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<InscripcionResponse> listar() {
        log.info("Listando inscripciones...");
        return inscripcionRepository.findAll().stream()
                .map(inscripcionMapper::entidadAResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public InscripcionResponse obtenerPorId(Long id) {
        Inscripcion inscripcion = obtenerInscripcion(id);
        return inscripcionMapper.entidadAResponse(inscripcion);
    }

    @Override
    public InscripcionResponse registrar(InscripcionRequest request) {
        log.info("Registrando nueva inscripción...");

        validarUnicidad(request.idAlumno(), request.idGrupo(), null);

        Alumno alumno = ServiceUtils.obtenerEntidadException(alumnoRepository, request.idAlumno(), Alumno.class);
        Grupo grupo = ServiceUtils.obtenerEntidadException(grupoRepository, request.idGrupo(), Grupo.class);

        Inscripcion inscripcion = Inscripcion.builder()
                .alumno(alumno)
                .grupo(grupo)
                .build();

        inscripcionRepository.save(inscripcion);
        log.info("Inscripción registrada con id: {}", inscripcion.getId());

        return inscripcionMapper.entidadAResponse(inscripcion);
    }

    @Override
    public InscripcionResponse actualizar(InscripcionRequest request, Long id) {
        Inscripcion inscripcion = obtenerInscripcion(id);
        log.info("Actualizando inscripción con id: {}", id);

        validarUnicidad(request.idAlumno(), request.idGrupo(), inscripcion);

        Alumno alumno = ServiceUtils.obtenerEntidadException(alumnoRepository, request.idAlumno(), Alumno.class);
        Grupo grupo = ServiceUtils.obtenerEntidadException(grupoRepository, request.idGrupo(), Grupo.class);

        inscripcion = Inscripcion.builder()
                .id(inscripcion.getId())
                .alumno(alumno)
                .grupo(grupo)
                .fechaInscripcion(inscripcion.getFechaInscripcion())
                .calificacion(inscripcion.getCalificacion())
                .build();

        inscripcionRepository.save(inscripcion);
        log.info("Inscripción con id {} actualizada correctamente", id);

        return inscripcionMapper.entidadAResponse(inscripcion);
    }

    @Override
    public void eliminar(Long id) {
        Inscripcion inscripcion = obtenerInscripcion(id);
        log.info("Eliminando inscripción con id: {}", id);

        if (inscripcion.getCalificacion() != null) {
            throw new IllegalArgumentException("No se puede eliminar una inscripción que ya cuenta con una calificación asociada");
        }

        inscripcionRepository.delete(inscripcion);
        log.info("Inscripción con id {} eliminada correctamente", id);
    }

    private Inscripcion obtenerInscripcion(Long id) {
        return ServiceUtils.obtenerEntidadException(inscripcionRepository, id, Inscripcion.class);
    }

    private void validarUnicidad(Long idAlumno, Long idGrupo, Inscripcion inscripcionExistente) {
        if (inscripcionExistente != null) {
            boolean mismoAlumno = inscripcionExistente.getAlumno().getId().equals(idAlumno);
            boolean mismoGrupo = inscripcionExistente.getGrupo().getId().equals(idGrupo);

            if (mismoAlumno && mismoGrupo) {
                return;
            }
        }

        if (inscripcionRepository.existsByAlumnoIdAndGrupoId(idAlumno, idGrupo)) {
            throw new IllegalArgumentException("El alumno ya se encuentra inscrito en este grupo");
        }
    }
}