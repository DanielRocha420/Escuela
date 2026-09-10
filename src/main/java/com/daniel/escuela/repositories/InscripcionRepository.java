package com.daniel.escuela.repositories;

import com.daniel.escuela.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    boolean existsByAlumnoIdAndGrupoId(Long idAlumno, Long idGrupo);
}