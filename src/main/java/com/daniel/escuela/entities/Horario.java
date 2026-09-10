package com.daniel.escuela.entities;

import com.daniel.escuela.enums.DiaSemana;
import com.daniel.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HORARIOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
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

    @Column(name = "HORA_INICIO", length = 5, nullable = false)
    private String horaInicio;

    @Column(name = "HORA_FIN", length = 5, nullable = false)
    private String horaFin;

    private void validarDatos(Grupo grupo, DiaSemana diaSemana, String horaInicio, String horaFin) {
        if (grupo == null) {
            throw new IllegalArgumentException("El grupo es requerido");
        }
        if (diaSemana == null) {
            throw new IllegalArgumentException("El dia de la semana es requerido");
        }
        StringCustomUtils.validarTamanio(horaInicio, 5,5,"La hora de inicio debe tener el formato HH:mm");
        StringCustomUtils.validarTamanio(horaFin,5,5,"La hora de fin debe tener el formato HH:mm");
    }

    public void actualizar(Grupo grupo, DiaSemana diaSemana, String horaInicio, String horaFin) {
        validarDatos(grupo, diaSemana, horaInicio, horaFin);
        this.grupo = grupo;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
}
