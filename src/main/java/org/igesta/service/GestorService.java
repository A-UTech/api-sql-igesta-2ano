package org.igesta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.igesta.dto.GestorRequestDTO;
import org.igesta.dto.GestorResponseDTO;
import org.igesta.model.Gestor;
import org.igesta.repository.GestorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GestorService {
    private final GestorRepository gestorRepository;
    private final ObjectMapper objectMapper;

    public GestorService(GestorRepository gestorRepository, ObjectMapper objectMapper) {
        this.gestorRepository = gestorRepository;
        this.objectMapper = objectMapper;
    }

    public List<Gestor> buscarGestores() {
        return gestorRepository.findAll();
    }

    public GestorResponseDTO buscarGestorPorId(Long id) {
        return objectMapper.convertValue(gestorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gestor não encontrado")), GestorResponseDTO.class);
    }

    @Transactional
    public GestorResponseDTO adicionarGestor(GestorRequestDTO dto){
        Gestor gestor = objectMapper.convertValue(dto, Gestor.class);
        return objectMapper.convertValue(gestorRepository.save(gestor), GestorResponseDTO.class);
    }

    @Transactional
    public GestorResponseDTO removerGestor(Long id){
        GestorResponseDTO existente = buscarGestorPorId(id);
        gestorRepository.deleteById(id);
        return existente;
    }

    @Transactional
    public GestorResponseDTO atualizarGestor(Long id, GestorRequestDTO dto) {
        if (!gestorRepository.existsById(id)) {
            throw new EntityNotFoundException("Gestor não encontrado para atualizar");
        }
        Gestor atualizado =objectMapper.convertValue(dto, Gestor.class);
        atualizado.setId(id);
        return objectMapper.convertValue(gestorRepository.save(atualizado), GestorResponseDTO.class);
    }

    @Transactional
    public GestorResponseDTO atualizarGestorParcial(Long id, GestorRequestDTO dto) {
        if (!gestorRepository.existsById(id)) {
            throw new EntityNotFoundException("Gestor não encontrado para atualização parcial");
        }
        Gestor gestor = gestorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gestor não encontrado"));

        Gestor atualizacoes =objectMapper.convertValue(dto, Gestor.class);

        if (atualizacoes.getNome() != null) {
            String nome = atualizacoes.getNome();
            gestor.setNome(nome);
        }

        if (atualizacoes.getEmail() != null) {
            String email = atualizacoes.getEmail();
            gestor.setNome(email);
        }

        if (atualizacoes.getSenha() != null) {
            String senha = atualizacoes.getSenha();
            gestor.setSenha(senha);
        }

        return objectMapper.convertValue(gestorRepository.save(gestor), GestorResponseDTO.class);
    }

    public List<GestorResponseDTO> buscarGestorPorNome(String nome) {
        List<Gestor> gestores = gestorRepository.findByNome(nome);
        if (gestores == null){
            throw new EntityNotFoundException("Gestor não encontrado");
        }
        List<GestorResponseDTO> listaResponseDTO = new ArrayList<>();
        for (Gestor gestor : gestores){
            listaResponseDTO.add(objectMapper.convertValue(gestor, GestorResponseDTO.class));
        }
        return listaResponseDTO;
    }

    public List<GestorResponseDTO> buscarGestorPorParteDoNome(String nome) {
        List<Gestor> gestores = gestorRepository.findByParteNome(nome);
        if (gestores.isEmpty()){
            throw new EntityNotFoundException("Nenhum gestor encontrado");
        }
        List<GestorResponseDTO> listaResponseDTO =new ArrayList<>();
        for (Gestor gestor : gestores){
            listaResponseDTO.add(objectMapper.convertValue(gestor, GestorResponseDTO.class));
        }
        return listaResponseDTO;
    }
}
