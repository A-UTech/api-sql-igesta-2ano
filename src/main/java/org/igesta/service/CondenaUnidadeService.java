package org.igesta.service;

import org.igesta.dto.CondenaUnidadeRequestDTO;
import org.igesta.dto.CondenaUnidadeResponseDTO;
import org.igesta.model.CondenaUnidade;
import org.igesta.model.CondenaUnidadeId;
import org.igesta.model.Unidade;
import org.igesta.model.Condena;
import org.igesta.repository.CondenaUnidadeRepository;
import org.igesta.repository.UnidadeRepository;
import org.igesta.repository.CondenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CondenaUnidadeService {

    @Autowired
    private CondenaUnidadeRepository condenaUnidadeRepository;
    @Autowired
    private UnidadeRepository unidadeRepository;
    @Autowired
    private CondenaRepository condenaRepository;

    @Transactional
    public CondenaUnidadeResponseDTO associar(CondenaUnidadeRequestDTO requestDTO) {

        Unidade unidade = unidadeRepository.findById(requestDTO.getIdUnidade().longValue())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada com ID: " + requestDTO.getIdUnidade()));

        Condena condena = condenaRepository.findById(requestDTO.getIdCondena().longValue())
                .orElseThrow(() -> new RuntimeException("Condena não encontrada com ID: " + requestDTO.getIdCondena()));

        CondenaUnidade condenaUnidade = new CondenaUnidade();
        condenaUnidade.setIdUnidade(requestDTO.getIdUnidade());
        condenaUnidade.setIdCondena(requestDTO.getIdCondena());

        condenaUnidadeRepository.save(condenaUnidade);

        return new CondenaUnidadeResponseDTO(condenaUnidade.getIdUnidade(), condenaUnidade.getIdCondena());
    }

    @Transactional
    public void desassociar(CondenaUnidadeRequestDTO requestDTO) {
        CondenaUnidadeId id = new CondenaUnidadeId(requestDTO.getIdCondena(), requestDTO.getIdUnidade());

        condenaUnidadeRepository.deleteById(id);
    }

    public List<CondenaUnidadeResponseDTO> buscarCondenasPorUnidade(Integer unidadeId) {

        List<CondenaUnidade> entidades = condenaUnidadeRepository.findByIdUnidade(unidadeId);

        return entidades.stream()
                .map(entidade -> new CondenaUnidadeResponseDTO(
                        entidade.getIdUnidade().intValue(),
                        entidade.getIdCondena().intValue()
                ))
                .collect(Collectors.toList());
    }
}