package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.igesta.validation.OnCreate;

@Setter
@Getter
@Schema(description = "Objeto para criação/atualização de líder")
public class LiderRequestDTO {

    @Column(name = "id_unidade")
    @Schema(description = "ID único da empresa que o líder trabalha", example = "1234")
    private Long idUnidade;
    @Column(name = "id_empresa")
    @Schema(description = "ID único da empresa que o líder trabalha", example = "1234")
    private Long idEmpresa;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome não pode estar em branco!")
    @Schema(description = "Nome do líder", example = "Thiago Gabriel Marinho Cardoso")
    private String nome;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O e-mail não pode estar em branco!")
    @Schema(description = "Email do líder", example = "thiago.gabriel@gmail.com")
    private String email;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "A senha não pode estar em branco!")
    @Schema(description = "Senha do líder", example = "abcd")
    private String senha;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O CPF não pode estar em branco!")
    @Schema(description = "Cpf do líder", example = "676.767.676-76")
    private String cpf;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "A área não pode estar em branco!")
    @Schema(description = "Área do líder", example = "Área Fria")
    private String area;
}
