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

import java.util.List;

@Tag(name = "Unidades", description = "API para gerenciamento de unidades")
public interface UnidadeOpenApi {
    @Operation(summary = "Insere uma nova unidade",
            description = "Cria uma nova unidade no sistema")
    @ApiResponse(responseCode = "200", description = "Unidade criada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UnidadeResponseDTO.class)))
    public ResponseEntity<Object> inserirUnidade(UnidadeRequestDTO dto);

    @Operation(summary = "Busca todas unidades",
            description = "Retorna uma lista com todas as unidades cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
            content = @Content(mediaType = "application/json"))
    public ResponseEntity<List<Unidade>> buscarUnidades();

    @Operation(summary = "Busca uma unidade por ID",
            description = "Retorna uma unidade pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Unidade encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UnidadeResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    public ResponseEntity<UnidadeResponseDTO> buscarUnidadePorId(@Parameter(description = "ID da unidade a ser buscada") Long id);

    @Operation(summary = "Excluir unidade",
            description = "Exclui uma unidade pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Unidade excluída com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UnidadeResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    public ResponseEntity<UnidadeResponseDTO> excluirUnidade(@Parameter(description = "ID da unidade a ser excluida") Long id);

    @Operation(summary = "Atualiza uma unidade",
            description = "Atualiza uma unidade pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Unidade atualizada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UnidadeResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Unidade não encontrada")
    public ResponseEntity<Object> atualizarUnidade(@Parameter(description = "ID da unidade a ser atualizada") Long id, UnidadeRequestDTO dto);
}
