package it.dlvsystem.web.rest;

import it.dlvsystem.domain.Offerta;
import it.dlvsystem.repository.OffertaRepository;
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
 * REST controller for managing {@link it.dlvsystem.domain.Offerta}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OffertaResource {

    private final Logger log = LoggerFactory.getLogger(OffertaResource.class);

    private static final String ENTITY_NAME = "offerta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OffertaRepository offertaRepository;

    public OffertaResource(OffertaRepository offertaRepository) {
        this.offertaRepository = offertaRepository;
    }

    /**
     * {@code POST  /offertas} : Create a new offerta.
     *
     * @param offerta the offerta to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerta, or with status {@code 400 (Bad Request)} if the offerta has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offertas")
    public ResponseEntity<Offerta> createOfferta(@Valid @RequestBody Offerta offerta) throws URISyntaxException {
        log.debug("REST request to save Offerta : {}", offerta);
        if (offerta.getId() != null) {
            throw new BadRequestAlertException("A new offerta cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Offerta result = offertaRepository.save(offerta);
        return ResponseEntity.created(new URI("/api/offertas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offertas} : Updates an existing offerta.
     *
     * @param offerta the offerta to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerta,
     * or with status {@code 400 (Bad Request)} if the offerta is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offerta couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offertas")
    public ResponseEntity<Offerta> updateOfferta(@Valid @RequestBody Offerta offerta) throws URISyntaxException {
        log.debug("REST request to update Offerta : {}", offerta);
        if (offerta.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Offerta result = offertaRepository.save(offerta);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, offerta.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /offertas} : get all the offertas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offertas in body.
     */
    @GetMapping("/offertas")
    public List<Offerta> getAllOffertas() {
        log.debug("REST request to get all Offertas");
        return offertaRepository.findAll();
    }

    /**
     * {@code GET  /offertas/:id} : get the "id" offerta.
     *
     * @param id the id of the offerta to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerta, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offertas/{id}")
    public ResponseEntity<Offerta> getOfferta(@PathVariable Long id) {
        log.debug("REST request to get Offerta : {}", id);
        Optional<Offerta> offerta = offertaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(offerta);
    }

    /**
     * {@code DELETE  /offertas/:id} : delete the "id" offerta.
     *
     * @param id the id of the offerta to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offertas/{id}")
    public ResponseEntity<Void> deleteOfferta(@PathVariable Long id) {
        log.debug("REST request to delete Offerta : {}", id);
        offertaRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
