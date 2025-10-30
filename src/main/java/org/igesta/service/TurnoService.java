package org.igesta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.igesta.dto.TurnoRequestDTO;
import org.igesta.dto.TurnoResponseDTO;
import org.igesta.model.Turno;
import org.igesta.repository.TurnoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final ObjectMapper objectMapper;

    public TurnoService(TurnoRepository turnoRepository, ObjectMapper objectMapper) {
        this.turnoRepository = turnoRepository;
        this.objectMapper = objectMapper;
    }

    public List<Turno> buscarTurnos() {
        List<Turno> turnos = turnoRepository.findAll();
        return turnos;
    }

    public TurnoResponseDTO buscarTurnoPorId(Long id) {
        return objectMapper.convertValue(turnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turno não encontrado")), TurnoResponseDTO.class);
    }

    public TurnoResponseDTO buscarTurnoPorUnidadeEPeriodo(Long idUnidade, LocalTime inicio, LocalTime fim) {
        Turno turno = turnoRepository.findByIdUnidadeAndInicioAndFim(idUnidade, inicio, fim);

        if (turno == null) {
            throw new EntityNotFoundException("Nenhum turno encontrado");
        }

        return objectMapper.convertValue(turno, TurnoResponseDTO.class);
    }

    @Transactional
    public TurnoResponseDTO inserirTurno(TurnoRequestDTO dto){
        Turno turno = objectMapper.convertValue(dto, Turno.class);
        return objectMapper.convertValue(turnoRepository.save(turno), TurnoResponseDTO.class);
    }

    @Transactional
    public TurnoResponseDTO atualizarTurno(Long id, TurnoRequestDTO dto) {
        if (!turnoRepository.existsById(id)) {
            throw new EntityNotFoundException("Turno não encontrado para atualizar");
        }
        Turno atualizado =objectMapper.convertValue(dto, Turno.class);
        atualizado.setId(id);
        return objectMapper.convertValue(turnoRepository.save(atualizado), TurnoResponseDTO.class);
    }

    @Transactional
    public TurnoResponseDTO atualizarTurnoParcial(Long id, TurnoRequestDTO dto) {
        if (!turnoRepository.existsById(id)) {
            throw new EntityNotFoundException("Turno não encontrado para atualização parcial!");
        }
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turno encontrado"));

        Turno atualizacoes =objectMapper.convertValue(dto, Turno.class);

        if (atualizacoes.getNome() != null) {
            String nome = atualizacoes.getNome();
            turno.setNome(nome);
        }

        if (atualizacoes.getInicio() != null) {
            LocalTime horaInicio =  atualizacoes.getInicio();
            atualizacoes.setInicio(horaInicio);
        }

        if (atualizacoes.getFim() != null) {
            LocalTime horaFim =  atualizacoes.getFim();
            atualizacoes.setFim(horaFim);
        }

        return objectMapper.convertValue(turnoRepository.save(turno), TurnoResponseDTO.class);
    }

    @Transactional
    public TurnoResponseDTO removerTurno(Long id){
        TurnoResponseDTO existente = buscarTurnoPorId(id);
        turnoRepository.deleteById(id);
        return existente;
    }
}
