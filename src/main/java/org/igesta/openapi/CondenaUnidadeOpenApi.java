package org.igesta.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.igesta.dto.CondenaUnidadeRequestDTO;
import org.igesta.dto.CondenaUnidadeResponseDTO;
import org.igesta.validation.OnCreate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "CondenaUnidade", description = "API para gerenciamento de condenas associadas a uma unidade")
public interface CondenaUnidadeOpenApi {
    @Operation(summary = "Associar uma condena a uma unidade",
            description = "Associa uma condena a uma unidade pelos seus IDs")
    @ApiResponse(responseCode = "200", description = "Condena associada com sucesso")
    @ApiResponse(responseCode = "400", description = "Condena ou Unidade inválida")
    @ApiResponse(responseCode = "404", description = "Condena ou Unidade não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<CondenaUnidadeResponseDTO> associar(@RequestBody @Validated({OnCreate.class, Default.class}) CondenaUnidadeRequestDTO dto);

    @Operation(summary = "Desassociar uma condena de uma unidade",
            description = "Desassocia uma condena de uma unidade pelos seus IDs")
    @ApiResponse(responseCode = "200", description = "Condena desassociada com sucesso")
    @ApiResponse(responseCode = "404", description = "Condena ou Unidade não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> desassociar(@RequestBody @Validated({OnCreate.class, Default.class}) CondenaUnidadeRequestDTO dto);

    @Operation(summary = "Listar condenas associadas a uma unidade",
            description = "Lista todas as condenas de uma unidade")
    @ApiResponse(responseCode = "200", description = "Condenas associadas a uma unidade")
    @ApiResponse(responseCode = "404", description = "Condena ou Unidade não encontrada")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public ResponseEntity<List<CondenaUnidadeResponseDTO>> buscarPorUnidade(@PathVariable Integer unidadeId);

}
