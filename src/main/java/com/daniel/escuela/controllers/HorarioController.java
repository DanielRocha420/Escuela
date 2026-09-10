package com.daniel.escuela.controllers;

import com.daniel.escuela.dto.horario.HorarioRequest;
import com.daniel.escuela.dto.horario.HorarioResponse;
import com.daniel.escuela.entities.Horario;
import com.daniel.escuela.services.horarios.HorarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/horarios")
@Tag(name = "Horarios", description = "Catalogo de horarios disponibles en la escuela")
public class HorarioController extends CrudController<HorarioRequest, HorarioResponse, HorarioService>{

    public HorarioController(HorarioService service){
        super(service);
    }
}
