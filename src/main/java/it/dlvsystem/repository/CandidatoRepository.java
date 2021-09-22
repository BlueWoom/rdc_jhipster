package it.dlvsystem.repository;

import it.dlvsystem.domain.Candidato;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Candidato entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
}
