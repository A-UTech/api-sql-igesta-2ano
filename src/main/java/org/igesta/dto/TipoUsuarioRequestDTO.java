package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.igesta.validation.OnCreate;

@Getter
@Setter
@Schema(description = "Objeto para autenticação e identificação do tipo de usuário (gestor, líder ou unidade)")
public class TipoUsuarioRequestDTO {

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*", message = "O email ou CNPJ não pode estar em branco!")
    @Schema(description = "Email (para gestor/líder) ou CNPJ (para unidade)", example = "daniel.freitas@gmail.com")
    private String emailCnpj;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*", message = "A senha não pode estar em branco!")
    @Schema(description = "Senha do usuário", example = "1234")
    private String senha;
}
