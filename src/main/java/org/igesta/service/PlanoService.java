package org.igesta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.igesta.dto.PlanoRequestDTO;
import org.igesta.dto.PlanoResponseDTO;
import org.igesta.model.Plano;
import org.igesta.repository.PlanoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;
    private final ObjectMapper objectMapper;

    public PlanoService(PlanoRepository planoRepository, ObjectMapper objectMapper) {
        this.planoRepository = planoRepository;
        this.objectMapper = objectMapper;
    }

    public List<Plano> buscarPlanos() {
        return planoRepository.findAll();
    }

    public PlanoResponseDTO buscarPlanoPorId(Long id) {
        return objectMapper.convertValue(planoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado")), PlanoResponseDTO.class);
    }

    @Transactional
    public PlanoResponseDTO inserirPlano(PlanoRequestDTO dto){
        Plano plano = objectMapper.convertValue(dto, Plano.class);
        return objectMapper.convertValue(planoRepository.save(plano), PlanoResponseDTO.class);
    }

    @Transactional
    public PlanoResponseDTO atualizarPlano(Long id, PlanoRequestDTO dto) {
        if (!planoRepository.existsById(id)) {
            throw new EntityNotFoundException("Plano não encontrado para atualizar");
        }
        Plano atualizado =objectMapper.convertValue(dto, Plano.class);
        atualizado.setId(id);
        return objectMapper.convertValue(planoRepository.save(atualizado), PlanoResponseDTO.class);
    }

    @Transactional
    public PlanoResponseDTO atualizarPlanoParcial(Long id, PlanoRequestDTO dto) {
        if (!planoRepository.existsById(id)) {
            throw new EntityNotFoundException("Lider não encontrado(a) para atualização parcial!");
        }
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));

        Plano atualizacoes =objectMapper.convertValue(dto, Plano.class);

        if (atualizacoes.getNome() != null) {
            String nome = atualizacoes.getNome();
            plano.setNome(nome);
        }

        if (atualizacoes.getPreco() != 0.0) {
            double preco = atualizacoes.getPreco();
            plano.setPreco(preco);
        }

        if (atualizacoes.getArmazenamento() != null) {
            String armazenamento =  atualizacoes.getArmazenamento();
            plano.setArmazenamento(armazenamento);
        }

        return objectMapper.convertValue(planoRepository.save(plano), PlanoResponseDTO.class);
    }

    @Transactional
    public PlanoResponseDTO removerPlano(Long id){
        PlanoResponseDTO existente = buscarPlanoPorId(id);
        planoRepository.deleteById(id);
        return existente;
    }
}
