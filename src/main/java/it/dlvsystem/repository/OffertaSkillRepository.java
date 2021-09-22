package it.dlvsystem.repository;

import it.dlvsystem.domain.OffertaSkill;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OffertaSkill entity.
 */
@Repository
public interface OffertaSkillRepository extends JpaRepository<OffertaSkill, Long> {

	Optional<OffertaSkill> findByCodiceOffertaAndCodiceEscoSkill(String codiceOfferta, String codiceEscoSkill);
}
