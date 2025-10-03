package org.igesta.controller;

import jakarta.validation.groups.Default;
import org.igesta.dto.CondenaRequestDTO;
import org.igesta.dto.CondenaResponseDTO;
import org.igesta.dto.LiderRequestDTO;
import org.igesta.dto.LiderResponseDTO;
import org.igesta.model.Condena;
import org.igesta.model.Lider;
import org.igesta.openapi.LiderOpenApi;
import org.igesta.service.CondenaService;
import org.igesta.service.LiderService;
import org.igesta.validation.OnCreate;
import org.igesta.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/igesta/lideres")
public class LiderController implements LiderOpenApi {

    private final LiderService liderService;

    public LiderController(LiderService liderService) {
        this.liderService = liderService;
    }

    @GetMapping("/selecionar")
    public ResponseEntity<List<Lider>> listarTodosLideres() {
        List<Lider> listaLider = liderService.listarLideres();
        return ResponseEntity.ok(listaLider);
    }

    @GetMapping("/selecionarPorId/{id}")
    public ResponseEntity<LiderResponseDTO> buscarLiderPorId(@PathVariable Long id) {
        LiderResponseDTO lider = liderService.buscarLiderPorId(id);
        return ResponseEntity.ok(lider);
    }

    @GetMapping("/selecionarPorNome/{nome}")
    public ResponseEntity<Object> buscarLiderPorNome(@PathVariable String nome) {
        List<LiderResponseDTO> lider = liderService.buscarLiderPorNome(nome);
        return ResponseEntity.ok(lider);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Object> inserirLider(@RequestBody
                                                 @Validated({OnCreate.class, Default.class}) LiderRequestDTO dto) {
        LiderResponseDTO lider = liderService.inserirLider(dto);
        return ResponseEntity.ok(lider);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<LiderResponseDTO> excluirLider(@PathVariable Long id) {
        LiderResponseDTO lider = liderService.removerLider(id);
        return ResponseEntity.ok(lider);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarLider(@PathVariable Long id,
                                                   @Validated({OnCreate.class, Default.class}) @RequestBody LiderRequestDTO dto) {
        LiderResponseDTO lider = liderService.atualizarlider(id, dto);
        return ResponseEntity.ok(lider);
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarLiderParcial(@PathVariable Long id, @RequestBody
    @Validated({OnPatch.class, Default.class}) LiderRequestDTO dto) {
        LiderResponseDTO liderAtualizado = liderService.atualizarLiderParcial(id, dto);
        return ResponseEntity.ok(liderAtualizado);
    }
}
