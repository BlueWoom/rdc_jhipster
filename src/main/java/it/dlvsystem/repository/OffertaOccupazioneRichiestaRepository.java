package it.dlvsystem.repository;

import it.dlvsystem.domain.OffertaOccupazioneRichiesta;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OffertaOccupazioneRichiesta entity.
 */
@Repository
public interface OffertaOccupazioneRichiestaRepository extends JpaRepository<OffertaOccupazioneRichiesta, Long> {

	Optional<OffertaOccupazioneRichiesta> findByCodiceOffertaAndCodiceEscoOccupazione(String codiceOfferta,
			String codiceEscoOccupazione);
}
