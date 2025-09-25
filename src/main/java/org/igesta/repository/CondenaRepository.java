package org.igesta.repository;

import org.igesta.model.Condena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CondenaRepository extends JpaRepository<Condena, Long> {

    @Query("SELECT c FROM Condena c WHERE c.nome = :nome")
    List<Condena> findByNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM Condena c WHERE LOWER(p.nome) LIKE LOWER(:nome)", nativeQuery = true)
    List<Condena> findByParteNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM Condena c WHERE LOWER(c.tipo) = LOWER(:tipo)", nativeQuery = true)
    List<Condena> findByTipo(@Param("tipo") String tipo);
}
