package org.igesta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Condena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String tipo;

    @OneToMany(mappedBy = "condena", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<CondenaUnidade> unidadeCondenas = new HashSet<>();
}
