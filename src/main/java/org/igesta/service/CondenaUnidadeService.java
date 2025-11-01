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
    public CondenaUnidadeResponseDTO associar(Long unidadeId, Long condenaId) {

        Unidade unidade = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada com ID: " + unidadeId));

        Condena condena = condenaRepository.findById(condenaId)
                .orElseThrow(() -> new RuntimeException("Condena não encontrada com ID: " + condenaId));

        CondenaUnidade condenaUnidade = new CondenaUnidade();
        condenaUnidade.setIdUnidade(unidadeId);
        condenaUnidade.setIdCondena(condenaId);

        condenaUnidadeRepository.save(condenaUnidade);

        return new CondenaUnidadeResponseDTO(condenaUnidade.getIdCondena());
    }

    @Transactional
    public void desassociar(Long unidadeId, Long condenaId) {
        condenaUnidadeRepository.deleteCondenaUnidadeByIdUnidadeAndIdCondena(unidadeId, condenaId);
    }

    public List<CondenaUnidadeResponseDTO> buscarCondenasPorUnidade(Integer unidadeId) {

        List<CondenaUnidade> entidades = condenaUnidadeRepository.findByIdUnidade(unidadeId);

        return entidades.stream()
                .map(entidade -> new CondenaUnidadeResponseDTO(
                        entidade.getIdCondena()
                ))
                .collect(Collectors.toList());
    }
}