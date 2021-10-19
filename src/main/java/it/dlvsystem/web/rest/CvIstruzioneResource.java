package it.dlvsystem.web.rest;

import it.dlvsystem.domain.CvIstruzione;
import it.dlvsystem.repository.CvIstruzioneRepository;
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
 * REST controller for managing {@link it.dlvsystem.domain.CvIstruzione}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CvIstruzioneResource {

    private final Logger log = LoggerFactory.getLogger(CvIstruzioneResource.class);

    private static final String ENTITY_NAME = "cvIstruzione";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CvIstruzioneRepository cvIstruzioneRepository;

    public CvIstruzioneResource(CvIstruzioneRepository cvIstruzioneRepository) {
        this.cvIstruzioneRepository = cvIstruzioneRepository;
    }

    /**
     * {@code POST  /cv-istruziones} : Create a new cvIstruzione.
     *
     * @param cvIstruzione the cvIstruzione to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cvIstruzione, or with status {@code 400 (Bad Request)} if the cvIstruzione has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cv-istruziones")
    public ResponseEntity<CvIstruzione> createCvIstruzione(@RequestBody CvIstruzione cvIstruzione) throws URISyntaxException {
        log.debug("REST request to save CvIstruzione : {}", cvIstruzione);
        if (cvIstruzione.getId() != null) {
            throw new BadRequestAlertException("A new cvIstruzione cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CvIstruzione result = cvIstruzioneRepository.save(cvIstruzione);
        return ResponseEntity.created(new URI("/api/cv-istruziones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cv-istruziones} : Updates an existing cvIstruzione.
     *
     * @param cvIstruzione the cvIstruzione to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cvIstruzione,
     * or with status {@code 400 (Bad Request)} if the cvIstruzione is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cvIstruzione couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cv-istruziones")
    public ResponseEntity<CvIstruzione> updateCvIstruzione(@RequestBody CvIstruzione cvIstruzione) throws URISyntaxException {
        log.debug("REST request to update CvIstruzione : {}", cvIstruzione);
        if (cvIstruzione.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CvIstruzione result = cvIstruzioneRepository.save(cvIstruzione);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cvIstruzione.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cv-istruziones} : get all the cvIstruziones.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cvIstruziones in body.
     */
    @GetMapping("/cv-istruziones")
    public ResponseEntity<List<CvIstruzione>> getAllCvIstruziones(Pageable pageable) {
        log.debug("REST request to get a page of CvIstruziones");
        Page<CvIstruzione> page = cvIstruzioneRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cv-istruziones/:id} : get the "id" cvIstruzione.
     *
     * @param id the id of the cvIstruzione to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cvIstruzione, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cv-istruziones/{id}")
    public ResponseEntity<CvIstruzione> getCvIstruzione(@PathVariable Long id) {
        log.debug("REST request to get CvIstruzione : {}", id);
        Optional<CvIstruzione> cvIstruzione = cvIstruzioneRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cvIstruzione);
    }

    /**
     * {@code DELETE  /cv-istruziones/:id} : delete the "id" cvIstruzione.
     *
     * @param id the id of the cvIstruzione to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cv-istruziones/{id}")
    public ResponseEntity<Void> deleteCvIstruzione(@PathVariable Long id) {
        log.debug("REST request to delete CvIstruzione : {}", id);
        cvIstruzioneRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
