package com.daniel.escuela.controllers;

import com.daniel.escuela.dto.grupo.GrupoRequest;
import com.daniel.escuela.dto.grupo.GrupoResponse;
import com.daniel.escuela.services.grupos.GrupoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grupos")
@Tag(name = "Grupos", description = "Administración de grupos académicos")
public class GrupoController extends CrudController<GrupoRequest, GrupoResponse, GrupoService> {

    public GrupoController(GrupoService service) {
        super(service);
    }
}