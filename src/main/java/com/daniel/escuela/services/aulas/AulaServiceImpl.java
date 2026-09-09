package com.daniel.escuela.services.aulas;

import com.daniel.escuela.dto.aula.AulaRequest;
import com.daniel.escuela.dto.aula.AulaResponse;
import com.daniel.escuela.entities.Aula;
import com.daniel.escuela.exceptions.EntidadRelacionadaException;
import com.daniel.escuela.mappers.AulaMapper;
import com.daniel.escuela.repositories.AulaRepository;
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
public class AulaServiceImpl implements AulaService {

    private final AulaRepository aulaRepository;
    private final GrupoRepository grupoRepository;
    private final AulaMapper aulaMapper;

    @Override
    @Transactional(readOnly = true)
    public List<AulaResponse> listar() {
        log.info("Listando todas las Aulas");
        return aulaRepository.findAll().stream()
                .map(aulaMapper::entidadAResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AulaResponse obtenerPorId(Long id) {
        return aulaMapper.entidadAResponse(obtenerAula(id));
    }

    @Override
    public AulaResponse registrar(AulaRequest request) {
        log.info("Registrando nueva aula...");
        validarDatosUnicos(request);

        Aula aula = aulaMapper.requestAEntidad(request);

        aulaRepository.save(aula);
        log.info("Nueva aula {} registrada", aula.getNombre());
        return aulaMapper.entidadAResponse(aula);
    }

    @Override
    public AulaResponse actualizar(AulaRequest request, Long id) {
        Aula aula = obtenerAula(id);
        log.info("Actualizando aula con id: {}", id);

        validarCambioUnico(request, id);

        aula.actualizar(
                request.nombre(),
                request.capacidad()
        );

        log.info("Aula con id {} actualizada correctamente", id);
        return aulaMapper.entidadAResponse(aula);
    }

    @Override
    public void eliminar(Long id) {
        Aula aula = obtenerAula(id);
        log.info("Eliminando aula con id: {}", id);

        if (grupoRepository.existsByAulaId(id)) {
            throw new EntidadRelacionadaException("No se puede eliminar el aula ya que tiene grupos asignados");
        }

        aulaRepository.delete(aula);
        log.info("Aula con id {} eliminada correctamente", id);
    }

    private Aula obtenerAula(Long id) {
        return ServiceUtils.obtenerEntidadException(aulaRepository, id, Aula.class);
    }

    private void validarDatosUnicos(AulaRequest request) {
        log.info("Validando nombre unico...");

        if (aulaRepository.existsByNombreIgnoreCase(request.nombre().trim())) {
            throw new IllegalArgumentException("Ya existe un aula registrada con el nombre: " + request.nombre());
        }
    }

    private void validarCambioUnico(AulaRequest request, Long id) {
        log.info("Validando cambio en nombre...");

        if (aulaRepository.existsByNombreIgnoreCaseAndIdNot(request.nombre().trim(), id)) {
            throw new IllegalArgumentException("Ya existe un aula registrada con el nombre: " + request.nombre());
        }
    }
}