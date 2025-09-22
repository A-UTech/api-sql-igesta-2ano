package org.igesta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.igesta.repository.CondenaRepository;
import org.springframework.stereotype.Service;

@Service
public class CondenaService {
    private final CondenaRepository condenaRepository;
    private final ObjectMapper objectMapper;
    public CondenaService(CondenaRepository condenaRepository, ObjectMapper objectMapper) {
        this.condenaRepository = condenaRepository;
        this.objectMapper = objectMapper;
    }
}
