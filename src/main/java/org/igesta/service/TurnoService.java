package org.igesta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.igesta.repository.TurnoRepository;
import org.springframework.stereotype.Service;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;
    private final ObjectMapper objectMapper;
    public TurnoService(TurnoRepository turnoRepository, ObjectMapper objectMapper) {
        this.turnoRepository = turnoRepository;
        this.objectMapper = objectMapper;
    }
}
