package org.igesta.model;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
public class CondenaUnidadeId implements Serializable {

    @Column(name = "id_condena")
    private Integer idCondena;
    @Column(name = "id_unidade")
    private Integer idUnidade;

    public CondenaUnidadeId(Integer idCondena, Integer idUnidade) {
        this.idCondena = idCondena;
        this.idUnidade = idUnidade;
    }

    public Integer getIdCondena() { return idCondena; }
    public void setIdCondena(Integer idCondena) { this.idCondena = idCondena; }
    public Integer getIdUnidade() { return idUnidade; }
    public void setIdUnidade(Integer idUnidade) { this.idUnidade = idUnidade; }
}