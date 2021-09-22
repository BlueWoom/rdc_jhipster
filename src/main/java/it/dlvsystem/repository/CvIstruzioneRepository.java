package it.dlvsystem.repository;

import it.dlvsystem.domain.CvIstruzione;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CvIstruzione entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CvIstruzioneRepository extends JpaRepository<CvIstruzione, Long> {
}
