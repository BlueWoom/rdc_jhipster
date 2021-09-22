package it.dlvsystem.web.rest;

import it.dlvsystem.RdcApp;
import it.dlvsystem.domain.Offerta;
import it.dlvsystem.repository.OffertaRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OffertaResource} REST controller.
 */
@SpringBootTest(classes = RdcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OffertaResourceIT {

    private static final String DEFAULT_CODICE = "500672";
    private static final String UPDATED_CODICE = "9";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INDIRIZZO_SEDE = "AAAAAAAAAA";
    private static final String UPDATED_INDIRIZZO_SEDE = "BBBBBBBBBB";

    private static final String DEFAULT_CITTA_SEDE = "AAAAAAAAAA";
    private static final String UPDATED_CITTA_SEDE = "BBBBBBBBBB";

    private static final String DEFAULT_CAP_SEDE = "30737";
    private static final String UPDATED_CAP_SEDE = "31002";

    private static final String DEFAULT_PROVINCIA_SEDE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCIA_SEDE = "BBBBBBBBBB";

    @Autowired
    private OffertaRepository offertaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOffertaMockMvc;

    private Offerta offerta;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Offerta createEntity(EntityManager em) {
        Offerta offerta = new Offerta()
            .codice(DEFAULT_CODICE)
            .data(DEFAULT_DATA)
            .indirizzoSede(DEFAULT_INDIRIZZO_SEDE)
            .cittaSede(DEFAULT_CITTA_SEDE)
            .capSede(DEFAULT_CAP_SEDE)
            .provinciaSede(DEFAULT_PROVINCIA_SEDE);
        return offerta;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Offerta createUpdatedEntity(EntityManager em) {
        Offerta offerta = new Offerta()
            .codice(UPDATED_CODICE)
            .data(UPDATED_DATA)
            .indirizzoSede(UPDATED_INDIRIZZO_SEDE)
            .cittaSede(UPDATED_CITTA_SEDE)
            .capSede(UPDATED_CAP_SEDE)
            .provinciaSede(UPDATED_PROVINCIA_SEDE);
        return offerta;
    }

    @BeforeEach
    public void initTest() {
        offerta = createEntity(em);
    }

    @Test
    @Transactional
    public void createOfferta() throws Exception {
        int databaseSizeBeforeCreate = offertaRepository.findAll().size();
        // Create the Offerta
        restOffertaMockMvc.perform(post("/api/offertas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerta)))
            .andExpect(status().isCreated());

        // Validate the Offerta in the database
        List<Offerta> offertaList = offertaRepository.findAll();
        assertThat(offertaList).hasSize(databaseSizeBeforeCreate + 1);
        Offerta testOfferta = offertaList.get(offertaList.size() - 1);
        assertThat(testOfferta.getCodice()).isEqualTo(DEFAULT_CODICE);
        assertThat(testOfferta.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testOfferta.getIndirizzoSede()).isEqualTo(DEFAULT_INDIRIZZO_SEDE);
        assertThat(testOfferta.getCittaSede()).isEqualTo(DEFAULT_CITTA_SEDE);
        assertThat(testOfferta.getCapSede()).isEqualTo(DEFAULT_CAP_SEDE);
        assertThat(testOfferta.getProvinciaSede()).isEqualTo(DEFAULT_PROVINCIA_SEDE);
    }

    @Test
    @Transactional
    public void createOffertaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offertaRepository.findAll().size();

        // Create the Offerta with an existing ID
        offerta.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOffertaMockMvc.perform(post("/api/offertas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerta)))
            .andExpect(status().isBadRequest());

        // Validate the Offerta in the database
        List<Offerta> offertaList = offertaRepository.findAll();
        assertThat(offertaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOffertas() throws Exception {
        // Initialize the database
        offertaRepository.saveAndFlush(offerta);

        // Get all the offertaList
        restOffertaMockMvc.perform(get("/api/offertas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offerta.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].indirizzoSede").value(hasItem(DEFAULT_INDIRIZZO_SEDE)))
            .andExpect(jsonPath("$.[*].cittaSede").value(hasItem(DEFAULT_CITTA_SEDE)))
            .andExpect(jsonPath("$.[*].capSede").value(hasItem(DEFAULT_CAP_SEDE)))
            .andExpect(jsonPath("$.[*].provinciaSede").value(hasItem(DEFAULT_PROVINCIA_SEDE)));
    }
    
    @Test
    @Transactional
    public void getOfferta() throws Exception {
        // Initialize the database
        offertaRepository.saveAndFlush(offerta);

        // Get the offerta
        restOffertaMockMvc.perform(get("/api/offertas/{id}", offerta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offerta.getId().intValue()))
            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.indirizzoSede").value(DEFAULT_INDIRIZZO_SEDE))
            .andExpect(jsonPath("$.cittaSede").value(DEFAULT_CITTA_SEDE))
            .andExpect(jsonPath("$.capSede").value(DEFAULT_CAP_SEDE))
            .andExpect(jsonPath("$.provinciaSede").value(DEFAULT_PROVINCIA_SEDE));
    }
    @Test
    @Transactional
    public void getNonExistingOfferta() throws Exception {
        // Get the offerta
        restOffertaMockMvc.perform(get("/api/offertas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOfferta() throws Exception {
        // Initialize the database
        offertaRepository.saveAndFlush(offerta);

        int databaseSizeBeforeUpdate = offertaRepository.findAll().size();

        // Update the offerta
        Offerta updatedOfferta = offertaRepository.findById(offerta.getId()).get();
        // Disconnect from session so that the updates on updatedOfferta are not directly saved in db
        em.detach(updatedOfferta);
        updatedOfferta
            .codice(UPDATED_CODICE)
            .data(UPDATED_DATA)
            .indirizzoSede(UPDATED_INDIRIZZO_SEDE)
            .cittaSede(UPDATED_CITTA_SEDE)
            .capSede(UPDATED_CAP_SEDE)
            .provinciaSede(UPDATED_PROVINCIA_SEDE);

        restOffertaMockMvc.perform(put("/api/offertas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOfferta)))
            .andExpect(status().isOk());

        // Validate the Offerta in the database
        List<Offerta> offertaList = offertaRepository.findAll();
        assertThat(offertaList).hasSize(databaseSizeBeforeUpdate);
        Offerta testOfferta = offertaList.get(offertaList.size() - 1);
        assertThat(testOfferta.getCodice()).isEqualTo(UPDATED_CODICE);
        assertThat(testOfferta.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testOfferta.getIndirizzoSede()).isEqualTo(UPDATED_INDIRIZZO_SEDE);
        assertThat(testOfferta.getCittaSede()).isEqualTo(UPDATED_CITTA_SEDE);
        assertThat(testOfferta.getCapSede()).isEqualTo(UPDATED_CAP_SEDE);
        assertThat(testOfferta.getProvinciaSede()).isEqualTo(UPDATED_PROVINCIA_SEDE);
    }

    @Test
    @Transactional
    public void updateNonExistingOfferta() throws Exception {
        int databaseSizeBeforeUpdate = offertaRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOffertaMockMvc.perform(put("/api/offertas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offerta)))
            .andExpect(status().isBadRequest());

        // Validate the Offerta in the database
        List<Offerta> offertaList = offertaRepository.findAll();
        assertThat(offertaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOfferta() throws Exception {
        // Initialize the database
        offertaRepository.saveAndFlush(offerta);

        int databaseSizeBeforeDelete = offertaRepository.findAll().size();

        // Delete the offerta
        restOffertaMockMvc.perform(delete("/api/offertas/{id}", offerta.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Offerta> offertaList = offertaRepository.findAll();
        assertThat(offertaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
