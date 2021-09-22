package it.dlvsystem.repository;

import it.dlvsystem.domain.Login;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Login entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
}
