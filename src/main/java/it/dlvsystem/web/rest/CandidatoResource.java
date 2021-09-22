package it.dlvsystem.web.rest;

import it.dlvsystem.domain.Candidato;
import it.dlvsystem.repository.CandidatoRepository;
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
 * REST controller for managing {@link it.dlvsystem.domain.Candidato}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CandidatoResource {

    private final Logger log = LoggerFactory.getLogger(CandidatoResource.class);

    private static final String ENTITY_NAME = "candidato";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CandidatoRepository candidatoRepository;

    public CandidatoResource(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    /**
     * {@code POST  /candidatoes} : Create a new candidato.
     *
     * @param candidato the candidato to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new candidato, or with status {@code 400 (Bad Request)} if the candidato has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/candidatoes")
    public ResponseEntity<Candidato> createCandidato(@Valid @RequestBody Candidato candidato) throws URISyntaxException {
        log.debug("REST request to save Candidato : {}", candidato);
        if (candidato.getId() != null) {
            throw new BadRequestAlertException("A new candidato cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Candidato result = candidatoRepository.save(candidato);
        return ResponseEntity.created(new URI("/api/candidatoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /candidatoes} : Updates an existing candidato.
     *
     * @param candidato the candidato to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated candidato,
     * or with status {@code 400 (Bad Request)} if the candidato is not valid,
     * or with status {@code 500 (Internal Server Error)} if the candidato couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/candidatoes")
    public ResponseEntity<Candidato> updateCandidato(@Valid @RequestBody Candidato candidato) throws URISyntaxException {
        log.debug("REST request to update Candidato : {}", candidato);
        if (candidato.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Candidato result = candidatoRepository.save(candidato);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, candidato.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /candidatoes} : get all the candidatoes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of candidatoes in body.
     */
    @GetMapping("/candidatoes")
    public List<Candidato> getAllCandidatoes() {
        log.debug("REST request to get all Candidatoes");
        return candidatoRepository.findAll();
    }

    /**
     * {@code GET  /candidatoes/:id} : get the "id" candidato.
     *
     * @param id the id of the candidato to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the candidato, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/candidatoes/{id}")
    public ResponseEntity<Candidato> getCandidato(@PathVariable Long id) {
        log.debug("REST request to get Candidato : {}", id);
        Optional<Candidato> candidato = candidatoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(candidato);
    }

    /**
     * {@code DELETE  /candidatoes/:id} : delete the "id" candidato.
     *
     * @param id the id of the candidato to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/candidatoes/{id}")
    public ResponseEntity<Void> deleteCandidato(@PathVariable Long id) {
        log.debug("REST request to delete Candidato : {}", id);
        candidatoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
