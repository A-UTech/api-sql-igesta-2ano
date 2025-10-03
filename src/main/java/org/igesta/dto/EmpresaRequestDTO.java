package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.igesta.validation.OnCreate;

@Setter
@Getter
@Schema(description = "Objeto para criação/atualização de empresa")
public class EmpresaRequestDTO {
    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome não pode estar em branco!")
    @Schema(description = "Nome da empresa", example = "Panatem")
    private String nome;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O cnpj não pode estar em branco!")
    @Schema(description = "Cnpj da empresa", example = "77400958000102")
    private String cnpj;

}
