package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.igesta.validation.OnCreate;

@Getter
@Setter
@Schema(description = "Objeto para criação/atualização de gestor")
public class GestorRequestDTO {
    @Column(name = "id_empresa")
    @Schema(description = "ID único da empresa que ogestor trabalha", example = "1234")
    private Integer idEmpresa;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome não pode estar em branco!")
    @Schema(description = "Nome do gestor", example = "Daniel Freitas")
    private String nome;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O email não pode estar em branco!")
    @Schema(description = "Email do gestor", example = "daniel.freitas@gmail.com")
    private String email;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "A senha não pode estar em branco!")
    @Schema(description = "Senha do gestor", example = "123456")
    private String senha;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O cpf não pode estar em branco!")
    @Schema(description = "Cpf do gestor", example = "222.222.222-22")
    private String cpf;
}
