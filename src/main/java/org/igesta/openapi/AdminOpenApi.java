package org.igesta.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.igesta.dto.AdminRequestDTO;
import org.igesta.dto.AdminResponseDTO;
import org.igesta.model.Admin;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Admin", description = "API para gerenciamento de admins")
public interface AdminOpenApi {
    @Operation(summary = "Buscar todos admins",
            description = "Retorna uma lista com todos os admins cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Admin>> buscarAdmins();

    @Operation(summary = "Busca admin por ID",
            description = "Retorna um admin pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Admin encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdminResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Admin não encontrado")
    })
    public ResponseEntity<Object> buscarAdminPorId(@Parameter(description = "ID do admin a ser buscado") Long id);

    @Operation(summary = "Busca admin por nome",
            description = "Retorna um admin pelo seu nome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Admin encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdminResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Admin não encontrado")
    })
    public ResponseEntity<Object> buscarAdminPorNome(@Parameter(description = "Nome do admin a ser buscado") String nome);

    @Operation(summary = "Busca admin por parte do nome",
            description = "Retorna um admin por parte de seu nome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Admin encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdminResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Admin não encontrado")
    })
    public ResponseEntity<Object> buscarCondenaPorParteDoNome(@Parameter(description = "Parte do nome do admin a ser buscado") String nome);

    @Operation(summary = "Busca admin por email",
            description = "Retorna um admin pelo email")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Admin encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdminResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Admin não encontrado")
    })
    public ResponseEntity<Object> buscarAdminPorEmail(@Parameter(description = "Email do admin a ser buscado") String email);

    @Operation(summary = "Insere um novo admin",
            description = "Cria um novo admin no sistema")
    @ApiResponse(responseCode = "200", description = "Admin criado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AdminResponseDTO.class)))
    public ResponseEntity<Object> inserirAdmin(AdminRequestDTO dto);


    @Operation(summary = "Excluir admin",
            description = "Exclui um admin pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Admin excluido com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdminResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Admin não encontrado")
    })
    public ResponseEntity<Object> excluirAdmin(@Parameter(description = "ID do admin a ser excluido") Long id);

    @Operation(
            summary = "Atualizar admin",
            description = "Atualiza todos os dados de um admin existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Admin atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdminResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Admin não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Object> atualizarAdmin(@Parameter(description = "ID do admin a ser atulizado") Long id, AdminRequestDTO dto);

    @Operation(
            summary = "Atualização parcial do admin",
            description = "Atualiza apenas os campos informados de um admin existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Admin atualizado parcialmente com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdminResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Admin não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<?> atualizarAdminParcial(@Parameter(description = "ID da unidade a ser atulizada parcialmente") Long id, AdminRequestDTO atualizacao);
}
