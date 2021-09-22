package it.dlvsystem.web.rest;

import it.dlvsystem.domain.Navigator;
import it.dlvsystem.repository.NavigatorRepository;
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
 * REST controller for managing {@link it.dlvsystem.domain.Navigator}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class NavigatorResource {

    private final Logger log = LoggerFactory.getLogger(NavigatorResource.class);

    private static final String ENTITY_NAME = "navigator";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NavigatorRepository navigatorRepository;

    public NavigatorResource(NavigatorRepository navigatorRepository) {
        this.navigatorRepository = navigatorRepository;
    }

    /**
     * {@code POST  /navigators} : Create a new navigator.
     *
     * @param navigator the navigator to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new navigator, or with status {@code 400 (Bad Request)} if the navigator has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/navigators")
    public ResponseEntity<Navigator> createNavigator(@Valid @RequestBody Navigator navigator) throws URISyntaxException {
        log.debug("REST request to save Navigator : {}", navigator);
        if (navigator.getId() != null) {
            throw new BadRequestAlertException("A new navigator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Navigator result = navigatorRepository.save(navigator);
        return ResponseEntity.created(new URI("/api/navigators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /navigators} : Updates an existing navigator.
     *
     * @param navigator the navigator to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated navigator,
     * or with status {@code 400 (Bad Request)} if the navigator is not valid,
     * or with status {@code 500 (Internal Server Error)} if the navigator couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/navigators")
    public ResponseEntity<Navigator> updateNavigator(@Valid @RequestBody Navigator navigator) throws URISyntaxException {
        log.debug("REST request to update Navigator : {}", navigator);
        if (navigator.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Navigator result = navigatorRepository.save(navigator);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, navigator.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /navigators} : get all the navigators.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of navigators in body.
     */
    @GetMapping("/navigators")
    public List<Navigator> getAllNavigators() {
        log.debug("REST request to get all Navigators");
        return navigatorRepository.findAll();
    }

    /**
     * {@code GET  /navigators/:id} : get the "id" navigator.
     *
     * @param id the id of the navigator to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the navigator, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/navigators/{id}")
    public ResponseEntity<Navigator> getNavigator(@PathVariable Long id) {
        log.debug("REST request to get Navigator : {}", id);
        Optional<Navigator> navigator = navigatorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(navigator);
    }

    /**
     * {@code DELETE  /navigators/:id} : delete the "id" navigator.
     *
     * @param id the id of the navigator to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/navigators/{id}")
    public ResponseEntity<Void> deleteNavigator(@PathVariable Long id) {
        log.debug("REST request to delete Navigator : {}", id);
        navigatorRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
