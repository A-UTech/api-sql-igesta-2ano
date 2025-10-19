package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO de Resposta para Associação Condena-Unidade")
public class CondenaUnidadeResponseDTO {

    private Integer idUnidade;
    private Integer idCondena;

    public CondenaUnidadeResponseDTO(Integer idUnidade, Integer idCondena) {
        this.idUnidade = idUnidade;
        this.idCondena = idCondena;
    }

    public CondenaUnidadeResponseDTO() {
    }
}