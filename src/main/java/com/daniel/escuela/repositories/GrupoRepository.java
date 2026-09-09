package com.daniel.escuela.repositories;

import com.daniel.escuela.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    boolean existsByMaestroId(Long idMaestro);

    boolean existsByCursoId(Long idCurso);

    boolean existsByAulaId(Long idAula);
}
