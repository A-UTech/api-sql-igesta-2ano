package org.igesta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.igesta.repository.GestorRepository;
import org.springframework.stereotype.Service;

@Service
public class GestorService {
    private final GestorRepository gestorRepository;
    private final ObjectMapper objectMapper;

    public GestorService(GestorRepository gestorRepository, ObjectMapper objectMapper) {
        this.gestorRepository = gestorRepository;
        this.objectMapper = objectMapper;
    }
}
