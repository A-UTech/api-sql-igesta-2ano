package org.igesta.controller;

import org.igesta.service.GestoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/igesta/gestores")
public class GestorController {

    private final GestoService gestoService;

    public GestorController(GestoService gestoService) {
        this.gestoService = gestoService;
    }
}
