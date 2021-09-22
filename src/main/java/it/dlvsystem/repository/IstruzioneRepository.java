package it.dlvsystem.repository;

import it.dlvsystem.domain.Istruzione;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Istruzione entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IstruzioneRepository extends JpaRepository<Istruzione, Long> {
}
