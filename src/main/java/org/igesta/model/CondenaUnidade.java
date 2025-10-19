package org.igesta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "condena_unidade")
@IdClass(CondenaUnidadeId.class)
@Getter
@Setter
public class CondenaUnidade {
    @Id
    @Column(name = "id_condena")
    private Integer idCondena;

    @Id
    @Column(name = "id_unidade")
    private Integer idUnidade;

    @ManyToOne
    @JoinColumn(name = "id_condena", referencedColumnName = "id", insertable = false, updatable = false)
    private Condena condena;

    @ManyToOne
    @JoinColumn(name = "id_unidade", referencedColumnName = "id", insertable = false, updatable = false)
    private Unidade unidade;

    public CondenaUnidade() {}
}