package org.igesta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.igesta.dto.CondenaRequestDTO;
import org.igesta.dto.CondenaResponseDTO;
import org.igesta.model.Condena;
import org.igesta.repository.CondenaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CondenaService {
    private final CondenaRepository condenaRepository;
    private final ObjectMapper objectMapper;

    public CondenaService(CondenaRepository condenaRepository, ObjectMapper objectMapper) {
        this.condenaRepository = condenaRepository;
        this.objectMapper = objectMapper;
    }

    public List<Condena> buscarCondenas() {
        return condenaRepository.findAll();
    }

    public CondenaResponseDTO buscarCondenaPorId(Long id) {
        return objectMapper.convertValue(condenaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condena não encontrada")), CondenaResponseDTO.class);
    }

    @Transactional
    public CondenaResponseDTO adicionarCondena(CondenaRequestDTO dto){
        Condena condena = objectMapper.convertValue(dto, Condena.class);
        return objectMapper.convertValue(condenaRepository.save(condena), CondenaResponseDTO.class);
    }

    @Transactional
    public CondenaResponseDTO removerCondena(Long id){
        CondenaResponseDTO existente = buscarCondenaPorId(id);
        condenaRepository.deleteById(id);
        return existente;
    }

    @Transactional
    public CondenaResponseDTO atualizarCondena(Long id, CondenaRequestDTO dto) {
        if (!condenaRepository.existsById(id)) {
            throw new EntityNotFoundException("Condena não encontrada para atualizar");
        }
        Condena atualizado =objectMapper.convertValue(dto, Condena.class);
        atualizado.setId(id);
        return objectMapper.convertValue(condenaRepository.save(atualizado), CondenaResponseDTO.class);
    }

    @Transactional
    public CondenaResponseDTO atualizarCondenaParcial(Long id, CondenaRequestDTO dto) {
        if (!condenaRepository.existsById(id)) {
            throw new EntityNotFoundException("Condena não encontrada para atualização parcial");
        }
        Condena condena = condenaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Condena não encontrada"));

        Condena atualizacoes =objectMapper.convertValue(dto, Condena.class);

        if (atualizacoes.getNome() != null) {
            String nome = atualizacoes.getNome();
            condena.setNome(nome);
        }

        if (atualizacoes.getTipo() != null) {
            String tipo =  atualizacoes.getTipo();
            condena.setTipo(tipo);
        }

        return objectMapper.convertValue(condenaRepository.save(condena), CondenaResponseDTO.class);
    }

    public List<CondenaResponseDTO> buscarCondenaPorNome(String nome) {
        List<Condena> condenas = condenaRepository.findByNome(nome);
        if (condenas == null){
            throw new EntityNotFoundException("Condena não encontrada");
        }
        List<CondenaResponseDTO> listaResponseDTO = new ArrayList<>();
        for (Condena condena : condenas){
            listaResponseDTO.add(objectMapper.convertValue(condena, CondenaResponseDTO.class));
        }
        return listaResponseDTO;
    }

    public List<CondenaResponseDTO> buscarCondenaPorParteDoNome(String nome) {
        List<Condena> condenas = condenaRepository.findByParteNome(nome);
        if (condenas.isEmpty()){
            throw new EntityNotFoundException("Nenhuma condena encontrada");
        }
        List<CondenaResponseDTO> listaResponseDTO =new ArrayList<>();
        for (Condena condena : condenas){
            listaResponseDTO.add(objectMapper.convertValue(condena, CondenaResponseDTO.class));
        }
        return listaResponseDTO;
    }

    public List<CondenaResponseDTO> buscarCondenaPorTipo(String tipo) {
        List<Condena> condenas = condenaRepository.findByTipo(tipo);
        if (condenas.isEmpty()){
            throw new EntityNotFoundException("Nenhuma condena encontrada");
        }
        List<CondenaResponseDTO> listaResponseDTO =new ArrayList<>();
        for (Condena condena : condenas){
            listaResponseDTO.add(objectMapper.convertValue(condena, CondenaResponseDTO.class));
        }
        return listaResponseDTO;
    }

}
