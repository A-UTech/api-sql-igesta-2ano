package org.igesta.repository;

import org.igesta.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    Turno findByIdUnidadeAndInicioAndFim(Long idUnidade, LocalTime inicio, LocalTime fim);
}
