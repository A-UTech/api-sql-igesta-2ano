package org.igesta.controller;

import jakarta.validation.groups.Default;
import org.igesta.dto.TurnoRequestDTO;
import org.igesta.dto.TurnoResponseDTO;
import org.igesta.dto.UnidadeRequestDTO;
import org.igesta.dto.UnidadeResponseDTO;
import org.igesta.model.Unidade;
import org.igesta.openapi.UnidadeOpenApi;
import org.igesta.service.UnidadeService;
import org.igesta.validation.OnCreate;
import org.igesta.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/igesta/admin/unidades")
public class UnidadeController implements UnidadeOpenApi {

    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping("/selecionar")
    public ResponseEntity<List<Unidade>> buscarUnidades() {
        List<Unidade> unidades = unidadeService.buscarUnidades();
        return ResponseEntity.ok(unidades);
    }

    @GetMapping("/selecionarPorId/{id}")
    public ResponseEntity<UnidadeResponseDTO> buscarUnidadePorId(@PathVariable Long id) {
        UnidadeResponseDTO unidade = unidadeService.buscarUnidadePorId(id);
        return ResponseEntity.ok(unidade);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Object> inserirUnidade(@RequestBody
                                                 @Validated({OnCreate.class, Default.class}) UnidadeRequestDTO dto) {
        UnidadeResponseDTO unidade= unidadeService.inserirUnidade(dto);
        return ResponseEntity.ok(unidade);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<UnidadeResponseDTO> excluirUnidade(@PathVariable Long id) {
        UnidadeResponseDTO unidade = unidadeService.removerUnidade(id);
        return ResponseEntity.ok(unidade);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarUnidade(@PathVariable Long id,
                                                   @Validated({OnCreate.class, Default.class}) @RequestBody UnidadeRequestDTO dto) {
        UnidadeResponseDTO unidade = unidadeService.atualizarUnidade(id, dto);
        return ResponseEntity.ok(unidade);
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarUnidadeParcial(@PathVariable Long id, @RequestBody
    @Validated({OnPatch.class, Default.class}) UnidadeRequestDTO dto) {
        UnidadeResponseDTO unidadeAtualizado = unidadeService.atualizarUnidadeParcial(id, dto);
        return ResponseEntity.ok(unidadeAtualizado);
    }
}
