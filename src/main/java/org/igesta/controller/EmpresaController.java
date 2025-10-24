package org.igesta.controller;

import jakarta.validation.groups.Default;
import org.igesta.dto.EmpresaRequestDTO;
import org.igesta.dto.EmpresaResponseDTO;
import org.igesta.model.Empresa;
import org.igesta.openapi.EmpresaOpenApi;
import org.igesta.service.EmpresaService;
import org.igesta.validation.OnCreate;
import org.igesta.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/igesta/admin/empresas")
public class EmpresaController implements EmpresaOpenApi {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/selecionar")
    public ResponseEntity<List<Empresa>> buscarEmpresas() {
        List<Empresa> listaEmpresas = empresaService.buscarEmpresas();
        return ResponseEntity.ok(listaEmpresas);
    }

    @GetMapping("/selecionarPorId/{id}")
    public ResponseEntity<Object> buscarEmpresaPorId(@PathVariable Long id) {
        EmpresaResponseDTO empresa = empresaService.buscarEmpresaPorId(id);
        return ResponseEntity.ok(empresa);
    }

    @GetMapping("/selecionarPorNome/{nome}")
    public ResponseEntity<Object> buscarEmpresaPorNome(@PathVariable String nome) {
        List<EmpresaResponseDTO> empresas = empresaService.buscarEmpresaPorNome(nome);
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/selecionarPorCnpj/{cnpj}")
    public ResponseEntity<Object> buscarEmpresaPorCnpj(@PathVariable String cnpj) {
        List<EmpresaResponseDTO> empresas = empresaService.buscarEmpresaPorCnpj(cnpj);
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/selecionarPorParteDoNome/{nome}")
    public ResponseEntity<Object> buscarEmpresaPorParteDoNome(@PathVariable String nome) {
        String nomeBuscado = "%" + nome + "%";
        List<EmpresaResponseDTO> empresas = empresaService.buscarEmpresaPorParteDoNome(nomeBuscado);
        return ResponseEntity.ok(empresas);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Object> inserirEmpresa(@RequestBody
                                                 @Validated({OnCreate.class, Default.class}) EmpresaRequestDTO dto) {
        EmpresaResponseDTO empresaSalva = empresaService.adicionarEmpresa(dto);
        return ResponseEntity.ok(empresaSalva);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> excluirEmpresa(@PathVariable Long id) {
        EmpresaResponseDTO empresaExcluida = empresaService.removerEmpresa(id);
        return ResponseEntity.ok(empresaExcluida);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarEmpresa(@PathVariable Long id,
                                                   @Validated({OnCreate.class, Default.class}) @RequestBody EmpresaRequestDTO dto) {
        EmpresaResponseDTO empresaSalva = empresaService.atualizarEmpresa(id, dto);
        return ResponseEntity.ok(empresaSalva);
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarEmpresaParcial(@PathVariable Long id, @RequestBody
    @Validated({OnPatch.class, Default.class}) EmpresaRequestDTO atualizacao) {
        EmpresaResponseDTO empresaAtualizada = empresaService.atualizarEmpresaParcial(id, atualizacao);
        return ResponseEntity.ok(empresaAtualizada);
    }
}
