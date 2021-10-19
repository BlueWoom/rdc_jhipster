package it.dlvsystem.web.rest;

import it.dlvsystem.domain.Occupazione;
import it.dlvsystem.repository.OccupazioneRepository;
import it.dlvsystem.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link it.dlvsystem.domain.Occupazione}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OccupazioneResource {

    private final Logger log = LoggerFactory.getLogger(OccupazioneResource.class);

    private static final String ENTITY_NAME = "occupazione";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OccupazioneRepository occupazioneRepository;

    public OccupazioneResource(OccupazioneRepository occupazioneRepository) {
        this.occupazioneRepository = occupazioneRepository;
    }

    /**
     * {@code POST  /occupaziones} : Create a new occupazione.
     *
     * @param occupazione the occupazione to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new occupazione, or with status {@code 400 (Bad Request)} if the occupazione has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/occupaziones")
    public ResponseEntity<Occupazione> createOccupazione(@RequestBody Occupazione occupazione) throws URISyntaxException {
        log.debug("REST request to save Occupazione : {}", occupazione);
        if (occupazione.getId() != null) {
            throw new BadRequestAlertException("A new occupazione cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Occupazione result = occupazioneRepository.save(occupazione);
        return ResponseEntity.created(new URI("/api/occupaziones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /occupaziones} : Updates an existing occupazione.
     *
     * @param occupazione the occupazione to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated occupazione,
     * or with status {@code 400 (Bad Request)} if the occupazione is not valid,
     * or with status {@code 500 (Internal Server Error)} if the occupazione couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/occupaziones")
    public ResponseEntity<Occupazione> updateOccupazione(@RequestBody Occupazione occupazione) throws URISyntaxException {
        log.debug("REST request to update Occupazione : {}", occupazione);
        if (occupazione.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Occupazione result = occupazioneRepository.save(occupazione);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, occupazione.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /occupaziones} : get all the occupaziones.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of occupaziones in body.
     */
    @GetMapping("/occupaziones")
    public ResponseEntity<List<Occupazione>> getAllOccupaziones(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Occupaziones");
        Page<Occupazione> page;
        if (eagerload) {
            page = occupazioneRepository.findAllWithEagerRelationships(pageable);
        } else {
            page = occupazioneRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /occupaziones/:id} : get the "id" occupazione.
     *
     * @param id the id of the occupazione to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the occupazione, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/occupaziones/{id}")
    public ResponseEntity<Occupazione> getOccupazione(@PathVariable Long id) {
        log.debug("REST request to get Occupazione : {}", id);
        Optional<Occupazione> occupazione = occupazioneRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(occupazione);
    }

    /**
     * {@code DELETE  /occupaziones/:id} : delete the "id" occupazione.
     *
     * @param id the id of the occupazione to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/occupaziones/{id}")
    public ResponseEntity<Void> deleteOccupazione(@PathVariable Long id) {
        log.debug("REST request to delete Occupazione : {}", id);
        occupazioneRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
