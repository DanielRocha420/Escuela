package com.daniel.escuela.repositories;

import com.daniel.escuela.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Búsqueda insensible a mayúsculas/minúsculas
    Optional<Curso> findByNombreIgnoreCase(String nombre);

    // Validaciones de unicidad ignorando mayúsculas/minúsculas
    boolean existsByNombreIgnoreCase(String nombre);

    // Validación de unicidad ignorando el ID propio (para actualización)
    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Long id);

}