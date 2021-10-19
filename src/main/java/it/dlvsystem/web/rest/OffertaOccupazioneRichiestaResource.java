package it.dlvsystem.web.rest;

import it.dlvsystem.domain.OffertaOccupazioneRichiesta;
import it.dlvsystem.repository.OffertaOccupazioneRichiestaRepository;
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
 * REST controller for managing {@link it.dlvsystem.domain.OffertaOccupazioneRichiesta}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OffertaOccupazioneRichiestaResource {

    private final Logger log = LoggerFactory.getLogger(OffertaOccupazioneRichiestaResource.class);

    private static final String ENTITY_NAME = "offertaOccupazioneRichiesta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OffertaOccupazioneRichiestaRepository offertaOccupazioneRichiestaRepository;

    public OffertaOccupazioneRichiestaResource(OffertaOccupazioneRichiestaRepository offertaOccupazioneRichiestaRepository) {
        this.offertaOccupazioneRichiestaRepository = offertaOccupazioneRichiestaRepository;
    }

    /**
     * {@code POST  /offerta-occupazione-richiestas} : Create a new offertaOccupazioneRichiesta.
     *
     * @param offertaOccupazioneRichiesta the offertaOccupazioneRichiesta to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offertaOccupazioneRichiesta, or with status {@code 400 (Bad Request)} if the offertaOccupazioneRichiesta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offerta-occupazione-richiestas")
    public ResponseEntity<OffertaOccupazioneRichiesta> createOffertaOccupazioneRichiesta(@RequestBody OffertaOccupazioneRichiesta offertaOccupazioneRichiesta) throws URISyntaxException {
        log.debug("REST request to save OffertaOccupazioneRichiesta : {}", offertaOccupazioneRichiesta);
        if (offertaOccupazioneRichiesta.getId() != null) {
            throw new BadRequestAlertException("A new offertaOccupazioneRichiesta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OffertaOccupazioneRichiesta result = offertaOccupazioneRichiestaRepository.save(offertaOccupazioneRichiesta);
        return ResponseEntity.created(new URI("/api/offerta-occupazione-richiestas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offerta-occupazione-richiestas} : Updates an existing offertaOccupazioneRichiesta.
     *
     * @param offertaOccupazioneRichiesta the offertaOccupazioneRichiesta to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offertaOccupazioneRichiesta,
     * or with status {@code 400 (Bad Request)} if the offertaOccupazioneRichiesta is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offertaOccupazioneRichiesta couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offerta-occupazione-richiestas")
    public ResponseEntity<OffertaOccupazioneRichiesta> updateOffertaOccupazioneRichiesta(@RequestBody OffertaOccupazioneRichiesta offertaOccupazioneRichiesta) throws URISyntaxException {
        log.debug("REST request to update OffertaOccupazioneRichiesta : {}", offertaOccupazioneRichiesta);
        if (offertaOccupazioneRichiesta.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OffertaOccupazioneRichiesta result = offertaOccupazioneRichiestaRepository.save(offertaOccupazioneRichiesta);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offertaOccupazioneRichiesta.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offerta-occupazione-richiestas} : get all the offertaOccupazioneRichiestas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offertaOccupazioneRichiestas in body.
     */
    @GetMapping("/offerta-occupazione-richiestas")
    public ResponseEntity<List<OffertaOccupazioneRichiesta>> getAllOffertaOccupazioneRichiestas(Pageable pageable) {
        log.debug("REST request to get a page of OffertaOccupazioneRichiestas");
        Page<OffertaOccupazioneRichiesta> page = offertaOccupazioneRichiestaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /offerta-occupazione-richiestas/:id} : get the "id" offertaOccupazioneRichiesta.
     *
     * @param id the id of the offertaOccupazioneRichiesta to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offertaOccupazioneRichiesta, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offerta-occupazione-richiestas/{id}")
    public ResponseEntity<OffertaOccupazioneRichiesta> getOffertaOccupazioneRichiesta(@PathVariable Long id) {
        log.debug("REST request to get OffertaOccupazioneRichiesta : {}", id);
        Optional<OffertaOccupazioneRichiesta> offertaOccupazioneRichiesta = offertaOccupazioneRichiestaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(offertaOccupazioneRichiesta);
    }

    /**
     * {@code DELETE  /offerta-occupazione-richiestas/:id} : delete the "id" offertaOccupazioneRichiesta.
     *
     * @param id the id of the offertaOccupazioneRichiesta to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offerta-occupazione-richiestas/{id}")
    public ResponseEntity<Void> deleteOffertaOccupazioneRichiesta(@PathVariable Long id) {
        log.debug("REST request to delete OffertaOccupazioneRichiesta : {}", id);
        offertaOccupazioneRichiestaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
