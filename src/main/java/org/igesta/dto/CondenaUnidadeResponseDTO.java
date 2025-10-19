package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "DTO de Resposta para Associação Condena-Unidade")
public class CondenaUnidadeResponseDTO {

    private Integer idCondena;

    public CondenaUnidadeResponseDTO(Integer idCondena) {
        this.idCondena = idCondena;
    }

    public CondenaUnidadeResponseDTO() {
    }
}