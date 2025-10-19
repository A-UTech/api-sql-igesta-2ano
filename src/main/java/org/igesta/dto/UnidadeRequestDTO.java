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
public class UnidadeRequestDTO {

    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome não pode estar em branco!")
    @NotNull(groups = OnCreate.class)
    private String nome;
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O estado não pode estar em branco!")
    @NotNull(groups = OnCreate.class)
    private String estado;
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome da cidade não pode estar em branco!")
    @NotNull(groups = OnCreate.class)
    private String cidade;
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O CNPJ não pode estar em branco!")
    @NotNull(groups = OnCreate.class)
    private String cnpj;
    @Column(name = "id_plano")
    private Long idPlano;
    @Column(name = "id_empresa")
    private Long idEmpresa;
    @Pattern(regexp = ".*\\S.*\\S.*", message = "A senha não pode estar em branco!")
    @NotNull(groups = OnCreate.class)
    private String senha;
}
