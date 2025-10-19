package org.igesta.repository;

import org.igesta.model.CondenaUnidade;
import org.igesta.model.CondenaUnidadeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondenaUnidadeRepository extends JpaRepository<CondenaUnidade, CondenaUnidadeId> {
    List<CondenaUnidade> findByIdUnidade(Integer idUnidade);
    @Query("SELECT cu FROM CondenaUnidade cu JOIN FETCH cu.unidade u JOIN FETCH cu.condena c WHERE u.id = :idUnidade")
    List<CondenaUnidade> findCondenasByUnidadeIdJoinFetch(@Param("idUnidade") Integer idUnidade);
}