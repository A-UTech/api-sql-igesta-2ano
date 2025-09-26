package org.igesta.controller;

import jakarta.validation.groups.Default;

import org.igesta.dto.GestorRequestDTO;
import org.igesta.dto.GestorResponseDTO;
import org.igesta.model.Gestor;
import org.igesta.service.GestorService;
import org.igesta.validation.OnCreate;
import org.igesta.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/igesta/gestores")
public class GestorController {

    private final GestorService gestorService;

    public GestorController(GestorService gestorService) {
        this.gestorService = gestorService;
    }

    @GetMapping("/selecionar")
    public ResponseEntity<List<Gestor>> listarTodosGestores() {
        List<Gestor> listaGestores = gestorService.buscarTodosGestores();
        return ResponseEntity.ok(listaGestores);
    }

    @GetMapping("/selecionarPorId/{id}")
    public ResponseEntity<Object> buscarGestorPorId(@PathVariable Long id) {
        GestorResponseDTO gestor = gestorService.buscarGestorPorId(id);
        return ResponseEntity.ok(gestor);
    }

    @GetMapping("/selecionarPorNome/{nome}")
    public ResponseEntity<Object> buscarGestorPorNome(@PathVariable String nome) {
        List<GestorResponseDTO> gestores = gestorService.buscarGestorPorNome(nome);
        return ResponseEntity.ok(gestores);
    }

    @GetMapping("/selecionarPorCpf/{cpf}")
    public ResponseEntity<Object> buscarGestorPorCpf(@PathVariable String cpf) {
        List<GestorResponseDTO> gestores = gestorService.buscarGestorPorCpf(cpf);
        return ResponseEntity.ok(gestores);
    }

    @GetMapping("/selecionarPorParteDoNome/{nome}")
    public ResponseEntity<Object> buscarGestorPorParteDoNome(@PathVariable String nome) {
        String nomeBuscado = "%" + nome + "%";
        List<GestorResponseDTO> gestores = gestorService.buscarGestorPorParteDoNome(nomeBuscado);
        return ResponseEntity.ok(gestores);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Object> inserirGestor(@RequestBody
                                                 @Validated({OnCreate.class, Default.class}) GestorRequestDTO dto) {
        GestorResponseDTO gestorSalvo = gestorService.adicionarGestor(dto);
        return ResponseEntity.ok(gestorSalvo);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> excluirGestor(@PathVariable Long id) {
        GestorResponseDTO gestorExcluido = gestorService.removerGestor(id);
        return ResponseEntity.ok(gestorExcluido);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarGestor(@PathVariable Long id,
                                                   @Validated({OnCreate.class, Default.class}) @RequestBody GestorRequestDTO dto) {
        GestorResponseDTO gestorSalvo = gestorService.atualizarGestor(id, dto);
        return ResponseEntity.ok(gestorSalvo);
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarGestorParcial(@PathVariable Long id, @RequestBody
    @Validated({OnPatch.class, Default.class}) GestorRequestDTO atualizacao) {
        GestorResponseDTO gestorAtualizado = gestorService.atualizarGestorParcial(id, atualizacao);
        return ResponseEntity.ok(gestorAtualizado);
    }
}
