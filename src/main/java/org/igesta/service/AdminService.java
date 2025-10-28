package org.igesta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.igesta.dto.AdminRequestDTO;
import org.igesta.dto.AdminResponseDTO;
import org.igesta.model.Admin;
import org.igesta.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final ObjectMapper objectMapper;

    public AdminService(AdminRepository adminRepository, ObjectMapper objectMapper) {
        this.adminRepository = adminRepository;
        this.objectMapper = objectMapper;
    }

    public List<Admin> buscarAdmins() {
        return adminRepository.findAll();
    }

    public AdminResponseDTO buscarAdminPorId(Long id) {
        return objectMapper.convertValue(adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin não encontrado")), AdminResponseDTO.class);
    }

    @Transactional
    public AdminResponseDTO adicionarAdmin(AdminRequestDTO dto){
        Admin admin = objectMapper.convertValue(dto, Admin.class);
        return objectMapper.convertValue(adminRepository.save(admin), AdminResponseDTO.class);
    }

    @Transactional
    public AdminResponseDTO removerAdmin(Long id){
        AdminResponseDTO existente = buscarAdminPorId(id);
        adminRepository.deleteById(id);
        return existente;
    }

    @Transactional
    public AdminResponseDTO atualizarAdmin(Long id, AdminRequestDTO dto) {
        if (!adminRepository.existsById(id)) {
            throw new EntityNotFoundException("Admin não encontrado para atualizar");
        }
        Admin atualizado =objectMapper.convertValue(dto, Admin.class);
        atualizado.setId(id);
        return objectMapper.convertValue(adminRepository.save(atualizado), AdminResponseDTO.class);
    }

    @Transactional
    public AdminResponseDTO atualizarAdminParcial(Long id, AdminRequestDTO dto) {
        if (!adminRepository.existsById(id)) {
            throw new EntityNotFoundException("Admin não encontrado para atualização parcial");
        }
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin não encontrado"));

        Admin atualizacoes =objectMapper.convertValue(dto, Admin.class);

        if (atualizacoes.getNome() != null) {
            String nome = atualizacoes.getNome();
            admin.setNome(nome);
        }

        if (atualizacoes.getEmail() != null) {
            String email =  atualizacoes.getEmail();
            admin.setEmail(email);
        }

        return objectMapper.convertValue(adminRepository.save(admin), AdminResponseDTO.class);
    }

    public List<AdminResponseDTO> buscarAdminPorNome(String nome) {
        List<Admin> admins = adminRepository.findByNome(nome);
        if (admins == null){
            throw new EntityNotFoundException("Admin não encontrado");
        }
        List<AdminResponseDTO> listaResponseDTO = new ArrayList<>();
        for (Admin admin : admins){
            listaResponseDTO.add(objectMapper.convertValue(admin, AdminResponseDTO.class));
        }
        return listaResponseDTO;
    }

    public List<AdminResponseDTO> buscarAdminPorParteDoNome(String nome) {
        List<Admin> admins = adminRepository.findByParteNome(nome);
        if (admins.isEmpty()){
            throw new EntityNotFoundException("Nenhum admin encontrado");
        }
        List<AdminResponseDTO> listaResponseDTO =new ArrayList<>();
        for (Admin admin : admins){
            listaResponseDTO.add(objectMapper.convertValue(admin, AdminResponseDTO.class));
        }
        return listaResponseDTO;
    }

    public List<AdminResponseDTO> buscarAdminPorEmail(String email) {
        List<Admin> admins = adminRepository.findByEmail(email);
        if (admins.isEmpty()){
            throw new EntityNotFoundException("Nenhum admin encontrado");
        }
        List<AdminResponseDTO> listaResponseDTO =new ArrayList<>();
        for (Admin admin : admins){
            listaResponseDTO.add(objectMapper.convertValue(admin, AdminResponseDTO.class));
        }
        return listaResponseDTO;
    }
}
