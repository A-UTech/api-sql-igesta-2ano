package org.igesta.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GestorResponseDTO {
    private Long id;
    @Column(name = "id_empresa")
    private int idEmpresa;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
}
