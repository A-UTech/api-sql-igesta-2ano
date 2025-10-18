package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Objeto de resposta com o tipo de usuário e seu identificador")
public class TipoUsuarioResponseDTO {

    @Schema(description = "ID único do usuário retornado pela função", example = "1")
    private Long id;

    @Schema(description = "Tipo do usuário (gestor, líder ou unidade)", example = "gestor")
    private String tipoUsuario;
}
