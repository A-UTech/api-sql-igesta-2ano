package org.igesta.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.igesta.dto.EmpresaRequestDTO;
import org.igesta.dto.EmpresaResponseDTO;
import org.igesta.model.Empresa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Empresas", description = "API para gerenciamento de empresas")
public interface EmpresaOpenApi {
    @Operation(summary = "Buscar todas empresas",
            description = "Retorna uma lista com todas as empresas cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<Empresa>> listarTodasEmpresas();

    @Operation(summary = "Busca empresa por ID",
            description = "Retorna uma empresa pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    public ResponseEntity<Object> buscarEmpresaPorId(@Parameter(description = "ID da empresa a ser buscada") @PathVariable Long id);

    @Operation(summary = "Busca empresa por nome",
            description = "Retorna uma empresa pelo seu nome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    public ResponseEntity<Object> buscarEmpresaPorNome(@Parameter(description = "Nome da empresa a ser buscada") @PathVariable String nome);

    @Operation(summary = "Busca empresa por cnpj",
            description = "Retorna uma empresa pelo seu cnpj")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    public ResponseEntity<Object> buscarEmpresaPorCnpj(@Parameter(description = "Cnpj da empresa a ser buscada") @PathVariable String cnpj);

    @Operation(summary = "Busca empresa por parte do nome",
            description = "Retorna uma empresa por parte do seu nome")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    public ResponseEntity<Object> buscarEmpresaPorParteDoNome(@Parameter(description = "Parte do nome da empresa a ser buscada") String nome);

    @Operation(summary = "Insere uma nova empresa",
            description = "Cria uma nova empresa no sistema")
    @ApiResponse(responseCode = "200", description = "Empresa criada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = EmpresaResponseDTO.class)))
    public ResponseEntity<Object> inserirEmpresa(EmpresaRequestDTO dto);

    @Operation(summary = "Excluir empresa",
            description = "Exclui uma empresa pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa excluída com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    public ResponseEntity<Object> excluirEmpresa(@Parameter(description = "ID da empresa a ser excluida") Long id);

    @Operation(
            summary = "Atualizar empresa",
            description = "Atualiza todos os dados de uma empresa existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Empresa atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Object> atualizarEmpresa(@Parameter(description = "ID da empresa a ser atulizada") Long id, EmpresaRequestDTO dto);

    @Operation(
            summary = "Atualização parcial da empresa",
            description = "Atualiza apenas os campos informados de uma empresa existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Empresa atualizada parcialmente com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<?> atualizarEmpresaParcial(@Parameter(description = "ID da empresa a ser atulizada parcialmente") Long id, EmpresaRequestDTO atualizacao);
}
