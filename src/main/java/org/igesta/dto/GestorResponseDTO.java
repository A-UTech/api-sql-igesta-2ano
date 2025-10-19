package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Objeto de resposta com dados do gestor")
public class GestorResponseDTO {

    @Schema(description = "ID único do gestor", example = "1234")
    private Long id;

    @Column(name = "id_unidade")
    @Schema(description = "ID único da unidade que o gestor trabalha", example = "1234")
    private Integer idUnidade;

    @Schema(description = "Nome do gestor", example = "Daniel Freitas")
    private String nome;

    @Schema(description = "Email do gestor", example = "daniel.freitas@gmail.com")
    private String email;

    @Schema(description = "Senha do gestor", example = "123456")
    private String senha;

    @Schema(description = "Cpf do gestor", example = "222.222.222-22")
    private String cpf;
}
