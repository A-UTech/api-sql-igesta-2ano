package org.igesta.controller;

import org.igesta.dto.TipoUsuarioRequestDTO;
import org.igesta.dto.TipoUsuarioResponseDTO;
import org.igesta.openapi.TipoUsuarioOpenApi;
import org.igesta.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.igesta.validation.OnCreate;

@RestController
@RequestMapping("/igesta/admin/tipo-usuario")
public class TipoUsuarioController implements TipoUsuarioOpenApi {

    private final TipoUsuarioService tipoUsuarioService;

    @Autowired
    public TipoUsuarioController(TipoUsuarioService tipoUsuarioService) {
        this.tipoUsuarioService = tipoUsuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<TipoUsuarioResponseDTO> login(
            @Validated(OnCreate.class) @RequestBody TipoUsuarioRequestDTO request) {

        try {
            TipoUsuarioResponseDTO resultado = tipoUsuarioService.realizarLogin(
                    request.getEmailCnpj(),
                    request.getSenha()
            );

            return ResponseEntity.ok(resultado);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
    }
}