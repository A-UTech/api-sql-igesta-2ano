package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Objeto para realização do Login")
public class AuthRequestDTO {
    @Schema(description = "E-mail ou CNPJ do usuário", example = "usuario@gmail.com")
    private String emailCnpj;

    @Schema(description = "Senha do usuário", example = "123456")
    private String senha;
}
