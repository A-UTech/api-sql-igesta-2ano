package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.igesta.validation.OnCreate;

@Getter
@Setter
@Schema(description = "Objeto para criação/atualização de admin")
public class AdminRequestDTO {
    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome não pode estar em branco!")
    @Schema(description = "Nome do admin", example = "Samuel Mauricio")
    private String nome;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O email não pode estar em branco!")
    @Schema(description = "Email do admin", example = "samuel.evangelista@gmail.com")
    private String email;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "A senha não pode estar em branco!")
    @Schema(description = "Senha do admin", example = "123456")
    private String senha;
}
