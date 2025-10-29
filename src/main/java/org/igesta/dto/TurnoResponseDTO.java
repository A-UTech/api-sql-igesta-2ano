package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Objeto de resposta com dados do turno")
public class TurnoResponseDTO {

    @Schema(description = "ID único do turno", example = "1234")
    private Long id;

    @Schema(description = "Nome do turno", example = "Manhã")
    private String nome;

    @Schema(description = "Hora de início do turno", example = "07:00")
    private String inicio;

    @Schema(description = "Hora de término do turno", example = "15:00")
    private String fim;

    @Schema(description = "ID da unidade associada ao turno", example = "1")
    private Long idUnidade;
}
