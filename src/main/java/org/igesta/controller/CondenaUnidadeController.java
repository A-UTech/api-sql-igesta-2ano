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
@RequestMapping("/igesta/admin/condena-unidade")
public class CondenaUnidadeController implements CondenaUnidadeOpenApi {

    @Autowired
    private CondenaUnidadeService service;
    @PostMapping("/associar")
    public ResponseEntity<CondenaUnidadeResponseDTO> associar(
            @Validated(OnCreate.class) @RequestBody CondenaUnidadeRequestDTO requestDTO) {

        CondenaUnidadeResponseDTO response = service.associar(requestDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/desassociar")
    public ResponseEntity<Void> desassociar(@RequestBody CondenaUnidadeRequestDTO requestDTO) {

        service.desassociar(requestDTO);

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