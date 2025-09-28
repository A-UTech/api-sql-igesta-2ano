package org.igesta.repository;

import org.igesta.model.Condena;
import org.igesta.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query("SELECT e FROM Empresa e WHERE e.nome = :nome")
    List<Empresa> findByNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM Empresa e WHERE LOWER(e.nome) LIKE LOWER(:nome)", nativeQuery = true)
    List<Empresa> findByParteNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM Empresa e WHERE LOWER(e.cnpj) = LOWER(:cnpj)", nativeQuery = true)
    List<Empresa> findByCnpj(@Param("cnpj") String cnpj);
}
