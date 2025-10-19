package org.igesta.repository;

import org.igesta.model.Empresa;
import org.igesta.model.Gestor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GestorRepository extends JpaRepository<Gestor, Long> {
    @Query("SELECT g FROM Gestor g WHERE g.nome = :nome")
    List<Gestor> findByNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM Gestor g WHERE LOWER(g.nome) LIKE LOWER(:nome)", nativeQuery = true)
    List<Gestor> findByParteNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM Gestor g WHERE LOWER(g.cpf) = LOWER(:cpf)", nativeQuery = true)
    List<Gestor> findByCpf(@Param("cpf") String cpf);
}
