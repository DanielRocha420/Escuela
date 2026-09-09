package com.daniel.escuela.services.alumnos;

import com.daniel.escuela.dto.alumno.AlumnoRequest;
import com.daniel.escuela.dto.alumno.AlumnoResponse;
import com.daniel.escuela.entities.Alumno;
import com.daniel.escuela.mappers.AlumnoMapper;
import com.daniel.escuela.repositories.AlumnoRepository;
import com.daniel.escuela.repositories.InscripcionRepository;
import com.daniel.escuela.utils.ServiceUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    private final InscripcionRepository inscripcionRepository;

    private final AlumnoMapper alumnoMapper;

    @Override
    public List<AlumnoResponse> listar() {

        log.info("Listando todos los alumnos");

        return alumnoRepository.findAll().stream()
                .map(alumnoMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoResponse obtenerPorId(Long id) {
        return alumnoMapper.entidadAResponse(obtenerAlumno(id));
    }

    @Override
    public AlumnoResponse registrar(AlumnoRequest request) {

        log.info("Registrando nuevo alumno...");

        Alumno alumno = alumnoMapper.requestAEntidad(
                request,
                generarEmail(request),
                generarMatricula(request)
        );

        alumnoRepository.save(alumno);

        log.info("Nuevo alumno {} registrado correctamente", alumno.getNombre());

        return alumnoMapper.entidadAResponse(alumno);
    }

    @Override
    public AlumnoResponse actualizar(AlumnoRequest request, Long id) {

        Alumno alumno = obtenerAlumno(id);

        log.info("Actualizando alumno con id: {}", id);

        if (alumno.cambioEnDatos(
                request.nombre().trim(),
                request.apellidoPaterno().trim(),
                request.apellidoMaterno().trim()
        )) {

            alumno.actualizar(
                    request.nombre(),
                    request.apellidoPaterno(),
                    request.apellidoMaterno(),
                    generarEmail(request),
                    generarMatricula(request));
            log.info("Datos academicos regenerados para el alumno con id: {}", id);
        }

        return alumnoMapper.entidadAResponse(alumno);
    }

    @Override
    public void eliminar(Long id) {
        Alumno alumno = obtenerAlumno(id);
        alumnoRepository.delete(alumno);
        log.info("Alumno con id {} eliminado correctamente", id);
        if(inscripcionRepository.existsById(id))
            throw new EntityNotFoundException("No se puede eliminar el alumno ya tiene inscripcion asignada");
        alumnoRepository.delete(alumno);
        log.info("Alumno con id {} eliminado correctamente", id);
    }

    public Alumno obtenerAlumno(Long id) {
        return ServiceUtils.obtenerEntidadException(alumnoRepository, id, Alumno.class);
    }

    private String generarEmail(AlumnoRequest request) {

        log.info("Generando email...");

        return alumnoRepository.generarEmail(
                request.nombre().trim(),
                request.apellidoPaterno().trim(),
                request.apellidoMaterno().trim());
    }

    private String generarMatricula(AlumnoRequest request) {

        log.info("Generando matricula...");

        return alumnoRepository.generarMatricula(
                request.nombre().trim(),
                request.apellidoPaterno().trim(),
                request.apellidoMaterno().trim());
    }
}