package org.igesta.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.igesta.dto.LiderRequestDTO;
import org.igesta.dto.LiderResponseDTO;
import org.igesta.model.Lider;
import org.igesta.repository.LiderRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LiderService {

    private final LiderRepository liderRepository;
    private final ObjectMapper objectMapper;

    public LiderService(LiderRepository liderRepository, ObjectMapper objectMapper) {
        this.liderRepository = liderRepository;
        this.objectMapper = objectMapper;
    }

    public List<Lider> buscarLideres() {
        return liderRepository.findAll();
    }

    public LiderResponseDTO buscarLiderPorId(Long id) {
        return objectMapper.convertValue(liderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condena não encontrada")), LiderResponseDTO.class);
    }

    public List<LiderResponseDTO> buscarLiderPorNome(String nome) {
        List<Lider> lideres = liderRepository.findByNome(nome);
        if (lideres == null){
            throw new EntityNotFoundException("Lider não encontrado(a)");
        }
        List<LiderResponseDTO> listaResponseDTO = new ArrayList<>();
        for (Lider lider : lideres){
            listaResponseDTO.add(objectMapper.convertValue(lider, LiderResponseDTO.class));
        }
        return listaResponseDTO;
    }

    @Transactional
    public LiderResponseDTO inserirLider(LiderRequestDTO dto){
        Lider lider = objectMapper.convertValue(dto, Lider.class);
        return objectMapper.convertValue(liderRepository.save(lider), LiderResponseDTO.class);
    }

    @Transactional
    public LiderResponseDTO atualizarlider(Long id, LiderRequestDTO dto) {
        if (!liderRepository.existsById(id)) {
            throw new EntityNotFoundException("Líder não encontrada para atualizar");
        }
        Lider atualizado =objectMapper.convertValue(dto, Lider.class);
        atualizado.setId(id);
        return objectMapper.convertValue(liderRepository.save(atualizado), LiderResponseDTO.class);
    }

    @Transactional
    public LiderResponseDTO atualizarLiderParcial(Long id, LiderRequestDTO dto) {
        if (!liderRepository.existsById(id)) {
            throw new EntityNotFoundException("Lider não encontrado(a) para atualização parcial!");
        }
        Lider lider = liderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Líder não encontrado"));

        Lider atualizacoes =objectMapper.convertValue(dto, Lider.class);

        if (atualizacoes.getIdGestor() != 0){
            Long idGestor = atualizacoes.getIdGestor();
            lider.setIdGestor(idGestor);
        }
        if (atualizacoes.getNome() != null) {
            String nome = atualizacoes.getNome();
            lider.setNome(nome);
        }

        if (atualizacoes.getEmail() != null) {
            String email = atualizacoes.getEmail();
            lider.setEmail(email);
        }

        if (atualizacoes.getCpf() != null) {
            String cpf =  atualizacoes.getCpf();
            lider.setCpf(cpf);
        }

        if (atualizacoes.getSenha() != null) {
            String senha =  atualizacoes.getSenha();
            lider.setSenha(senha);
        }

        return objectMapper.convertValue(liderRepository.save(lider), LiderResponseDTO.class);
    }

    @Transactional
    public LiderResponseDTO removerLider(Long id){
        LiderResponseDTO existente = buscarLiderPorId(id);
        liderRepository.deleteById(id);
        return existente;
    }
}
