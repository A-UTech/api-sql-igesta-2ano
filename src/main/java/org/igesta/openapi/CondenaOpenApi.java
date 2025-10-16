package org.igesta.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.igesta.dto.CondenaRequestDTO;
import org.igesta.dto.CondenaResponseDTO;
import org.igesta.model.Condena;

import org.springframework.http.ResponseEntity;


import java.util.List;

@Tag(name = "Condenas", description = "API para gerenciamento de condenas")
public interface CondenaOpenApi {

    @Operation(summary = "Buscar todas condenas",
            description = "Retorna uma lista com todas as condenas cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Condena>> buscarCondenas();

    @Operation(summary = "Busca condena por ID",
            description = "Retorna uma condena pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Condena encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CondenaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Condena não encontrada")
    })
    public ResponseEntity<Object> buscarCondenaPorId(@Parameter(description = "ID da condena a ser buscada") Long id);

    @Operation(summary = "Busca condena por nome",
            description = "Retorna uma condena pelo seu nome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Condena encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CondenaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Condena não encontrada")
    })
    public ResponseEntity<Object> buscarCondenaPorNome(@Parameter(description = "Nome da condena a ser buscada") String nome);

    @Operation(summary = "Busca condena por tipo",
            description = "Retorna uma condena pelo seu tipo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Condena encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CondenaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Condena não encontrada")
    })
    public ResponseEntity<Object> buscarCondenaPorTipo(@Parameter(description = "Tipo da condena a ser buscada") String tipo);

    @Operation(summary = "Busca condena por parte do nome",
            description = "Retorna uma condena por parte do seu nome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Condena encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CondenaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Condena não encontrada")
    })
    public ResponseEntity<Object> buscarCondenaPorParteDoNome(@Parameter(description = "Parte do nome da condena a ser buscada") String nome);

    @Operation(summary = "Insere uma nova condena",
            description = "Cria uma nova condena no sistema")
    @ApiResponse(responseCode = "200", description = "Condena criada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CondenaResponseDTO.class)))
    public ResponseEntity<Object> inserirCondena(CondenaRequestDTO dto);

    @Operation(summary = "Excluir condena",
            description = "Exclui uma condena pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Condena excluída com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CondenaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Condena não encontrada")
    })
    public ResponseEntity<Object> excluirCondena(@Parameter(description = "ID da condena a ser excluida") Long id);

    @Operation(
            summary = "Atualizar condena",
            description = "Atualiza todos os dados de uma condena existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Condena atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CondenaResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Condena não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Object> atualizarCondena(@Parameter(description = "ID da condena a ser atulizada") Long id, CondenaRequestDTO dto);

    @Operation(
            summary = "Atualização parcial de condena",
            description = "Atualiza apenas os campos informados de uma condena existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Condena atualizada parcialmente com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CondenaResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Condena não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<?> atualizarCondenaParcial(@Parameter(description = "ID da condena a ser atulizada parcialmente") Long id, CondenaRequestDTO atualizacao);
}
