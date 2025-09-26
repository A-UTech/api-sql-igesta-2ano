package org.igesta.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.igesta.validation.OnCreate;

@Setter
@Getter
public class EmpresaRequestDTO {
    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome não pode estar em branco!")
    private String nome;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O cnpj não pode estar em branco!")
    private String cnpj;

}
