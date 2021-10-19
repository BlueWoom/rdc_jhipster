package it.dlvsystem.repository;

import it.dlvsystem.domain.SkillUtente;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SkillUtente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SkillUtenteRepository extends JpaRepository<SkillUtente, Long> {
}
