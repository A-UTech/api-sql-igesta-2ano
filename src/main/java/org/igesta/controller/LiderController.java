package org.igesta.controller;

import org.igesta.service.LiderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/igesta/lideres")
public class LiderController {

    private final LiderService liderService;

    public LiderController(LiderService liderService) {
        this.liderService = liderService;
    }
}
