package it.dlvsystem.repository;

import it.dlvsystem.domain.OffertaSkill;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the OffertaSkill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OffertaSkillRepository extends JpaRepository<OffertaSkill, Long> {
}
