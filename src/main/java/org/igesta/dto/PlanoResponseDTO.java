package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Objeto de resposta com dados do plano")
public class PlanoResponseDTO {

    @Schema(description = "ID único do plano", example = "1234")
    private Long id;

    @Schema(description = "Nome do plano", example = "Premium")
    private String nome;

    @Schema(description = "Preço do plano", example = "100.00")
    private double preco;

    @Schema(description = "Armazenamento do plano", example = "Grande")
    private String armazenamento;
}
