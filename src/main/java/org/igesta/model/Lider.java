package org.igesta.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_empresa")
    private Long idEmpresa;
    @Column(name = "id_gestor")
    private Long idGestor;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
}

