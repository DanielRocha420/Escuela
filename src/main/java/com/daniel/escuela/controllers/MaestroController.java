package com.daniel.escuela.controllers;

import com.daniel.escuela.dto.maestro.MaestroRequest;
import com.daniel.escuela.dto.maestro.MaestroResponse;
import com.daniel.escuela.services.maestros.MaestroService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maestros")
@Tag(name = "API Maestros", description = "Metodos para gestion de maestros")
public class MaestroController extends CrudController<MaestroRequest, MaestroResponse, MaestroService>{

    public MaestroController(MaestroService service){
        super(service);
    }

}
