package com.daniel.escuela.services.maestros;

import com.daniel.escuela.dto.maestro.MaestroRequest;
import com.daniel.escuela.dto.maestro.MaestroResponse;
import com.daniel.escuela.entities.Maestro;
import com.daniel.escuela.exceptions.EntidadRelacionadaException;
import com.daniel.escuela.mappers.MaestroMapper;
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
public class MaestroServiceImpl implements MaestroService{

    private final MaestroRepository maestroRepository;

    private final GrupoRepository grupoRepository;

    private final MaestroMapper maestroMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MaestroResponse> listar() {
        log.info("Listando todos los maestros");
        return maestroRepository.findAll().stream()
                .map(maestroMapper::entidadAResponse).toList();
    }

    @Override
    public MaestroResponse obtenerPorId(Long id) {
        return maestroMapper.entidadAResponse(obtenerMaestro(id));
    }

    @Override
    public MaestroResponse registrar(MaestroRequest request) {
        log.info("Registrando nuevo maestro...");

        Maestro maestro = maestroMapper.requestAEntidad(request);

        maestroRepository.save(maestro);

        log.info("Nuevo maestro {} registrado", maestro.getNombre());
        return maestroMapper.entidadAResponse(maestro);
    }

    @Override
    public MaestroResponse actualizar(MaestroRequest request, Long id) {

        Maestro maestro = obtenerMaestro(id);
        log.info("Actualizando maestro con id: {}", id);

        validarCambiosUnicos(request, id);

        maestro.actualizar(
                request.nombre(),
                request.apellidoPaterno(),
                request.apellidoMaterno(),
                request.email(),
                request.telefono());

        log.info("Maestro {} actualizado correctamente", maestro.getNombre());
        return maestroMapper.entidadAResponse(maestro);

    }

    @Override
    public void eliminar(Long id) {

        Maestro maestro = obtenerMaestro(id);
        log.info("Eliminando maestro con id: {}", id);

        if (grupoRepository.existsByMaestroId(id))
            throw new EntidadRelacionadaException("No se puede eliminar el maestro ya que tiene grupos asignados");

        maestroRepository.delete(maestro);

        log.info("Maestro {} eliminado correctamente", maestro.getNombre());

    }

    private Maestro obtenerMaestro(Long id) {
        return ServiceUtils.obtenerEntidadException(maestroRepository, id, Maestro.class);
    }

    private void validarDatosUnicos(MaestroRequest request) {
        log.info("Validando email unico...");

        if (maestroRepository.existsByEmailIgnoreCase(request.email().trim()))
            throw new IllegalArgumentException("Ya existe un maestro registrado con este email: " + request.email());

        log.info("Validando telefono unico...");

        if (maestroRepository.existsByTelefono(request.telefono().trim()))
            throw new IllegalArgumentException("Ya existe un maestro registrado con el telefono: " + request.telefono());
    }

    private void validarCambiosUnicos(MaestroRequest request, Long id) {
        log.info("Validando cambio en email unico...");

        if (maestroRepository.existsByEmailIgnoreCaseAndIdNot(request.email().trim(), id))
            throw new IllegalArgumentException("Ya existe un maestro registrado con este email: " + request.email());

        log.info("Validando cambio telefono unico...");

        if (maestroRepository.existsByTelefonoAndIdNot(request.telefono().trim(), id))
            throw new IllegalArgumentException("Ya existe un maestro registrado con el telefono: " + request.telefono());
    }
}
