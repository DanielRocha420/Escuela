package com.daniel.escuela.services.cursos;

import com.daniel.escuela.dto.cursos.CursoRequest;
import com.daniel.escuela.dto.cursos.CursoResponse;
import com.daniel.escuela.entities.Curso;
import com.daniel.escuela.exceptions.EntidadRelacionadaException;
import com.daniel.escuela.mappers.CursoMapper;
import com.daniel.escuela.repositories.CursoRepository;
import com.daniel.escuela.repositories.GrupoRepository;
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
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final GrupoRepository grupoRepository;
    private final CursoMapper cursoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CursoResponse> listar() {
        log.info("Listando todos los cursos");
        return cursoRepository.findAll().stream()
                .map(cursoMapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CursoResponse obtenerPorId(Long id) {
        return cursoMapper.entidadAResponse(obtenerCurso(id));
    }

    @Override
    public CursoResponse registrar(CursoRequest request) {
        log.info("Registrando nuevo Curso...");

        validarDatosUnicos(request);

        Curso curso = cursoMapper.requestAEntidad(request);

        cursoRepository.save(curso);

        log.info("Nuevo Curso {} registrado", curso.getNombre());

        return cursoMapper.entidadAResponse(curso);
    }

    @Override
    public CursoResponse actualizar(CursoRequest request, Long id) {
        Curso curso = obtenerCurso(id);
        log.info("Actualizando curso con id: {}", id);

        validarCambioUnico(request, id);

        curso.actualizar(
                request.nombre(),
                request.descripcion(),
                request.creditos()
        );

        log.info("Curso con id {} actualizado correctamente", id);
        return cursoMapper.entidadAResponse(curso);
    }

    @Override
    public void eliminar(Long id) {
        Curso curso = obtenerCurso(id);
        log.info("Eliminando curso con id: {}", id);

        if (grupoRepository.existsByCursoId(id)) {
            throw new EntidadRelacionadaException("No se puede eliminar el curso ya que tiene grupos asignados");
        }

        cursoRepository.delete(curso);
        log.info("Curso con id {} eliminado correctamente", id);
    }

    private Curso obtenerCurso(Long id) {
        return ServiceUtils.obtenerEntidadException(cursoRepository, id, Curso.class);
    }

    private void validarDatosUnicos(CursoRequest request) {
        log.info("Validando nombre unico...");

        if (cursoRepository.existsByNombreIgnoreCase(request.nombre().trim())) {
            throw new IllegalArgumentException("Ya existe un curso registrado con el nombre: " + request.nombre());
        }
    }

    private void validarCambioUnico(CursoRequest request, Long id) {
        log.info("Validando cambio en nombre...");

        if (cursoRepository.existsByNombreIgnoreCaseAndIdNot(request.nombre().trim(), id)) {
            throw new IllegalArgumentException("Ya existe un curso registrado con el nombre: " + request.nombre());
        }
    }
}