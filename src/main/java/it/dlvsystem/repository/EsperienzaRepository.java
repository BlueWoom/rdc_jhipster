package it.dlvsystem.repository;

import it.dlvsystem.domain.Esperienza;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Esperienza entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EsperienzaRepository extends JpaRepository<Esperienza, Long> {
}
