package org.igesta.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.igesta.dto.PlanoRequestDTO;
import org.igesta.dto.PlanoResponseDTO;
import org.igesta.model.Plano;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Planos", description = "API para gerenciamento de planos")
public interface PlanoOpenApi {
    @Operation(summary = "Buscar todos planos",
            description = "Retorna uma lista com todos os planos cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Plano>> listarTodosPlanos();

    @Operation(summary = "Busca plano por ID",
            description = "Retorna um plano pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plano encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlanoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado")
    })
    public ResponseEntity<PlanoResponseDTO> buscarPlanoPorId(@Parameter(description = "ID do plano a ser buscado") @PathVariable Long id);

    @Operation(summary = "Insere um novo plano",
            description = "Cria um novo plano no sistema")
    @ApiResponse(responseCode = "200", description = "Plano criado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PlanoResponseDTO.class)))
    public ResponseEntity<Object> inserirPlano(PlanoRequestDTO dto);

    @Operation(summary = "Excluir plano",
            description = "Exclui um plano pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Plano excluido com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlanoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado")
    })
    public ResponseEntity<PlanoResponseDTO> excluirPlano(@Parameter(description = "ID do plano a ser excluido") Long id);

    @Operation(
            summary = "Atualizar plano",
            description = "Atualiza todos os dados de um plano existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Plano atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlanoResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Object> atualizarPlano(@Parameter(description = "ID do plano a ser atulizado") Long id, PlanoRequestDTO dto);

    @Operation(
            summary = "Atualização parcial do plano",
            description = "Atualiza apenas os campos informados de um plano existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Plano atualizado parcialmente com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlanoResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Plano não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<?> atualizarPlanoParcial(@Parameter(description = "ID do plano a ser atulizado parcialmente") Long id, PlanoRequestDTO atualizacao);
}
