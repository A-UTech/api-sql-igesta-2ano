package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Objeto de resposta com dados da empresa")
public class EmpresaResponseDTO {
    @Schema(description = "ID único da empresa", example = "1234")
    private Long id;
    @Schema(description = "Nome da empresa", example = "Panatem")
    private String nome;
    @Schema(description = "Cnpj da empresa", example = "77400958000102")
    private String cnpj;
}
