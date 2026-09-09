package com.daniel.escuela.controllers;

import com.daniel.escuela.dto.alumno.AlumnoRequest;
import com.daniel.escuela.dto.alumno.AlumnoResponse;
import com.daniel.escuela.services.alumnos.AlumnoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alumnos")
@Tag(name = "API Alumnos", description = "Metodos para gestion  de alumnos")
public class AlumnoController extends CrudController<AlumnoRequest, AlumnoResponse, AlumnoService>{

    public AlumnoController(AlumnoService service) {
        super(service);
    }

}
