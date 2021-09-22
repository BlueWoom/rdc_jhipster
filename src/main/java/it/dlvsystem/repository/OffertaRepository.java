package it.dlvsystem.repository;

import it.dlvsystem.domain.Offerta;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Offerta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OffertaRepository extends JpaRepository<Offerta, Long> {
}
