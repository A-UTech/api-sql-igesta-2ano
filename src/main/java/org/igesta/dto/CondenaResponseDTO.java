package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Objeto de resposta com dados da condena")
public class CondenaResponseDTO {
    @Schema(description = "ID único da condena", example = "1234")
    private Long id;
    @Schema(description = "Nome da condena", example = "Artrite T")
    private String nome;
    @Schema(description = "Tipo da condena", example = "Total")
    private String tipo;
}
