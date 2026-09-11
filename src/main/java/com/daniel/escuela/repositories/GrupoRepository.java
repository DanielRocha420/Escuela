package com.daniel.escuela.repositories;

import com.daniel.escuela.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    @Query("SELECT DISTINCT g FROM Grupo g " +
            "LEFT JOIN FETCH g.curso " +
            "LEFT JOIN FETCH g.maestro " +
            "LEFT JOIN FETCH g.aula " +
            "LEFT JOIN FETCH g.horarios")
    List<Grupo> findAllWithRelaciones();

    boolean existsByMaestroId(Long idMaestro);

    boolean existsByCursoId(Long idCurso);

    boolean existsByAulaId(Long idAula);

    boolean existsByCursoIdAndMaestroIdAndAulaIdAndPeriodo(Long idCurso, Long idMaestro, Long idAula, String periodo);
}