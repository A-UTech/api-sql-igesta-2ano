package org.igesta.repository;

import org.igesta.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query("SELECT e FROM Admin e WHERE e.nome = :nome")
    List<Admin> findByNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM Admin e WHERE LOWER(e.nome) LIKE LOWER(:nome)", nativeQuery = true)
    List<Admin> findByParteNome(@Param("nome") String nome);

    @Query(value = "SELECT * FROM Admin e WHERE LOWER(e.email) = LOWER(:email)", nativeQuery = true)
    List<Admin> findByEmail(@Param("email") String email);
}
