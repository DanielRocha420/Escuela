package com.daniel.escuela.services.grupos;

import com.daniel.escuela.dto.grupo.GrupoRequest;
import com.daniel.escuela.dto.grupo.GrupoResponse;
import com.daniel.escuela.entities.Aula;
import com.daniel.escuela.entities.Curso;
import com.daniel.escuela.entities.Grupo;
import com.daniel.escuela.entities.Maestro;
import com.daniel.escuela.mappers.GrupoMapper;
import com.daniel.escuela.repositories.AulaRepository;
import com.daniel.escuela.repositories.CursoRepository;
import com.daniel.escuela.repositories.GrupoRepository;
import com.daniel.escuela.repositories.MaestroRepository;
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
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository grupoRepository;
    private final CursoRepository cursoRepository;
    private final MaestroRepository maestroRepository;
    private final AulaRepository aulaRepository;
    private final GrupoMapper grupoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GrupoResponse> listar() {
        log.info("Listando grupos...");
        return grupoRepository.findAll().stream()
                .map(grupoMapper::entidadAResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GrupoResponse obtenerPorId(Long id) {
        Grupo grupo = obtenerGrupo(id);
        return grupoMapper.entidadAResponse(obtenerGrupo(id));
    }

    @Override
    public GrupoResponse registrar(GrupoRequest request) {
        log.info("Registrando nuevo grupo...");

        validarUnicidad(request, null);

        Curso curso = ServiceUtils.obtenerEntidadException(cursoRepository, request.idCurso(), Curso.class);
        Maestro maestro = ServiceUtils.obtenerEntidadException(maestroRepository, request.idMaestro(), Maestro.class);
        Aula aula = ServiceUtils.obtenerEntidadException(aulaRepository, request.idAula(), Aula.class);

        Grupo grupo = Grupo.builder()
                .curso(curso)
                .maestro(maestro)
                .aula(aula)
                .periodo(request.periodo().trim())
                .build();

        grupoRepository.save(grupo);
        log.info("Grupo registrado con id: {}", grupo.getId());

        return grupoMapper.entidadAResponse(grupo);
    }

    @Override
    public GrupoResponse actualizar(GrupoRequest request, Long id) {
        Grupo grupoExistente = obtenerGrupo(id);
        log.info("Actualizando grupo con id: {}", id);

        validarUnicidad(request, grupoExistente);

        Curso curso = ServiceUtils.obtenerEntidadException(cursoRepository, request.idCurso(), Curso.class);
        Maestro maestro = ServiceUtils.obtenerEntidadException(maestroRepository, request.idMaestro(), Maestro.class);
        Aula aula = ServiceUtils.obtenerEntidadException(aulaRepository, request.idAula(), Aula.class);

        Grupo grupo = Grupo.builder()
                .id(grupoExistente.getId())
                .curso(curso)
                .maestro(maestro)
                .aula(aula)
                .periodo(request.periodo().trim())
                .inscripciones(grupoExistente.getInscripciones())
                .horarios(grupoExistente.getHorarios())
                .build();

        grupoRepository.save(grupo);
        log.info("Grupo con id {} actualizado correctamente", id);

        return grupoMapper.entidadAResponse(grupo);
    }

    @Override
    public void eliminar(Long id) {
        Grupo grupo = obtenerGrupo(id);
        log.info("Eliminando grupo con id: {}", id);

        if (grupo.getInscripciones() != null && !grupo.getInscripciones().isEmpty()) {
            throw new IllegalArgumentException("No se puede eliminar un grupo que tiene inscripciones asociadas");
        }

        if (grupo.getHorarios() != null && !grupo.getHorarios().isEmpty()) {
            throw new IllegalArgumentException("No se puede eliminar un grupo que tiene horarios asociados");
        }

        grupoRepository.delete(grupo);
        log.info("Grupo con id {} eliminado correctamente", id);
    }

    private Grupo obtenerGrupo(Long id) {
        return ServiceUtils.obtenerEntidadException(grupoRepository, id, Grupo.class);
    }

    private void validarUnicidad(GrupoRequest request, Grupo grupoExistente) {
        if (grupoExistente != null) {
            boolean mismoCurso = grupoExistente.getCurso().getId().equals(request.idCurso());
            boolean mismoMaestro = grupoExistente.getMaestro().getId().equals(request.idMaestro());
            boolean mismaAula = grupoExistente.getAula().getId().equals(request.idAula());
            boolean mismoPeriodo = grupoExistente.getPeriodo().equalsIgnoreCase(request.periodo().trim());

            if (mismoCurso && mismoMaestro && mismaAula && mismoPeriodo) {
                return;
            }
        }

        if (grupoRepository.existsByCursoIdAndMaestroIdAndAulaIdAndPeriodo(
                request.idCurso(), request.idMaestro(), request.idAula(), request.periodo().trim())) {
            throw new IllegalArgumentException("Ya existe un grupo registrado con la misma combinación de Curso, Maestro, Aula y Periodo");
        }
    }
}