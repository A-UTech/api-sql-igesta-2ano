package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Objeto de resposta com dados do admin")
public class AdminResponseDTO {
    @Schema(description = "ID único do admin", example = "1234")
    private Long id;

    @Schema(description = "Nome do admin", example = "Samuel Mauricio")
    private String nome;

    @Schema(description = "Email do admin", example = "samuel.evangelista@gmail.com")
    private String email;

    @Schema(description = "Senha do admin", example = "123456")
    private String senha;
}
