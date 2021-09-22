package it.dlvsystem.repository;

import it.dlvsystem.domain.Cv;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Cv entity.
 */
@Repository
public interface CvRepository extends JpaRepository<Cv, Long> {

	Optional<Cv> findByCfUtenteAndCodice(String cfUtente, String codice);
}
