package com.daniel.escuela.entities;

import com.daniel.escuela.enums.DiaSemana;
import com.daniel.escuela.utils.LocalTimeAttributeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "HORARIOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HORARIO")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_GRUPO", nullable = false)
    private Grupo grupo;

    @Enumerated(EnumType.STRING)
    @Column(name = "DIA", nullable = false)
    private DiaSemana diaSemana;

    @Convert(converter = LocalTimeAttributeConverter.class)
    @Column(name = "HORA_INICIO", nullable = false, length = 5)
    private LocalTime horaInicio;

    @Convert(converter = LocalTimeAttributeConverter.class)
    @Column(name = "HORA_FIN", nullable = false, length = 5)
    private LocalTime horaFin;

    public void actualizar(Grupo grupo, DiaSemana diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        this.grupo = grupo;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
}