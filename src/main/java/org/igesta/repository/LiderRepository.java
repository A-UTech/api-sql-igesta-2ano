package org.igesta.repository;

import org.igesta.model.Lider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LiderRepository extends JpaRepository<Lider, Long> {

    @Query("SELECT l FROM Lider l WHERE l.nome = :nome")
    List<Lider> findByNome(@Param("nome") String nome);

}
