package org.igesta.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.igesta.validation.OnCreate;

public class TurnoRequestDTO {
    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = ".*\\S.*\\S.*", message = "O nome não pode estar em branco!")
    private String nome;
    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$", message = "O horário de início deve estar no formato HH:mm!")
    private String horarioInicio;
    @NotNull(groups = OnCreate.class)
    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$", message = "O horário de fim deve estar no formato HH:mm!")
    private String horarioFim;
}
