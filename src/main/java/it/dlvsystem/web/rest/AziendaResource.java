package it.dlvsystem.web.rest;

import it.dlvsystem.domain.Azienda;
import it.dlvsystem.repository.AziendaRepository;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link it.dlvsystem.domain.Azienda}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AziendaResource {

    private final Logger log = LoggerFactory.getLogger(AziendaResource.class);

    private static final String ENTITY_NAME = "azienda";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AziendaRepository aziendaRepository;

    public AziendaResource(AziendaRepository aziendaRepository) {
        this.aziendaRepository = aziendaRepository;
    }

    /**
     * {@code POST  /aziendas} : Create a new azienda.
     *
     * @param azienda the azienda to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new azienda, or with status {@code 400 (Bad Request)} if the azienda has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/aziendas")
    public ResponseEntity<Azienda> createAzienda(@Valid @RequestBody Azienda azienda) throws URISyntaxException {
        log.debug("REST request to save Azienda : {}", azienda);
        if (azienda.getId() != null) {
            throw new BadRequestAlertException("A new azienda cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Azienda result = aziendaRepository.save(azienda);
        return ResponseEntity.created(new URI("/api/aziendas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /aziendas} : Updates an existing azienda.
     *
     * @param azienda the azienda to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated azienda,
     * or with status {@code 400 (Bad Request)} if the azienda is not valid,
     * or with status {@code 500 (Internal Server Error)} if the azienda couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/aziendas")
    public ResponseEntity<Azienda> updateAzienda(@Valid @RequestBody Azienda azienda) throws URISyntaxException {
        log.debug("REST request to update Azienda : {}", azienda);
        if (azienda.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Azienda result = aziendaRepository.save(azienda);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, azienda.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /aziendas} : get all the aziendas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of aziendas in body.
     */
    @GetMapping("/aziendas")
    public ResponseEntity<List<Azienda>> getAllAziendas(Pageable pageable) {
        log.debug("REST request to get a page of Aziendas");
        Page<Azienda> page = aziendaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /aziendas/:id} : get the "id" azienda.
     *
     * @param id the id of the azienda to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the azienda, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/aziendas/{id}")
    public ResponseEntity<Azienda> getAzienda(@PathVariable Long id) {
        log.debug("REST request to get Azienda : {}", id);
        Optional<Azienda> azienda = aziendaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(azienda);
    }

    /**
     * {@code DELETE  /aziendas/:id} : delete the "id" azienda.
     *
     * @param id the id of the azienda to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/aziendas/{id}")
    public ResponseEntity<Void> deleteAzienda(@PathVariable Long id) {
        log.debug("REST request to delete Azienda : {}", id);
        aziendaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
