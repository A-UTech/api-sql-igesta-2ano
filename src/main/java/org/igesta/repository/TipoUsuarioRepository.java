package org.igesta.repository;

import org.igesta.dto.TipoUsuarioResponseDTO;
import org.igesta.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {
    @Query(
            value = "SELECT id, tipo_usuario FROM tipo_usuario(:p_email_cnpj, :p_senha)",
            nativeQuery = true
    )
    Optional<TipoUsuarioResponseDTO> autenticar(
            @Param("p_email_cnpj") String p_email_cnpj,
            @Param("p_senha") String p_senha
    );
}