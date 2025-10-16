package org.igesta.controller;

import jakarta.validation.groups.Default;
import org.igesta.dto.PlanoRequestDTO;
import org.igesta.dto.PlanoResponseDTO;
import org.igesta.model.Plano;
import org.igesta.openapi.PlanoOpenApi;
import org.igesta.service.PlanoService;
import org.igesta.validation.OnCreate;
import org.igesta.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/igesta/planos")
public class PlanoController implements PlanoOpenApi {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @GetMapping("/selecionar")
    public ResponseEntity<List<Plano>> buscarPlanos() {
        List<Plano> planos = planoService.listarTodos();
        return ResponseEntity.ok(planos);
    }

    @GetMapping("/selecionarPorId/{id}")
    public ResponseEntity<PlanoResponseDTO> buscarPlanoPorId(@PathVariable Long id) {
        PlanoResponseDTO plano = planoService.buscarPlanoPorId(id);
        return ResponseEntity.ok(plano);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Object> inserirPlano(@RequestBody
                                               @Validated({OnCreate.class, Default.class}) PlanoRequestDTO dto) {
        PlanoResponseDTO plano = planoService.inserirPlano(dto);
        return ResponseEntity.ok(plano);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<PlanoResponseDTO> excluirPlano(@PathVariable Long id) {
        PlanoResponseDTO plano = planoService.removerPlano(id);
        return ResponseEntity.ok(plano);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarPlano(@PathVariable Long id,
                                                   @Validated({OnCreate.class, Default.class}) @RequestBody PlanoRequestDTO dto) {
        PlanoResponseDTO plano = planoService.atualizarPlano(id, dto);
        return ResponseEntity.ok(plano);
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarPlanoParcial(@PathVariable Long id, @RequestBody
    @Validated({OnPatch.class, Default.class}) PlanoRequestDTO dto) {
        PlanoResponseDTO planoAtualizado = planoService.atualizarPlanoParcial(id, dto);
        return ResponseEntity.ok(planoAtualizado);
    }
}
