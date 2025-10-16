package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.igesta.validation.OnCreate;

@Getter
@Setter
@Schema(description = "Objeto para criação/atualização de plano")
public class PlanoRequestDTO {
    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome não pode estar em branco!")
    @Schema(description = "Nome do plano", example = "Premium")
    private String nome;

    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero!")
    @NotNull(groups = OnCreate.class)
    @Schema(description = "Preço do plano", example = "100.00")
    private double preco;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O armazenamento não pode estar em branco!")
    @Schema(description = "Armazenamento do plano", example = "Grande")
    private String armazenamento;
}
