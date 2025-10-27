package org.igesta.security;

import org.igesta.dto.TipoUsuarioResponseDTO;
import org.igesta.service.GestorService;
import org.igesta.service.LiderService;
import org.igesta.service.TipoUsuarioService;
import org.igesta.service.UnidadeService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TipoUsuarioService tipoUsuarioService;
    private final GestorService gestorService;
    private final LiderService liderService;
    private final UnidadeService unidadeService;

    public CustomUserDetailsService(TipoUsuarioService tipoUsuarioService,
                                    GestorService gestorService,
                                    LiderService liderService,
                                    UnidadeService unidadeService) {
        this.tipoUsuarioService = tipoUsuarioService;
        this.gestorService = gestorService;
        this.liderService = liderService;
        this.unidadeService = unidadeService;
    }

    public UserDetails loadUserByCredencialAndSenha(String credencial, String senha) {
        TipoUsuarioResponseDTO tipoUsuario = tipoUsuarioService.realizarLogin(credencial, senha);

        String username;
        String senhaReal;
        String role = tipoUsuario.getTipoUsuario().toUpperCase();

        switch (role) {
            case "GESTOR":
                var gestor = gestorService.buscarGestorPorId(tipoUsuario.getId());
                username = gestor.getEmail();
                senhaReal = gestor.getSenha();
                break;
            case "LIDER":
                var lider = liderService.buscarLiderPorId(tipoUsuario.getId());
                username = lider.getEmail();
                senhaReal = lider.getSenha();
                break;
            case "UNIDADE":
                var unidade = unidadeService.buscarUnidadePorId(tipoUsuario.getId());
                username = unidade.getCnpj();
                senhaReal = unidade.getSenha();
                break;
            default:
                throw new UsernameNotFoundException("Tipo de usuário desconhecido");
        }

        return User.builder()
                .username(username)
                .password(senhaReal) // senha real da entidade
                .roles(role)
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Use loadUserByCredencialAndSenha instead");
    }
}


