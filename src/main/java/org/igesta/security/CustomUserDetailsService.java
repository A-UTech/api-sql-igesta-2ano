package org.igesta.security;

import org.igesta.dto.TipoUsuarioResponseDTO;
import org.igesta.service.*;
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

    private final AdminService adminService;

    public CustomUserDetailsService(TipoUsuarioService tipoUsuarioService,
                                    GestorService gestorService,
                                    LiderService liderService,
                                    UnidadeService unidadeService,
                                    AdminService adminService) {
        this.tipoUsuarioService = tipoUsuarioService;
        this.gestorService = gestorService;
        this.liderService = liderService;
        this.unidadeService = unidadeService;
        this.adminService = adminService;
    }

    public UserDetails loadUserByCredencialAndSenha(String emailCnpj, String senha) {
        TipoUsuarioResponseDTO tipoUsuario = tipoUsuarioService.realizarLogin(emailCnpj, senha);

        String username;
        String senhaReal;
        String role = tipoUsuario.getTipoUsuario().toUpperCase();

        switch (role) {
            case "ADMIN":
                var admin = adminService.buscarAdminPorId(tipoUsuario.getId());
                username = admin.getEmail();
                senhaReal = admin.getSenha();
                break;
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


