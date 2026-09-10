package com.daniel.escuela.controllers;

import com.daniel.escuela.dto.calificacion.CalificacionRequest;
import com.daniel.escuela.dto.calificacion.CalificacionResponse;
import com.daniel.escuela.services.calificaciones.CalificacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calificaciones")
@Tag(name = "Calificaciones", description = "Administración de calificaciones de alumnos")
public class CalificacionController extends CrudController<CalificacionRequest, CalificacionResponse, CalificacionService> {

    public CalificacionController(CalificacionService service) {
        super(service);
    }
}