package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.igesta.validation.OnCreate;

@Setter
@Getter
@Schema(description = "Objeto para criação/atualização de condena")
public class CondenaRequestDTO {

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome não pode estar em branco!")
    @Schema(description = "Nome da condena", example = "Artrite T")
    private String nome;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O tipo não pode estar em branco!")
    @Schema(description = "Tipo da condena", example = "Total")
    private String tipo;

}
