package com.daniel.escuela.controllers;

import com.daniel.escuela.dto.aula.AulaRequest;
import com.daniel.escuela.dto.aula.AulaResponse;
import com.daniel.escuela.services.aulas.AulaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aulas")
@Tag(name = "AULAS", description = "Catalogo de aulas disponibles en la escuela")
public class AulaController extends CrudController<AulaRequest, AulaResponse, AulaService>{

    public AulaController(AulaService service) {
        super(service);
    }
}
