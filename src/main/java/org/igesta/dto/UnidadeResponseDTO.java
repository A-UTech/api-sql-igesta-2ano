package org.igesta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Objeto de resposta com dados da unidade")
public class UnidadeResponseDTO {

    private Long id;
    @Schema(description = "Nome da unidade", example = "Panatem Osasco")
    private String nome;
    @Schema(description = "Estado da unidade", example = "SP")
    private String estado;
    @Schema(description = "Cidade da unidade", example = "Osasco")
    private String cidade;
    @Schema(description = "Cnpj da unidade", example = "12.345.678/0001-90")
    private String cnpj;
    @Column(name = "id_plano")
    @Schema(description = "ID único do plano que a unidade possui", example = "1234")
    private Long idPlano;
    @Column(name = "id_empresa")
    @Schema(description = "ID único da empresa que a unidade pertence", example = "1234")
    private Long idEmpresa;
    private String senha;
}
