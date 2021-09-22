package it.dlvsystem.web.rest;

import it.dlvsystem.domain.Istruzione;
import it.dlvsystem.repository.IstruzioneRepository;
import it.dlvsystem.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link it.dlvsystem.domain.Istruzione}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class IstruzioneResource {

    private final Logger log = LoggerFactory.getLogger(IstruzioneResource.class);

    private static final String ENTITY_NAME = "istruzione";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IstruzioneRepository istruzioneRepository;

    public IstruzioneResource(IstruzioneRepository istruzioneRepository) {
        this.istruzioneRepository = istruzioneRepository;
    }

    /**
     * {@code POST  /istruziones} : Create a new istruzione.
     *
     * @param istruzione the istruzione to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new istruzione, or with status {@code 400 (Bad Request)} if the istruzione has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/istruziones")
    public ResponseEntity<Istruzione> createIstruzione(@Valid @RequestBody Istruzione istruzione) throws URISyntaxException {
        log.debug("REST request to save Istruzione : {}", istruzione);
        if (istruzione.getId() != null) {
            throw new BadRequestAlertException("A new istruzione cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Istruzione result = istruzioneRepository.save(istruzione);
        return ResponseEntity.created(new URI("/api/istruziones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /istruziones} : Updates an existing istruzione.
     *
     * @param istruzione the istruzione to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated istruzione,
     * or with status {@code 400 (Bad Request)} if the istruzione is not valid,
     * or with status {@code 500 (Internal Server Error)} if the istruzione couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/istruziones")
    public ResponseEntity<Istruzione> updateIstruzione(@Valid @RequestBody Istruzione istruzione) throws URISyntaxException {
        log.debug("REST request to update Istruzione : {}", istruzione);
        if (istruzione.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Istruzione result = istruzioneRepository.save(istruzione);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, istruzione.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /istruziones} : get all the istruziones.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of istruziones in body.
     */
    @GetMapping("/istruziones")
    public List<Istruzione> getAllIstruziones() {
        log.debug("REST request to get all Istruziones");
        return istruzioneRepository.findAll();
    }

    /**
     * {@code GET  /istruziones/:id} : get the "id" istruzione.
     *
     * @param id the id of the istruzione to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the istruzione, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/istruziones/{id}")
    public ResponseEntity<Istruzione> getIstruzione(@PathVariable Long id) {
        log.debug("REST request to get Istruzione : {}", id);
        Optional<Istruzione> istruzione = istruzioneRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(istruzione);
    }

    /**
     * {@code DELETE  /istruziones/:id} : delete the "id" istruzione.
     *
     * @param id the id of the istruzione to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/istruziones/{id}")
    public ResponseEntity<Void> deleteIstruzione(@PathVariable Long id) {
        log.debug("REST request to delete Istruzione : {}", id);
        istruzioneRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
