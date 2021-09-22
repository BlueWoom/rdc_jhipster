package it.dlvsystem.web.rest;

import it.dlvsystem.domain.GapAnalysis;
import it.dlvsystem.repository.GapAnalysisRepository;
import it.dlvsystem.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link it.dlvsystem.domain.GapAnalysis}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class GapAnalysisResource {

    private final Logger log = LoggerFactory.getLogger(GapAnalysisResource.class);

    private static final String ENTITY_NAME = "gapAnalysis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GapAnalysisRepository gapAnalysisRepository;

    public GapAnalysisResource(GapAnalysisRepository gapAnalysisRepository) {
        this.gapAnalysisRepository = gapAnalysisRepository;
    }

    /**
     * {@code POST  /gap-analyses} : Create a new gapAnalysis.
     *
     * @param gapAnalysis the gapAnalysis to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gapAnalysis, or with status {@code 400 (Bad Request)} if the gapAnalysis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gap-analyses")
    public ResponseEntity<GapAnalysis> createGapAnalysis(@RequestBody GapAnalysis gapAnalysis) throws URISyntaxException {
        log.debug("REST request to save GapAnalysis : {}", gapAnalysis);
        if (gapAnalysis.getId() != null) {
            throw new BadRequestAlertException("A new gapAnalysis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GapAnalysis result = gapAnalysisRepository.save(gapAnalysis);
        return ResponseEntity.created(new URI("/api/gap-analyses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gap-analyses} : Updates an existing gapAnalysis.
     *
     * @param gapAnalysis the gapAnalysis to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gapAnalysis,
     * or with status {@code 400 (Bad Request)} if the gapAnalysis is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gapAnalysis couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gap-analyses")
    public ResponseEntity<GapAnalysis> updateGapAnalysis(@RequestBody GapAnalysis gapAnalysis) throws URISyntaxException {
        log.debug("REST request to update GapAnalysis : {}", gapAnalysis);
        if (gapAnalysis.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GapAnalysis result = gapAnalysisRepository.save(gapAnalysis);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, gapAnalysis.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gap-analyses} : get all the gapAnalyses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gapAnalyses in body.
     */
    @GetMapping("/gap-analyses")
    public List<GapAnalysis> getAllGapAnalyses() {
        log.debug("REST request to get all GapAnalyses");
        return gapAnalysisRepository.findAll();
    }

    /**
     * {@code GET  /gap-analyses/:id} : get the "id" gapAnalysis.
     *
     * @param id the id of the gapAnalysis to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gapAnalysis, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gap-analyses/{id}")
    public ResponseEntity<GapAnalysis> getGapAnalysis(@PathVariable Long id) {
        log.debug("REST request to get GapAnalysis : {}", id);
        Optional<GapAnalysis> gapAnalysis = gapAnalysisRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(gapAnalysis);
    }

    /**
     * {@code DELETE  /gap-analyses/:id} : delete the "id" gapAnalysis.
     *
     * @param id the id of the gapAnalysis to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gap-analyses/{id}")
    public ResponseEntity<Void> deleteGapAnalysis(@PathVariable Long id) {
        log.debug("REST request to delete GapAnalysis : {}", id);
        gapAnalysisRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
