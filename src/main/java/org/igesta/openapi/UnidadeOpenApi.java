package org.igesta.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.igesta.dto.UnidadeRequestDTO;
import org.igesta.dto.UnidadeResponseDTO;
import org.igesta.model.Unidade;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Unidades", description = "API para gerenciamento de unidades")
public interface UnidadeOpenApi {

    @Operation(summary = "Buscar todas unidades",
            description = "Retorna uma lista com todas as unidades cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Unidade>> buscarUnidades();

    @Operation(summary = "Busca unidades por ID",
            description = "Retorna uma unidade pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidade encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnidadeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    public ResponseEntity<UnidadeResponseDTO> buscarUnidadePorId(@Parameter(description = "ID da unidade a ser buscada") @PathVariable Long id);

    @Operation(summary = "Insere uma nova unidade",
            description = "Cria uma nova unidade no sistema")
    @ApiResponse(responseCode = "200", description = "Unidade criada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UnidadeResponseDTO.class)))
    public ResponseEntity<Object> inserirUnidade(UnidadeRequestDTO dto);


    @Operation(summary = "Excluir unidade",
            description = "Exclui uma unidade pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Unidade excluida com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnidadeResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    })
    public ResponseEntity<UnidadeResponseDTO> excluirUnidade(@Parameter(description = "ID da unidade a ser excluida") Long id);

    @Operation(
            summary = "Atualizar unidade",
            description = "Atualiza todos os dados de uma unidade existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Unidade atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnidadeResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Object> atualizarUnidade(@Parameter(description = "ID da unidade a ser atulizada") Long id, UnidadeRequestDTO dto);

    @Operation(
            summary = "Atualização parcial da unidade",
            description = "Atualiza apenas os campos informados de uma unidade existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Unidade atualizada parcialmente com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnidadeResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Unidade não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<?> atualizarUnidadeParcial(@Parameter(description = "ID da unidade a ser atulizada parcialmente") Long id, UnidadeRequestDTO atualizacao);
}
