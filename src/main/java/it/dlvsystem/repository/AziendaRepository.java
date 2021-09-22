package it.dlvsystem.repository;

import it.dlvsystem.domain.Azienda;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Azienda entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AziendaRepository extends JpaRepository<Azienda, Long> {
}
