package org.igesta.controller;

import org.igesta.service.CondenaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/igesta/condenas")
public class CondenaController {

    private final CondenaService condenaService;

    public CondenaController(CondenaService condenaService) {
        this.condenaService = condenaService;
    }
}
