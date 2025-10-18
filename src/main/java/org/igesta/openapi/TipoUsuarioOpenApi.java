package org.igesta.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.igesta.dto.TipoUsuarioRequestDTO;
import org.igesta.dto.TipoUsuarioResponseDTO;
import org.springframework.http.ResponseEntity;

@Tag(name = "Autenticação", description = "Endpoints para Login e Identificação de Usuário (Gestor, Líder, Unidade)")
public interface TipoUsuarioOpenApi {

    @Operation(summary = "Realizar Login",
            description = "Autentica um usuário (gestor, líder ou unidade) usando email/CNPJ e senha, retornando seu tipo e ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Autenticação bem-sucedida. Retorna o ID e o Tipo de Usuário.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TipoUsuarioResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Credenciais inválidas. (Não Autorizado)",
                    content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida (Campos ausentes ou inválidos)",
                    content = @Content(schema = @Schema(hidden = true))
            )
    })
    public ResponseEntity<TipoUsuarioResponseDTO> login(
            @RequestBody(
                    description = "Credenciais de login (email ou CNPJ e senha)",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TipoUsuarioRequestDTO.class))
            ) TipoUsuarioRequestDTO request
    );
}