package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Objeto de resposta com dados do líder")
public class LiderResponseDTO {

    @Schema(description = "ID único do líder", example = "1234")
    private Long id;

    @Column(name = "id_empresa")
    @Schema(description = "ID único da empresa que o líder trabalha", example = "1234")
    private Long idEmpresa;

    @Column(name = "id_empresa")
    @Schema(description = "ID único do gestor que comanda o líder", example = "1234")
    private Long idGestor;

    @Schema(description = "Nome do líder", example = "Thiago Gabriel Marinho Cardoso")
    private String nome;

    @Schema(description = "Email do líder", example = "thiago.gabriel@gmail.com")
    private String email;

    @Schema(description = "Senha do líder", example = "abcd")
    private String senha;

    @Schema(description = "Cpf do líder", example = "676.767.676-76")
    private String cpf;
}
