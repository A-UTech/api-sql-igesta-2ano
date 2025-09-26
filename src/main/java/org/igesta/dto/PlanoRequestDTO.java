package org.igesta.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.igesta.validation.OnCreate;

public class PlanoRequestDTO {
    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome não pode estar em branco!")
    private String nome;
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero!")
    @NotNull(groups = OnCreate.class)
    private double preco;
    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O armazenamento não pode estar em branco!")
    private String armazenamento;
}
