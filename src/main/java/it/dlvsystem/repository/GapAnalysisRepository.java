package it.dlvsystem.repository;

import it.dlvsystem.domain.GapAnalysis;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the GapAnalysis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GapAnalysisRepository extends JpaRepository<GapAnalysis, Long> {
}
