package it.dlvsystem.web.rest;

import it.dlvsystem.domain.SkillUtente;
import it.dlvsystem.repository.SkillUtenteRepository;
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
 * REST controller for managing {@link it.dlvsystem.domain.SkillUtente}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SkillUtenteResource {

    private final Logger log = LoggerFactory.getLogger(SkillUtenteResource.class);

    private static final String ENTITY_NAME = "skillUtente";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SkillUtenteRepository skillUtenteRepository;

    public SkillUtenteResource(SkillUtenteRepository skillUtenteRepository) {
        this.skillUtenteRepository = skillUtenteRepository;
    }

    /**
     * {@code POST  /skill-utentes} : Create a new skillUtente.
     *
     * @param skillUtente the skillUtente to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new skillUtente, or with status {@code 400 (Bad Request)} if the skillUtente has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/skill-utentes")
    public ResponseEntity<SkillUtente> createSkillUtente(@RequestBody SkillUtente skillUtente) throws URISyntaxException {
        log.debug("REST request to save SkillUtente : {}", skillUtente);
        if (skillUtente.getId() != null) {
            throw new BadRequestAlertException("A new skillUtente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SkillUtente result = skillUtenteRepository.save(skillUtente);
        return ResponseEntity.created(new URI("/api/skill-utentes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /skill-utentes} : Updates an existing skillUtente.
     *
     * @param skillUtente the skillUtente to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated skillUtente,
     * or with status {@code 400 (Bad Request)} if the skillUtente is not valid,
     * or with status {@code 500 (Internal Server Error)} if the skillUtente couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/skill-utentes")
    public ResponseEntity<SkillUtente> updateSkillUtente(@RequestBody SkillUtente skillUtente) throws URISyntaxException {
        log.debug("REST request to update SkillUtente : {}", skillUtente);
        if (skillUtente.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SkillUtente result = skillUtenteRepository.save(skillUtente);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, skillUtente.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /skill-utentes} : get all the skillUtentes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of skillUtentes in body.
     */
    @GetMapping("/skill-utentes")
    public ResponseEntity<List<SkillUtente>> getAllSkillUtentes(Pageable pageable) {
        log.debug("REST request to get a page of SkillUtentes");
        Page<SkillUtente> page = skillUtenteRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /skill-utentes/:id} : get the "id" skillUtente.
     *
     * @param id the id of the skillUtente to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the skillUtente, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/skill-utentes/{id}")
    public ResponseEntity<SkillUtente> getSkillUtente(@PathVariable Long id) {
        log.debug("REST request to get SkillUtente : {}", id);
        Optional<SkillUtente> skillUtente = skillUtenteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(skillUtente);
    }

    /**
     * {@code DELETE  /skill-utentes/:id} : delete the "id" skillUtente.
     *
     * @param id the id of the skillUtente to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/skill-utentes/{id}")
    public ResponseEntity<Void> deleteSkillUtente(@PathVariable Long id) {
        log.debug("REST request to delete SkillUtente : {}", id);
        skillUtenteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
