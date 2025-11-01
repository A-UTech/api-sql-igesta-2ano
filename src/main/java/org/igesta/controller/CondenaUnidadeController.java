package org.igesta.controller;

import org.igesta.dto.CondenaUnidadeRequestDTO;
import org.igesta.dto.CondenaUnidadeResponseDTO;
import org.igesta.openapi.CondenaUnidadeOpenApi;
import org.igesta.service.CondenaUnidadeService;
import org.igesta.validation.OnCreate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/igesta/user/condena-unidade")
public class CondenaUnidadeController implements CondenaUnidadeOpenApi {

    @Autowired
    private CondenaUnidadeService service;
    @PostMapping("/associar/{unidadeId}/{condenaId}")
    public ResponseEntity<CondenaUnidadeResponseDTO> associar(@PathVariable Long unidadeId, @PathVariable Long condenaId) {

        CondenaUnidadeResponseDTO response = service.associar(unidadeId, condenaId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/desassociar/{idUnidade}/{idCondena}")
    public ResponseEntity<Void> desassociar(@PathVariable Long unidadeId, @PathVariable Long condenaId) {

        service.desassociar(unidadeId, condenaId);

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/unidade/{unidadeId}")
    public ResponseEntity<List<CondenaUnidadeResponseDTO>> buscarPorUnidade(@PathVariable Integer unidadeId) {

        List<CondenaUnidadeResponseDTO> resultados = service.buscarCondenasPorUnidade(unidadeId);

        if (resultados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resultados);
    }
}