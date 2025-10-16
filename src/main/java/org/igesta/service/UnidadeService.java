package org.igesta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.igesta.dto.UnidadeRequestDTO;
import org.igesta.dto.UnidadeResponseDTO;
import org.igesta.model.Unidade;
import org.igesta.repository.UnidadeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UnidadeService {

    private final UnidadeRepository unidadeRepository;
    private final ObjectMapper objectMapper;

    public UnidadeService(UnidadeRepository unidadeRepository, ObjectMapper objectMapper) {
        this.unidadeRepository = unidadeRepository;
        this.objectMapper = objectMapper;
    }

    public List<Unidade> buscarUnidades() {
        return unidadeRepository.findAll();
    }

    public UnidadeResponseDTO buscarUnidadePorId(Long id) {
        return objectMapper.convertValue(unidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidade não encontrada")), UnidadeResponseDTO.class);
    }

    @Transactional
    public UnidadeResponseDTO inserirUnidade(UnidadeRequestDTO dto){
        Unidade unidade = objectMapper.convertValue(dto, Unidade.class);
        return objectMapper.convertValue(unidadeRepository.save(unidade), UnidadeResponseDTO.class);
    }

    @Transactional
    public UnidadeResponseDTO atualizarUnidade(Long id, UnidadeRequestDTO dto) {
        if (!unidadeRepository.existsById(id)) {
            throw new EntityNotFoundException("Unidade não encontrada para atualizar");
        }
        Unidade atualizado =objectMapper.convertValue(dto, Unidade.class);
        atualizado.setId(id);
        return objectMapper.convertValue(unidadeRepository.save(atualizado), UnidadeResponseDTO.class);
    }


    @Transactional
    public UnidadeResponseDTO removerUnidade(Long id){
        UnidadeResponseDTO existente = buscarUnidadePorId(id);
        unidadeRepository.deleteById(id);
        return existente;
    }
}
