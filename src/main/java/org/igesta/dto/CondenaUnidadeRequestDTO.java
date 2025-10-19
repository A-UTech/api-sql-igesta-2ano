package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.igesta.validation.OnCreate;

@Getter
@Setter
@Schema(description = "DTO de Requisição para Associação Condena-Unidade")
public class CondenaUnidadeRequestDTO {
    @Schema(description = "ID da Unidade a ser associada", example = "1")
    @NotNull(groups = OnCreate.class, message = "O ID da Unidade não pode ser nulo.")
    private Integer idUnidade;

    @Schema(description = "ID da Condena a ser associada", example = "5")
    @NotNull(groups = OnCreate.class, message = "O ID da Condena não pode ser nulo.")
    private Integer idCondena;
}