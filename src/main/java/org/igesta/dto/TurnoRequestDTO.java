package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.igesta.validation.OnCreate;

@Getter
@Setter
@Schema(description = "Objeto para criação/atualização de turno")
public class TurnoRequestDTO {
    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome não pode estar em branco!")
    @Schema(description = "Nome do plano", example = "Manhã")
    private String nome;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$", message = "O horário de início deve estar no formato HH:mm!")
    @Schema(description = "Hora de início do turno", example = "07:00")
    private String inicio;

    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$", message = "O horário de fim deve estar no formato HH:mm!")
    @Schema(description = "Hora de término do turno", example = "15:00")
    private String fim;
}
