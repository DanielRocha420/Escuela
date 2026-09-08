package com.daniel.escuela.entities;


import com.daniel.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MAESTROS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Maestro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAESTRO")
    private Long id;

    @Column(name = "NOMBRE", length = 50, nullable = false)
    private String nombre;

    @Column(name = "APELLIDO_PATERNO", length = 50, nullable = false)
    private String apPaterno;

    @Column(name = "APELLIDO_MATERNO", length = 50, nullable = false)
    private String apMaterno;

    @Column(name = "EMAIL", length = 100,unique = true, nullable = false)
    private String email;

    @Column(name = "TELEFONO", length = 10,unique = true, nullable = false)
    private String telefono;

    @Builder.Default
    @OneToMany(mappedBy = "maestro", fetch = FetchType.LAZY)//optimizar rendimiento
    private List<Grupo> grupos = new ArrayList<>();

    public void validarDatos(String nombre, String apPaterno, String apMaterno, String email, String telefono){
        StringCustomUtils.validarTamanio(nombre, 5, 50, "El nombre del maestro es requerido y debe tener entre 5 y 50 caracteres");
        StringCustomUtils.validarTamanio(apPaterno,5,50, "El apellido paterno del maestro es requerido y debe tener entre 5 y 50 caracteres");
        StringCustomUtils.validarTamanio(apMaterno,5,50,"El apellido materno del maestro es requerido y debe tener entre 5 y 50 caracteres");
        StringCustomUtils.validarTamanio(email,5,100,"El email del maestro es requerido y debe tener entre 5 y 100 caracteres");
        StringCustomUtils.validarTamanio(telefono,10,10,"El telefono del maestro es requerido y debe tener 10 caracteres");
    }

    public void actualizar(String nombre, String apPaterno, String apMaterno, String email, String telefono){
        validarDatos(nombre, apPaterno, apMaterno, email, telefono);

        this.nombre = nombre.trim();
        this.apPaterno = apPaterno.trim();
        this.apMaterno = apMaterno.trim();
        this.email = email.trim();
        this.telefono = telefono.trim();
    }
}
