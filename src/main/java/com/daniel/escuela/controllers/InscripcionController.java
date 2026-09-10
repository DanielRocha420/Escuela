package com.daniel.escuela.controllers;

import com.daniel.escuela.dto.inscripcion.InscripcionRequest;
import com.daniel.escuela.dto.inscripcion.InscripcionResponse;
import com.daniel.escuela.services.inscripciones.InscripcionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inscripciones")
@Tag(name = "API INSRCRIPCIONES", description = "Catalogo de inscripciones disponibles en la escuela")
public class InscripcionController extends CrudController<InscripcionRequest, InscripcionResponse, InscripcionService> {

    public InscripcionController(InscripcionService service) {
        super(service);
    }
}
