package org.igesta.model;

import jakarta.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
public class CondenaUnidadeId implements Serializable {

    @Column(name = "id_condena")
    private Long idCondena;
    @Column(name = "id_unidade")
    private Long idUnidade;

    public CondenaUnidadeId(Long idCondena, Long idUnidade) {
        this.idCondena = idCondena;
        this.idUnidade = idUnidade;
    }

    public Long getIdCondena() { return idCondena; }
    public void setIdCondena(Long idCondena) { this.idCondena = idCondena; }
    public Long getIdUnidade() { return idUnidade; }
    public void setIdUnidade(Long idUnidade) { this.idUnidade = idUnidade; }
}