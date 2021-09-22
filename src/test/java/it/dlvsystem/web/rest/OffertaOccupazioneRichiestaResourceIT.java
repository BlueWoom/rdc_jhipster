package it.dlvsystem.web.rest;

import it.dlvsystem.RdcJhipsterApp;
import it.dlvsystem.domain.OffertaOccupazioneRichiesta;
import it.dlvsystem.repository.OffertaOccupazioneRichiestaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OffertaOccupazioneRichiestaResource} REST controller.
 */
@SpringBootTest(classes = RdcJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OffertaOccupazioneRichiestaResourceIT {

    private static final String DEFAULT_CODICE_OFFERTA = "50";
    private static final String UPDATED_CODICE_OFFERTA = "4664";

    private static final String DEFAULT_CODICE_ESCO_OCCUPAZIONE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_ESCO_OCCUPAZIONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ANNI = 1;
    private static final Integer UPDATED_ANNI = 2;

    @Autowired
    private OffertaOccupazioneRichiestaRepository offertaOccupazioneRichiestaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOffertaOccupazioneRichiestaMockMvc;

    private OffertaOccupazioneRichiesta offertaOccupazioneRichiesta;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OffertaOccupazioneRichiesta createEntity(EntityManager em) {
        OffertaOccupazioneRichiesta offertaOccupazioneRichiesta = new OffertaOccupazioneRichiesta()
            .codiceOfferta(DEFAULT_CODICE_OFFERTA)
            .codiceEscoOccupazione(DEFAULT_CODICE_ESCO_OCCUPAZIONE)
            .anni(DEFAULT_ANNI);
        return offertaOccupazioneRichiesta;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OffertaOccupazioneRichiesta createUpdatedEntity(EntityManager em) {
        OffertaOccupazioneRichiesta offertaOccupazioneRichiesta = new OffertaOccupazioneRichiesta()
            .codiceOfferta(UPDATED_CODICE_OFFERTA)
            .codiceEscoOccupazione(UPDATED_CODICE_ESCO_OCCUPAZIONE)
            .anni(UPDATED_ANNI);
        return offertaOccupazioneRichiesta;
    }

    @BeforeEach
    public void initTest() {
        offertaOccupazioneRichiesta = createEntity(em);
    }

    @Test
    @Transactional
    public void createOffertaOccupazioneRichiesta() throws Exception {
        int databaseSizeBeforeCreate = offertaOccupazioneRichiestaRepository.findAll().size();
        // Create the OffertaOccupazioneRichiesta
        restOffertaOccupazioneRichiestaMockMvc.perform(post("/api/offerta-occupazione-richiestas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offertaOccupazioneRichiesta)))
            .andExpect(status().isCreated());

        // Validate the OffertaOccupazioneRichiesta in the database
        List<OffertaOccupazioneRichiesta> offertaOccupazioneRichiestaList = offertaOccupazioneRichiestaRepository.findAll();
        assertThat(offertaOccupazioneRichiestaList).hasSize(databaseSizeBeforeCreate + 1);
        OffertaOccupazioneRichiesta testOffertaOccupazioneRichiesta = offertaOccupazioneRichiestaList.get(offertaOccupazioneRichiestaList.size() - 1);
        assertThat(testOffertaOccupazioneRichiesta.getCodiceOfferta()).isEqualTo(DEFAULT_CODICE_OFFERTA);
        assertThat(testOffertaOccupazioneRichiesta.getCodiceEscoOccupazione()).isEqualTo(DEFAULT_CODICE_ESCO_OCCUPAZIONE);
        assertThat(testOffertaOccupazioneRichiesta.getAnni()).isEqualTo(DEFAULT_ANNI);
    }

    @Test
    @Transactional
    public void createOffertaOccupazioneRichiestaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offertaOccupazioneRichiestaRepository.findAll().size();

        // Create the OffertaOccupazioneRichiesta with an existing ID
        offertaOccupazioneRichiesta.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOffertaOccupazioneRichiestaMockMvc.perform(post("/api/offerta-occupazione-richiestas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offertaOccupazioneRichiesta)))
            .andExpect(status().isBadRequest());

        // Validate the OffertaOccupazioneRichiesta in the database
        List<OffertaOccupazioneRichiesta> offertaOccupazioneRichiestaList = offertaOccupazioneRichiestaRepository.findAll();
        assertThat(offertaOccupazioneRichiestaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOffertaOccupazioneRichiestas() throws Exception {
        // Initialize the database
        offertaOccupazioneRichiestaRepository.saveAndFlush(offertaOccupazioneRichiesta);

        // Get all the offertaOccupazioneRichiestaList
        restOffertaOccupazioneRichiestaMockMvc.perform(get("/api/offerta-occupazione-richiestas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offertaOccupazioneRichiesta.getId().intValue())))
            .andExpect(jsonPath("$.[*].codiceOfferta").value(hasItem(DEFAULT_CODICE_OFFERTA)))
            .andExpect(jsonPath("$.[*].codiceEscoOccupazione").value(hasItem(DEFAULT_CODICE_ESCO_OCCUPAZIONE)))
            .andExpect(jsonPath("$.[*].anni").value(hasItem(DEFAULT_ANNI)));
    }
    
    @Test
    @Transactional
    public void getOffertaOccupazioneRichiesta() throws Exception {
        // Initialize the database
        offertaOccupazioneRichiestaRepository.saveAndFlush(offertaOccupazioneRichiesta);

        // Get the offertaOccupazioneRichiesta
        restOffertaOccupazioneRichiestaMockMvc.perform(get("/api/offerta-occupazione-richiestas/{id}", offertaOccupazioneRichiesta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offertaOccupazioneRichiesta.getId().intValue()))
            .andExpect(jsonPath("$.codiceOfferta").value(DEFAULT_CODICE_OFFERTA))
            .andExpect(jsonPath("$.codiceEscoOccupazione").value(DEFAULT_CODICE_ESCO_OCCUPAZIONE))
            .andExpect(jsonPath("$.anni").value(DEFAULT_ANNI));
    }
    @Test
    @Transactional
    public void getNonExistingOffertaOccupazioneRichiesta() throws Exception {
        // Get the offertaOccupazioneRichiesta
        restOffertaOccupazioneRichiestaMockMvc.perform(get("/api/offerta-occupazione-richiestas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOffertaOccupazioneRichiesta() throws Exception {
        // Initialize the database
        offertaOccupazioneRichiestaRepository.saveAndFlush(offertaOccupazioneRichiesta);

        int databaseSizeBeforeUpdate = offertaOccupazioneRichiestaRepository.findAll().size();

        // Update the offertaOccupazioneRichiesta
        OffertaOccupazioneRichiesta updatedOffertaOccupazioneRichiesta = offertaOccupazioneRichiestaRepository.findById(offertaOccupazioneRichiesta.getId()).get();
        // Disconnect from session so that the updates on updatedOffertaOccupazioneRichiesta are not directly saved in db
        em.detach(updatedOffertaOccupazioneRichiesta);
        updatedOffertaOccupazioneRichiesta
            .codiceOfferta(UPDATED_CODICE_OFFERTA)
            .codiceEscoOccupazione(UPDATED_CODICE_ESCO_OCCUPAZIONE)
            .anni(UPDATED_ANNI);

        restOffertaOccupazioneRichiestaMockMvc.perform(put("/api/offerta-occupazione-richiestas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOffertaOccupazioneRichiesta)))
            .andExpect(status().isOk());

        // Validate the OffertaOccupazioneRichiesta in the database
        List<OffertaOccupazioneRichiesta> offertaOccupazioneRichiestaList = offertaOccupazioneRichiestaRepository.findAll();
        assertThat(offertaOccupazioneRichiestaList).hasSize(databaseSizeBeforeUpdate);
        OffertaOccupazioneRichiesta testOffertaOccupazioneRichiesta = offertaOccupazioneRichiestaList.get(offertaOccupazioneRichiestaList.size() - 1);
        assertThat(testOffertaOccupazioneRichiesta.getCodiceOfferta()).isEqualTo(UPDATED_CODICE_OFFERTA);
        assertThat(testOffertaOccupazioneRichiesta.getCodiceEscoOccupazione()).isEqualTo(UPDATED_CODICE_ESCO_OCCUPAZIONE);
        assertThat(testOffertaOccupazioneRichiesta.getAnni()).isEqualTo(UPDATED_ANNI);
    }

    @Test
    @Transactional
    public void updateNonExistingOffertaOccupazioneRichiesta() throws Exception {
        int databaseSizeBeforeUpdate = offertaOccupazioneRichiestaRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOffertaOccupazioneRichiestaMockMvc.perform(put("/api/offerta-occupazione-richiestas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offertaOccupazioneRichiesta)))
            .andExpect(status().isBadRequest());

        // Validate the OffertaOccupazioneRichiesta in the database
        List<OffertaOccupazioneRichiesta> offertaOccupazioneRichiestaList = offertaOccupazioneRichiestaRepository.findAll();
        assertThat(offertaOccupazioneRichiestaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOffertaOccupazioneRichiesta() throws Exception {
        // Initialize the database
        offertaOccupazioneRichiestaRepository.saveAndFlush(offertaOccupazioneRichiesta);

        int databaseSizeBeforeDelete = offertaOccupazioneRichiestaRepository.findAll().size();

        // Delete the offertaOccupazioneRichiesta
        restOffertaOccupazioneRichiestaMockMvc.perform(delete("/api/offerta-occupazione-richiestas/{id}", offertaOccupazioneRichiesta.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OffertaOccupazioneRichiesta> offertaOccupazioneRichiestaList = offertaOccupazioneRichiestaRepository.findAll();
        assertThat(offertaOccupazioneRichiestaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
