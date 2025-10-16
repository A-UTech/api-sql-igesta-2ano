package org.igesta.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.igesta.dto.PlanoResponseDTO;
import org.igesta.dto.TurnoRequestDTO;
import org.igesta.dto.TurnoResponseDTO;
import org.igesta.model.Turno;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Turnos", description = "API para gerenciamento de turnos")
public interface TurnoOpenApi {
    @Operation(summary = "Buscar todos turnos",
            description = "Retorna uma lista com todos os turnos cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Turno>> buscarTurnos();

    @Operation(summary = "Busca turno por ID",
            description = "Retorna um turno pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Turno encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlanoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Turno não encontrado")
    })
    public ResponseEntity<TurnoResponseDTO> buscarTurnoPorId(@Parameter(description = "ID do turno a ser buscado") @PathVariable Long id);

    @Operation(summary = "Insere um novo turno",
            description = "Cria um novo turno no sistema")
    @ApiResponse(responseCode = "200", description = "Turno criado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TurnoResponseDTO.class)))
    public ResponseEntity<Object> inserirTurno(TurnoRequestDTO dto);

    @Operation(summary = "Excluir turno",
            description = "Exclui um turno pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Turno excluido com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Turno não encontrado")
    })
    public ResponseEntity<TurnoResponseDTO> excluirTurno(@Parameter(description = "ID do turno a ser excluido") Long id);

    @Operation(
            summary = "Atualizar turno",
            description = "Atualiza todos os dados de um turno existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Turno atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Turno não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Object> atualizarTurno(@Parameter(description = "ID do turno a ser atulizado") Long id, TurnoRequestDTO dto);

    @Operation(
            summary = "Atualização parcial do turno",
            description = "Atualiza apenas os campos informados de um turno existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Turno atualizado parcialmente com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnoResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Turno não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<?> atualizarTurnoParcial(@Parameter(description = "ID do turno a ser atulizado parcialmente") Long id, TurnoRequestDTO atualizacao);
}
