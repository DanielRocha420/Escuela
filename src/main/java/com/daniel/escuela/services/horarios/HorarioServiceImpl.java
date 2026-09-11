package com.daniel.escuela.services.horarios;

import com.daniel.escuela.dto.horario.HorarioRequest;
import com.daniel.escuela.dto.horario.HorarioResponse;
import com.daniel.escuela.entities.Grupo;
import com.daniel.escuela.entities.Horario;
import com.daniel.escuela.enums.DiaSemana;
import com.daniel.escuela.mappers.HorarioMapper;
import com.daniel.escuela.repositories.GrupoRepository;
import com.daniel.escuela.repositories.HorarioRepository;
import com.daniel.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class HorarioServiceImpl implements HorarioService {

    private final HorarioRepository horarioRepository;
    private final GrupoRepository grupoRepository;
    private final HorarioMapper horarioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<HorarioResponse> listar() {
        log.info("Listando horarios...");
        return horarioRepository.findAllWithRelaciones().stream()
                .map(horarioMapper::entidadAResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public HorarioResponse obtenerPorId(Long id) {
        return horarioMapper.entidadAResponse(obtenerHorario(id));
    }

    @Override
    public HorarioResponse registrar(HorarioRequest request) {
        log.info("Registrando nuevo horario...");

        Grupo grupo = ServiceUtils.obtenerEntidadException(grupoRepository, request.idGrupo(), Grupo.class);
        DiaSemana dia = DiaSemana.valueOf(request.dia().toUpperCase().trim());


        LocalTime inicio = parsearHora(request.horaInicio());
        LocalTime fin = parsearHora(request.horaFin());


        if (!fin.isAfter(inicio)) {
            throw new IllegalArgumentException("La hora de fin debe ser posterior a la hora de inicio");
        }


        validarTraslapes(grupo, dia, inicio, fin, null);

        Horario horario = Horario.builder()
                .grupo(grupo)
                .diaSemana(dia)
                .horaInicio(inicio)
                .horaFin(fin)
                .build();

        horarioRepository.save(horario);
        log.info("Nuevo horario registrado con id: {}", horario.getId());

        return horarioMapper.entidadAResponse(horario);
    }

    @Override
    public HorarioResponse actualizar(HorarioRequest request, Long id) {
        Horario horario = obtenerHorario(id);
        log.info("Actualizando horario con id: {}", id);

        Grupo grupo = ServiceUtils.obtenerEntidadException(grupoRepository, request.idGrupo(), Grupo.class);
        DiaSemana dia = DiaSemana.valueOf(request.dia().toUpperCase().trim());

        LocalTime inicio = parsearHora(request.horaInicio());
        LocalTime fin = parsearHora(request.horaFin());

        if (!fin.isAfter(inicio)) {
            throw new IllegalArgumentException("La hora de fin debe ser posterior a la hora de inicio");
        }


        validarTraslapes(grupo, dia, inicio, fin, id);

        horario.actualizar(grupo, dia, inicio, fin);
        log.info("Horario con id {} actualizado correctamente", id);

        return horarioMapper.entidadAResponse(horario);
    }

    @Override
    public void eliminar(Long id) {
        Horario horario = obtenerHorario(id);
        log.info("Eliminando horario con id: {}", id);
        horarioRepository.delete(horario);
        log.info("Horario con id {} eliminado correctamente", id);
    }

    private Horario obtenerHorario(Long id) {
        return ServiceUtils.obtenerEntidadException(horarioRepository, id, Horario.class);
    }

    private LocalTime parsearHora(String horaTexto) {
        try {
            return LocalTime.parse(horaTexto.trim());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de hora inválido. Debe ser HH:mm (ej. 08:00)");
        }
    }


    private void validarTraslapes(Grupo grupo, DiaSemana dia, LocalTime inicio, LocalTime fin, Long idHorarioActual) {

        if (horarioRepository.existeEmpalmeAula(grupo.getAula().getId(), grupo.getPeriodo(), dia, inicio, fin, idHorarioActual)) {
            throw new IllegalArgumentException("El aula " + grupo.getAula().getNombre() + " ya está ocupada el " + dia + " a esa hora.");
        }
        if (horarioRepository.existeEmpalmeMaestro(grupo.getMaestro().getId(), grupo.getPeriodo(), dia, inicio, fin, idHorarioActual)) {
            throw new IllegalArgumentException("El maestro ya tiene una clase asignada el " + dia + " a esa hora.");
        }
    }
}