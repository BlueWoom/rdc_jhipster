package it.dlvsystem.web.rest;

import it.dlvsystem.domain.OffertaSkill;
import it.dlvsystem.repository.OffertaSkillRepository;
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
 * REST controller for managing {@link it.dlvsystem.domain.OffertaSkill}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OffertaSkillResource {

    private final Logger log = LoggerFactory.getLogger(OffertaSkillResource.class);

    private static final String ENTITY_NAME = "offertaSkill";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OffertaSkillRepository offertaSkillRepository;

    public OffertaSkillResource(OffertaSkillRepository offertaSkillRepository) {
        this.offertaSkillRepository = offertaSkillRepository;
    }

    /**
     * {@code POST  /offerta-skills} : Create a new offertaSkill.
     *
     * @param offertaSkill the offertaSkill to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offertaSkill, or with status {@code 400 (Bad Request)} if the offertaSkill has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offerta-skills")
    public ResponseEntity<OffertaSkill> createOffertaSkill(@Valid @RequestBody OffertaSkill offertaSkill) throws URISyntaxException {
        log.debug("REST request to save OffertaSkill : {}", offertaSkill);
        if (offertaSkill.getId() != null) {
            throw new BadRequestAlertException("A new offertaSkill cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        Optional<OffertaSkill> alreadyExistingOffertaSkill = offertaSkillRepository.findByCodiceOffertaAndCodiceEscoSkill(offertaSkill.getCodiceOfferta(), offertaSkill.getCodiceEscoSkill());
        if(alreadyExistingOffertaSkill.isPresent()) {
        	throw new BadRequestAlertException("An old offertaSkill already has same codiceOfferta/codiceEscoSkill", ENTITY_NAME, "idexists");
        }
        
        OffertaSkill result = offertaSkillRepository.save(offertaSkill);
        return ResponseEntity.created(new URI("/api/offerta-skills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offerta-skills} : Updates an existing offertaSkill.
     *
     * @param offertaSkill the offertaSkill to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offertaSkill,
     * or with status {@code 400 (Bad Request)} if the offertaSkill is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offertaSkill couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offerta-skills")
    public ResponseEntity<OffertaSkill> updateOffertaSkill(@Valid @RequestBody OffertaSkill offertaSkill) throws URISyntaxException {
        log.debug("REST request to update OffertaSkill : {}", offertaSkill);
        if (offertaSkill.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OffertaSkill result = offertaSkillRepository.save(offertaSkill);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offertaSkill.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offerta-skills} : get all the offertaSkills.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offertaSkills in body.
     */
    @GetMapping("/offerta-skills")
    public List<OffertaSkill> getAllOffertaSkills() {
        log.debug("REST request to get all OffertaSkills");
        return offertaSkillRepository.findAll();
    }

    /**
     * {@code GET  /offerta-skills/:id} : get the "id" offertaSkill.
     *
     * @param id the id of the offertaSkill to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offertaSkill, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offerta-skills/{id}")
    public ResponseEntity<OffertaSkill> getOffertaSkill(@PathVariable Long id) {
        log.debug("REST request to get OffertaSkill : {}", id);
        Optional<OffertaSkill> offertaSkill = offertaSkillRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(offertaSkill);
    }

    /**
     * {@code DELETE  /offerta-skills/:id} : delete the "id" offertaSkill.
     *
     * @param id the id of the offertaSkill to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offerta-skills/{id}")
    public ResponseEntity<Void> deleteOffertaSkill(@PathVariable Long id) {
        log.debug("REST request to delete OffertaSkill : {}", id);
        offertaSkillRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
