package it.dlvsystem.repository;

import it.dlvsystem.domain.SkillUtente;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SkillUtente entity.
 */
@Repository
public interface SkillUtenteRepository extends JpaRepository<SkillUtente, Long> {

	Optional<SkillUtente> findByCfUtenteAndCodiceEscoSkill(String cfUtente, String codiceEscoSkill);
}
