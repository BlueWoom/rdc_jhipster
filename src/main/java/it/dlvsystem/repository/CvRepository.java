package it.dlvsystem.repository;

import it.dlvsystem.domain.Cv;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Cv entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CvRepository extends JpaRepository<Cv, Long> {
}
