package it.dlvsystem.web.rest;

import it.dlvsystem.domain.Esperienza;
import it.dlvsystem.repository.EsperienzaRepository;
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
 * REST controller for managing {@link it.dlvsystem.domain.Esperienza}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EsperienzaResource {

    private final Logger log = LoggerFactory.getLogger(EsperienzaResource.class);

    private static final String ENTITY_NAME = "esperienza";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EsperienzaRepository esperienzaRepository;

    public EsperienzaResource(EsperienzaRepository esperienzaRepository) {
        this.esperienzaRepository = esperienzaRepository;
    }

    /**
     * {@code POST  /esperienzas} : Create a new esperienza.
     *
     * @param esperienza the esperienza to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new esperienza, or with status {@code 400 (Bad Request)} if the esperienza has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/esperienzas")
    public ResponseEntity<Esperienza> createEsperienza(@RequestBody Esperienza esperienza) throws URISyntaxException {
        log.debug("REST request to save Esperienza : {}", esperienza);
        if (esperienza.getId() != null) {
            throw new BadRequestAlertException("A new esperienza cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Esperienza result = esperienzaRepository.save(esperienza);
        return ResponseEntity.created(new URI("/api/esperienzas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /esperienzas} : Updates an existing esperienza.
     *
     * @param esperienza the esperienza to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated esperienza,
     * or with status {@code 400 (Bad Request)} if the esperienza is not valid,
     * or with status {@code 500 (Internal Server Error)} if the esperienza couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/esperienzas")
    public ResponseEntity<Esperienza> updateEsperienza(@RequestBody Esperienza esperienza) throws URISyntaxException {
        log.debug("REST request to update Esperienza : {}", esperienza);
        if (esperienza.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Esperienza result = esperienzaRepository.save(esperienza);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, esperienza.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /esperienzas} : get all the esperienzas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of esperienzas in body.
     */
    @GetMapping("/esperienzas")
    public ResponseEntity<List<Esperienza>> getAllEsperienzas(Pageable pageable) {
        log.debug("REST request to get a page of Esperienzas");
        Page<Esperienza> page = esperienzaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /esperienzas/:id} : get the "id" esperienza.
     *
     * @param id the id of the esperienza to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the esperienza, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/esperienzas/{id}")
    public ResponseEntity<Esperienza> getEsperienza(@PathVariable Long id) {
        log.debug("REST request to get Esperienza : {}", id);
        Optional<Esperienza> esperienza = esperienzaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(esperienza);
    }

    /**
     * {@code DELETE  /esperienzas/:id} : delete the "id" esperienza.
     *
     * @param id the id of the esperienza to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/esperienzas/{id}")
    public ResponseEntity<Void> deleteEsperienza(@PathVariable Long id) {
        log.debug("REST request to delete Esperienza : {}", id);
        esperienzaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
