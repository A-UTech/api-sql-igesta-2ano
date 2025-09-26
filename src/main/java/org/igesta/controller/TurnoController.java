package org.igesta.controller;

import jakarta.validation.groups.Default;
import org.igesta.dto.PlanoRequestDTO;
import org.igesta.dto.PlanoResponseDTO;
import org.igesta.dto.TurnoRequestDTO;
import org.igesta.dto.TurnoResponseDTO;
import org.igesta.model.Turno;
import org.igesta.service.TurnoService;
import org.igesta.validation.OnCreate;
import org.igesta.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/igesta/turnos")
public class TurnoController {

    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping("/selecionar")
    public ResponseEntity<List<Turno>> listarTurno() {
        List<Turno> turnos = turnoService.listarTodos();
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/selecionarPorId/{id}")
    public ResponseEntity<TurnoResponseDTO> buscarTurnoPorId(@PathVariable Long id) {
        TurnoResponseDTO turno = turnoService.buscarTurnoPorId(id);
        return ResponseEntity.ok(turno);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Object> inserirPlano(@RequestBody
                                               @Validated({OnCreate.class, Default.class}) TurnoRequestDTO dto) {
        TurnoResponseDTO turno = turnoService.inserirTurno(dto);
        return ResponseEntity.ok(turno);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<TurnoResponseDTO> excluirTurno(@PathVariable Long id) {
        TurnoResponseDTO turno = turnoService.removerTurno(id);
        return ResponseEntity.ok(turno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarProduto(@PathVariable Long id,
                                                   @Validated({OnCreate.class, Default.class}) @RequestBody TurnoRequestDTO dto) {
        TurnoResponseDTO turno = turnoService.atualizarTurno(id, dto);
        return ResponseEntity.ok(turno);
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarTurnoParcial(@PathVariable Long id, @RequestBody
    @Validated({OnPatch.class, Default.class}) TurnoRequestDTO dto) {
        TurnoResponseDTO turnoAtualizado = turnoService.atualizarTurnoParcial(id, dto);
        return ResponseEntity.ok(turnoAtualizado);
    }


}
