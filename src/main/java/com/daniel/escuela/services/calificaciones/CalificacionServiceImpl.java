package com.daniel.escuela.services.calificaciones;

import com.daniel.escuela.dto.calificacion.CalificacionRequest;
import com.daniel.escuela.dto.calificacion.CalificacionResponse;
import com.daniel.escuela.entities.Calificacion;
import com.daniel.escuela.entities.Inscripcion;
import com.daniel.escuela.mappers.CalificacionMapper;
import com.daniel.escuela.repositories.CalificacionRepository;
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
public class CalificacionServiceImpl implements CalificacionService {

    private final CalificacionRepository calificacionRepository;
    private final InscripcionRepository inscripcionRepository;
    private final CalificacionMapper calificacionMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CalificacionResponse> listar() {
        log.info("Listando calificaciones...");
        return calificacionRepository.findAll().stream()
                .map(calificacionMapper::entidadAResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CalificacionResponse obtenerPorId(Long id) {
        Calificacion calificacion = obtenerCalificacion(id);
        return calificacionMapper.entidadAResponse(calificacion);
    }

    @Override
    public CalificacionResponse registrar(CalificacionRequest request) {
        log.info("Registrando nueva calificación...");

        validarUnicidad(request.idInscripcion(), null);

        Inscripcion inscripcion = ServiceUtils.obtenerEntidadException(
                inscripcionRepository, request.idInscripcion(), Inscripcion.class);

        Calificacion calificacion = Calificacion.builder()
                .inscripcion(inscripcion)
                .calificacion(request.calificacion())
                .build();

        calificacionRepository.save(calificacion);
        log.info("Calificación registrada con id: {}", calificacion.getId());

        return calificacionMapper.entidadAResponse(calificacion);
    }

    @Override
    public CalificacionResponse actualizar(CalificacionRequest request, Long id) {
        Calificacion calificacionExistente = obtenerCalificacion(id);
        log.info("Actualizando calificación con id: {}", id);

        validarUnicidad(request.idInscripcion(), calificacionExistente);

        Inscripcion inscripcion = ServiceUtils.obtenerEntidadException(
                inscripcionRepository, request.idInscripcion(), Inscripcion.class);

        Calificacion calificacion = Calificacion.builder()
                .id(calificacionExistente.getId())
                .inscripcion(inscripcion)
                .calificacion(request.calificacion())
                .fechaRegistro(calificacionExistente.getFechaRegistro())
                .build();

        calificacionRepository.save(calificacion);
        log.info("Calificación con id {} actualizada correctamente", id);

        return calificacionMapper.entidadAResponse(calificacion);
    }

    @Override
    public void eliminar(Long id) {
        Calificacion calificacion = obtenerCalificacion(id);
        log.info("Eliminando calificación con id: {}", id);

        calificacionRepository.delete(calificacion);
        log.info("Calificación con id {} eliminada correctamente", id);
    }

    private Calificacion obtenerCalificacion(Long id) {
        return ServiceUtils.obtenerEntidadException(calificacionRepository, id, Calificacion.class);
    }

    private void validarUnicidad(Long idInscripcion, Calificacion calificacionExistente) {
        if (calificacionExistente != null) {
            boolean mismaInscripcion = calificacionExistente.getInscripcion().getId().equals(idInscripcion);
            if (mismaInscripcion) {
                return;
            }
        }

        if (calificacionRepository.existsByInscripcionId(idInscripcion)) {
            throw new IllegalArgumentException("La inscripción ya cuenta con una calificación asignada");
        }
    }
}