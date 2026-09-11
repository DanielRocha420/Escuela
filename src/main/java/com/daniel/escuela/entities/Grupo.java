package com.daniel.escuela.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "GRUPOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GRUPO")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CURSO", nullable = false)
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MAESTRO", nullable = false)
    private Maestro maestro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AULA", nullable = false)
    private Aula aula;

    @Column(name = "PERIODO", nullable = false)
    private String periodo;

    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY)
    private List<Horario> horarios;

    @OneToMany(mappedBy = "grupo", fetch = FetchType.LAZY)
    private List<Inscripcion> inscripciones;
}