package it.dlvsystem.repository;

import it.dlvsystem.domain.Navigator;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Navigator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NavigatorRepository extends JpaRepository<Navigator, Long> {
}
