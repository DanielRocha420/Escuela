package com.daniel.escuela.repositories;

import com.daniel.escuela.entities.Horario;
import com.daniel.escuela.enums.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    @Query("SELECT DISTINCT h FROM Horario h " +
            "LEFT JOIN FETCH h.grupo g " +
            "LEFT JOIN FETCH g.curso " +
            "LEFT JOIN FETCH g.maestro " +
            "LEFT JOIN FETCH g.aula")
    List<Horario> findAllWithRelaciones();

    @Query("""
        SELECT COUNT(h) > 0 
        FROM Horario h 
        WHERE h.grupo.aula.id = :idAula 
          AND h.grupo.periodo = :periodo 
          AND h.diaSemana = :diaSemana 
          AND (:idHorario IS NULL OR h.id <> :idHorario)
          AND :horaInicio < h.horaFin 
          AND :horaFin > h.horaInicio
    """)
    boolean existeEmpalmeAula(
            @Param("idAula") Long idAula,
            @Param("periodo") String periodo,
            @Param("diaSemana") DiaSemana diaSemana,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFin") LocalTime horaFin,
            @Param("idHorario") Long idHorario
    );

    @Query("""
        SELECT COUNT(h) > 0 
        FROM Horario h 
        WHERE h.grupo.maestro.id = :idMaestro 
          AND h.grupo.periodo = :periodo 
          AND h.diaSemana = :diaSemana 
          AND (:idHorario IS NULL OR h.id <> :idHorario)
          AND :horaInicio < h.horaFin 
          AND :horaFin > h.horaInicio
    """)
    boolean existeEmpalmeMaestro(
            @Param("idMaestro") Long idMaestro,
            @Param("periodo") String periodo,
            @Param("diaSemana") DiaSemana diaSemana,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFin") LocalTime horaFin,
            @Param("idHorario") Long idHorario
    );
}