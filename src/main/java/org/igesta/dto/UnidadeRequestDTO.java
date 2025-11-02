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
@Schema(description = "Objeto para criação/atualização de unidade")
public class UnidadeRequestDTO {

    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome não pode estar em branco!")
    @NotNull(groups = OnCreate.class)
    @Schema(description = "Nome da unidade", example = "Panatem Osasco")
    private String nome;

    @Pattern(regexp = ".*\\S.*\\S.*", message = "O estado não pode estar em branco!")
    @NotNull(groups = OnCreate.class)
    @Schema(description = "Estado da unidade", example = "SP")
    private String estado;

    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome da cidade não pode estar em branco!")
    @NotNull(groups = OnCreate.class)
    @Schema(description = "Cidade da unidade", example = "Osasco")
    private String cidade;

    @Pattern(regexp = ".*\\S.*\\S.*", message = "O CNPJ não pode estar em branco!")
    @NotNull(groups = OnCreate.class)
    @Schema(description = "Cnpj da unidade", example = "12.345.678/0001-90")
    private String cnpj;

    @Column(name = "id_plano")
    @Schema(description = "ID único do plano que a unidade possui", example = "1234")
    private Long idPlano;

    @Column(name = "id_empresa")
    @Schema(description = "ID único da empresa que a unidade pertence", example = "1234")
    private Long idEmpresa;

    @Pattern(regexp = ".*\\S.*\\S.*", message = "A senha não pode estar em branco!")
    @NotNull(groups = OnCreate.class)
    private String senha;
}
