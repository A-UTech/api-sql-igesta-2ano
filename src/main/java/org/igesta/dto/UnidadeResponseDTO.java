package org.igesta.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeResponseDTO {

    private Long id;
    private String nome;
    private String estado;
    private String cidade;
    private String cnpj;
    @Column(name = "id_plano")
    private Long idPlano;
    @Column(name = "id_empresa")
    private Long idEmpresa;
    private String senha;
}
