package org.igesta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.igesta.dto.TurnoRequestDTO;
import org.igesta.dto.TurnoResponseDTO;
import org.igesta.dto.UnidadeRequestDTO;
import org.igesta.dto.UnidadeResponseDTO;
import org.igesta.model.Turno;
import org.igesta.model.Unidade;
import org.igesta.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public UnidadeResponseDTO atualizarUnidadeParcial(Long id, UnidadeRequestDTO dto) {
        if (!unidadeRepository.existsById(id)) {
            throw new EntityNotFoundException("Unidade não encontrada para atualização parcial!");
        }
        Unidade unidade = unidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unidade encontrada"));

        Unidade atualizacoes =objectMapper.convertValue(dto, Unidade.class);

        if (atualizacoes.getNome() != null) {
            String nome = atualizacoes.getNome();
            unidade.setNome(nome);
        }

        if (atualizacoes.getEstado() != null) {
            String estado =  atualizacoes.getEstado();
            atualizacoes.setEstado(estado);
        }

        if (atualizacoes.getCidade() != null) {
            String cidade =  atualizacoes.getCidade();
            atualizacoes.setCidade(cidade);
        }

        if (atualizacoes.getCnpj() != null) {
            String cnpj =  atualizacoes.getCnpj();
            atualizacoes.setCidade(cnpj);
        }


        return objectMapper.convertValue(unidadeRepository.save(unidade), UnidadeResponseDTO.class);
    }


    @Transactional
    public UnidadeResponseDTO removerUnidade(Long id){
        UnidadeResponseDTO existente = buscarUnidadePorId(id);
        unidadeRepository.deleteById(id);
        return existente;
    }
}
