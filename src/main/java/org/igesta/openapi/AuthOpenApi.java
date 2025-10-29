package org.igesta.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.igesta.dto.AuthRequestDTO;
import org.springframework.http.ResponseEntity;

@Tag(name = "Autenticação", description = "Endpoints para autenticação e geração de tokens JWT")
public interface AuthOpenApi {
    @Operation(
            summary = "Realiza login e retorna um token JWT",
            description = "Autentica um usuário com e-mail/CNPJ e senha e retorna um token JWT válido.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login realizado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(example = "{\"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6...\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Requisição inválida (faltando campos obrigatórios)",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(example = "{\"error\": \"Credencial e senha são obrigatórios\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Credenciais inválidas",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(example = "{\"error\": \"Credenciais inválidas\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno no servidor",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(example = "{\"error\": \"Erro interno ao autenticar\"}")
                            )
                    )
            }
    )
    public ResponseEntity<?> login(AuthRequestDTO authRequestDTO);
}
