package org.igesta.controller;


import jakarta.validation.groups.Default;
import org.igesta.openapi.CondenaOpenApi;
import org.igesta.dto.CondenaRequestDTO;
import org.igesta.dto.CondenaResponseDTO;
import org.igesta.model.Condena;
import org.igesta.service.CondenaService;
import org.igesta.validation.OnCreate;
import org.igesta.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/igesta/condenas")
public class CondenaController implements CondenaOpenApi {

    private final CondenaService condenaService;

    public CondenaController(CondenaService condenaService) {
        this.condenaService = condenaService;
    }

    @GetMapping("/selecionar")
    public ResponseEntity<List<Condena>> listarTodasCondenas() {
        List<Condena> listaCondenas = condenaService.buscarTodasCondenas();
        return ResponseEntity.ok(listaCondenas);
    }

    @GetMapping("/selecionarPorId/{id}")
    public ResponseEntity<Object> buscarCondenaPorId(@PathVariable Long id) {
        CondenaResponseDTO condena = condenaService.buscarCondenaPorId(id);
        return ResponseEntity.ok(condena);
    }

    @GetMapping("/selecionarPorNome/{nome}")
    public ResponseEntity<Object> buscarCondenaPorNome(@PathVariable String nome) {
        List<CondenaResponseDTO> condenas = condenaService.buscarCondenaPorNome(nome);
        return ResponseEntity.ok(condenas);
    }

    @GetMapping("/selecionarPorTipo/{tipo}")
    public ResponseEntity<Object> buscarCondenaPorTipo(@PathVariable String tipo) {
        List<CondenaResponseDTO> condenas = condenaService.buscarCondenaPorTipo(tipo);
        return ResponseEntity.ok(condenas);
    }

    @GetMapping("/selecionarPorParteDoNome/{nome}")
    public ResponseEntity<Object> buscarCondenaPorParteDoNome(@PathVariable String nome) {
        String nomeBuscado = "%" + nome + "%";
        List<CondenaResponseDTO> condenas = condenaService.buscarCondenaPorParteDoNome(nomeBuscado);
        return ResponseEntity.ok(condenas);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Object> inserirCondena(@RequestBody
                                                 @Validated({OnCreate.class, Default.class}) CondenaRequestDTO dto) {
        CondenaResponseDTO condenaSalva = condenaService.adicionarCondena(dto);
        return ResponseEntity.ok(condenaSalva);
    }


    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> excluirCondena(@PathVariable Long id) {
        CondenaResponseDTO condenaExcluida = condenaService.removerCondena(id);
        return ResponseEntity.ok(condenaExcluida);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarCondena(@PathVariable Long id,
                                                   @Validated({OnCreate.class, Default.class}) @RequestBody CondenaRequestDTO dto) {
        CondenaResponseDTO condenaSalva = condenaService.atualizarCondena(id, dto);
        return ResponseEntity.ok(condenaSalva);
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarCondenaParcial(@PathVariable Long id, @RequestBody
    @Validated({OnPatch.class, Default.class}) CondenaRequestDTO atualizacao) {
        CondenaResponseDTO condenaAtualizada = condenaService.atualizarCondenaParcial(id, atualizacao);
        return ResponseEntity.ok(condenaAtualizada);
    }

}
