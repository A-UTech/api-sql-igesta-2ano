package org.igesta.service;

import org.igesta.dto.TipoUsuarioResponseDTO;
import org.igesta.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoUsuarioService {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public TipoUsuarioService(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public TipoUsuarioResponseDTO realizarLogin(String credencial, String senha) {

        return tipoUsuarioRepository.autenticar(credencial, senha)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas."));
    }
}