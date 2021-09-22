package it.dlvsystem.repository;

import it.dlvsystem.domain.CvIstruzione;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CvIstruzione entity.
 */
@Repository
public interface CvIstruzioneRepository extends JpaRepository<CvIstruzione, Long> {

	Optional<CvIstruzione> findByCodiceIstruzioneAndCfUtenteAndCodiceCv(String codiceCv, String cfUtente,
			String codiceCv2);
}
