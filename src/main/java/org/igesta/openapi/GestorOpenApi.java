package org.igesta.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.igesta.dto.GestorRequestDTO;
import org.igesta.dto.GestorResponseDTO;
import org.igesta.model.Gestor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Gestores", description = "API para gerenciamento de gestores")
public interface GestorOpenApi {
    @Operation(summary = "Buscar todos gestores",
            description = "Retorna uma lista com todos os gestores cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Gestor>> buscarGestores();

    @Operation(summary = "Busca gestor por ID",
            description = "Retorna um gestor pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Gestor encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GestorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Gestor não encontrado")
    })
    public ResponseEntity<Object> buscarGestorPorId(@Parameter(description = "ID do gestor a ser buscado") @PathVariable Long id);

    @Operation(summary = "Busca gestor por nome",
            description = "Retorna um gestor pelo seu nome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Gestor encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GestorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Gestor não encontrado")
    })
    public ResponseEntity<Object> buscarGestorPorNome(@Parameter(description = "Nome do gestor a ser buscado") @PathVariable String nome);

    @Operation(summary = "Busca gestor por cpf",
            description = "Retorna um gestor pelo seu cpf")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Gestor encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GestorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Gestor não encontrado")
    })
    public ResponseEntity<Object> buscarGestorPorCpf(@Parameter(description = "Cpf do gestor a ser buscado") @PathVariable String cpf);

    @Operation(summary = "Busca gestor por parte do nome",
            description = "Retorna um gestor por parte do seu nome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Gestor encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GestorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Gestor não encontrado")
    })
    public ResponseEntity<Object> buscarGestorPorParteDoNome(@Parameter(description = "Parte do nome do gestor a ser buscado") String nome);

    @Operation(summary = "Insere um novo gestor",
            description = "Cria um novo gestor no sistema")
    @ApiResponse(responseCode = "200", description = "Gestor criado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = GestorResponseDTO.class)))
    public ResponseEntity<Object> inserirGestor(GestorRequestDTO dto);

    @Operation(summary = "Excluir gestor",
            description = "Exclui um gestor pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Gestor excluido com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GestorResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Gestor não encontrado")
    })
    public ResponseEntity<Object> excluirGestor(@Parameter(description = "ID do gestor a ser excluido") Long id);

    @Operation(
            summary = "Atualizar gestor",
            description = "Atualiza todos os dados de um gestor existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Gestor atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GestorResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Gestor não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Object> atualizarGestor(@Parameter(description = "ID do gestor a ser atulizado") Long id, GestorRequestDTO dto);

    @Operation(
            summary = "Atualização parcial do gestor",
            description = "Atualiza apenas os campos informados de um gestor existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Gestor atualizado parcialmente com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GestorResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Gestor não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<?> atualizarGestorParcial(@Parameter(description = "ID do gestor a ser atulizado parcialmente") Long id, GestorRequestDTO atualizacao);
}
