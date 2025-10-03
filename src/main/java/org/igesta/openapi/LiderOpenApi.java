package org.igesta.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.igesta.dto.LiderRequestDTO;
import org.igesta.dto.LiderResponseDTO;
import org.igesta.model.Lider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Líderes", description = "API para gerenciamento de líderes")
public interface LiderOpenApi {
    @Operation(summary = "Buscar todos líderes",
            description = "Retorna uma lista com todos os líderes cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Lider>> listarTodosLideres();

    @Operation(summary = "Busca líder por ID",
            description = "Retorna um líder pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Líder encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LiderResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Líder não encontrado")
    })
    public ResponseEntity<LiderResponseDTO> buscarLiderPorId(@Parameter(description = "ID do líder a ser buscado") @PathVariable Long id);

    @Operation(summary = "Busca líder por nome",
            description = "Retorna um líder pelo seu nome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Líder encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LiderResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Líder não encontrado")
    })
    public ResponseEntity<Object> buscarLiderPorNome(@Parameter(description = "Nome do líder a ser buscado") @PathVariable String nome);

    @Operation(summary = "Insere um novo líder",
            description = "Cria um novo líder no sistema")
    @ApiResponse(responseCode = "200", description = "Líder criado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LiderResponseDTO.class)))
    public ResponseEntity<Object> inserirLider(LiderRequestDTO dto);

    @Operation(summary = "Excluir líder",
            description = "Exclui um líder pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Líder excluido com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LiderResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Líder não encontrado")
    })
    public ResponseEntity<LiderResponseDTO> excluirLider(@Parameter(description = "ID do líder a ser excluido") Long id);

    @Operation(
            summary = "Atualizar líder",
            description = "Atualiza todos os dados de um líder existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Líder atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LiderResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Líder não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Object> atualizarLider(@Parameter(description = "ID do lider a ser atulizado") Long id, LiderRequestDTO dto);

    @Operation(
            summary = "Atualização parcial do líder",
            description = "Atualiza apenas os campos informados de um líder existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Líder atualizado parcialmente com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LiderResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Líder não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<?> atualizarLiderParcial(@Parameter(description = "ID do líder a ser atulizado parcialmente") Long id, LiderRequestDTO atualizacao);
}
