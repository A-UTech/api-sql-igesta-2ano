package org.igesta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.igesta.repository.LiderRepository;
import org.springframework.stereotype.Service;

@Service
public class LiderService {
    private final LiderRepository liderRepository;
    private final ObjectMapper objectMapper;

    public LiderService(LiderRepository liderRepository, ObjectMapper objectMapper) {
        this.liderRepository = liderRepository;
        this.objectMapper = objectMapper;
    }
}
