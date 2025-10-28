package org.igesta.controller;

import jakarta.validation.groups.Default;
import org.igesta.dto.AdminRequestDTO;
import org.igesta.dto.AdminResponseDTO;
import org.igesta.model.Admin;
import org.igesta.openapi.AdminOpenApi;
import org.igesta.service.AdminService;
import org.igesta.validation.OnCreate;
import org.igesta.validation.OnPatch;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/igesta/admin/admins")
public class AdminController implements AdminOpenApi {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/selecionar")
    public ResponseEntity<List<Admin>> buscarAdmins() {
        List<Admin> listaAdmins = adminService.buscarAdmins();
        return ResponseEntity.ok(listaAdmins);
    }

    @GetMapping("/selecionarPorId/{id}")
    public ResponseEntity<Object> buscarAdminPorId(@PathVariable Long id) {
        AdminResponseDTO admin = adminService.buscarAdminPorId(id);
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/selecionarPorNome/{nome}")
    public ResponseEntity<Object> buscarAdminPorNome(@PathVariable String nome) {
        List<AdminResponseDTO> admins = adminService.buscarAdminPorNome(nome);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/selecionarPorTipo/{email}")
    public ResponseEntity<Object> buscarAdminPorEmail(@PathVariable String email) {
        List<AdminResponseDTO> admins = adminService.buscarAdminPorEmail(email);
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/selecionarPorParteDoNome/{nome}")
    public ResponseEntity<Object> buscarCondenaPorParteDoNome(@PathVariable String nome) {
        String nomeBuscado = "%" + nome + "%";
        List<AdminResponseDTO> admins = adminService.buscarAdminPorParteDoNome(nomeBuscado);
        return ResponseEntity.ok(admins);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Object> inserirAdmin(@RequestBody
                                                 @Validated({OnCreate.class, Default.class}) AdminRequestDTO dto) {
        AdminResponseDTO amdinSalvo = adminService.adicionarAdmin(dto);
        return ResponseEntity.ok(amdinSalvo);
    }


    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Object> excluirAdmin(@PathVariable Long id) {
        AdminResponseDTO adminExcluido = adminService.removerAdmin(id);
        return ResponseEntity.ok(adminExcluido);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarAdmin(@PathVariable Long id,
                                                   @Validated({OnCreate.class, Default.class}) @RequestBody AdminRequestDTO dto) {
        AdminResponseDTO adminSalvo = adminService.atualizarAdmin(id, dto);
        return ResponseEntity.ok(adminSalvo);
    }

    @PatchMapping("/atualizarParcial/{id}")
    public ResponseEntity<?> atualizarAdminParcial(@PathVariable Long id, @RequestBody
    @Validated({OnPatch.class, Default.class}) AdminRequestDTO atualizacao) {
        AdminResponseDTO adminAtualizado = adminService.atualizarAdminParcial(id, atualizacao);
        return ResponseEntity.ok(adminAtualizado);
    }
}
