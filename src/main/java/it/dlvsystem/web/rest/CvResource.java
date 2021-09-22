package it.dlvsystem.web.rest;

import it.dlvsystem.domain.Cv;
import it.dlvsystem.repository.CvRepository;
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
 * REST controller for managing {@link it.dlvsystem.domain.Cv}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CvResource {

    private final Logger log = LoggerFactory.getLogger(CvResource.class);

    private static final String ENTITY_NAME = "cv";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CvRepository cvRepository;

    public CvResource(CvRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    /**
     * {@code POST  /cvs} : Create a new cv.
     *
     * @param cv the cv to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cv, or with status {@code 400 (Bad Request)} if the cv has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cvs")
    public ResponseEntity<Cv> createCv(@Valid @RequestBody Cv cv) throws URISyntaxException {
        log.debug("REST request to save Cv : {}", cv);
        if (cv.getId() != null) {
            throw new BadRequestAlertException("A new cv cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Cv result = cvRepository.save(cv);
        return ResponseEntity.created(new URI("/api/cvs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cvs} : Updates an existing cv.
     *
     * @param cv the cv to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cv,
     * or with status {@code 400 (Bad Request)} if the cv is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cv couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cvs")
    public ResponseEntity<Cv> updateCv(@Valid @RequestBody Cv cv) throws URISyntaxException {
        log.debug("REST request to update Cv : {}", cv);
        if (cv.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Cv result = cvRepository.save(cv);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cv.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cvs} : get all the cvs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cvs in body.
     */
    @GetMapping("/cvs")
    public List<Cv> getAllCvs() {
        log.debug("REST request to get all Cvs");
        return cvRepository.findAll();
    }

    /**
     * {@code GET  /cvs/:id} : get the "id" cv.
     *
     * @param id the id of the cv to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cv, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cvs/{id}")
    public ResponseEntity<Cv> getCv(@PathVariable Long id) {
        log.debug("REST request to get Cv : {}", id);
        Optional<Cv> cv = cvRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cv);
    }

    /**
     * {@code DELETE  /cvs/:id} : delete the "id" cv.
     *
     * @param id the id of the cv to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cvs/{id}")
    public ResponseEntity<Void> deleteCv(@PathVariable Long id) {
        log.debug("REST request to delete Cv : {}", id);
        cvRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
