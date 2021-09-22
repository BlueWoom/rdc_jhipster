package it.dlvsystem.repository;

import it.dlvsystem.domain.Occupazione;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Occupazione entity.
 */
@Repository
public interface OccupazioneRepository extends JpaRepository<Occupazione, Long> {

    @Query(value = "select distinct occupazione from Occupazione occupazione left join fetch occupazione.esperienzas left join fetch occupazione.offertas left join fetch occupazione.skills",
        countQuery = "select count(distinct occupazione) from Occupazione occupazione")
    Page<Occupazione> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct occupazione from Occupazione occupazione left join fetch occupazione.esperienzas left join fetch occupazione.offertas left join fetch occupazione.skills")
    List<Occupazione> findAllWithEagerRelationships();

    @Query("select occupazione from Occupazione occupazione left join fetch occupazione.esperienzas left join fetch occupazione.offertas left join fetch occupazione.skills where occupazione.id =:id")
    Optional<Occupazione> findOneWithEagerRelationships(@Param("id") Long id);
}
