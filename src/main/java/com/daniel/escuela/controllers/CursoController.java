package com.daniel.escuela.controllers;

import com.daniel.escuela.dto.cursos.CursoRequest;
import com.daniel.escuela.dto.cursos.CursoResponse;
import com.daniel.escuela.services.cursos.CursoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "Catálogo de cursos disponibles en la escuela")
public class CursoController extends CrudController<CursoRequest, CursoResponse, CursoService> {

    public CursoController(CursoService service) {
        super(service);
    }
}