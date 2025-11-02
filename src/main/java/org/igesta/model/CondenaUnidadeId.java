package org.igesta.model;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class CondenaUnidadeId implements Serializable {

    @Column(name = "id_condena")
    private Long idCondena;
    @Column(name = "id_unidade")
    private Long idUnidade;

    public CondenaUnidadeId(Long idCondena, Long idUnidade) {
        this.idCondena = idCondena;
        this.idUnidade = idUnidade;
    }
}