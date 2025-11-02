package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.igesta.validation.OnCreate;

import java.time.LocalTime;

@Getter
@Setter
@Schema(description = "Objeto para criação/atualização de turno")
public class TurnoRequestDTO {
    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome não pode estar em branco!")
    @Schema(description = "Nome do turno", example = "Manhã")
    private String nome;

    @NotNull(groups = OnCreate.class)
    @Schema(description = "Hora de início do turno", example = "07:00")
    private LocalTime inicio;

    @NotNull(groups = OnCreate.class)
    @Schema(description = "Hora de término do turno", example = "15:00")
    private LocalTime fim;

    @NotNull(groups = OnCreate.class)
    @NegativeOrZero(message = "O ID da unidade deve ser um número positivo ou zero!")
    @Schema(description = "ID da unidade associada ao turno", example = "1")
    private Long idUnidade;
}
