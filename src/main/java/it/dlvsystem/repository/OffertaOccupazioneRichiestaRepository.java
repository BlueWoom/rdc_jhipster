package it.dlvsystem.repository;

import it.dlvsystem.domain.OffertaOccupazioneRichiesta;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OffertaOccupazioneRichiesta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OffertaOccupazioneRichiestaRepository extends JpaRepository<OffertaOccupazioneRichiesta, Long> {
}
