package it.dlvsystem.web.rest;

import it.dlvsystem.RdcJhipsterApp;
import it.dlvsystem.domain.Azienda;
import it.dlvsystem.repository.AziendaRepository;

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
 * Integration tests for the {@link AziendaResource} REST controller.
 */
@SpringBootTest(classes = RdcJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AziendaResourceIT {

    private static final String DEFAULT_CF = "AAAAAAAAAA";
    private static final String UPDATED_CF = "BBBBBBBBBB";

    private static final String DEFAULT_RAGIONE_SOCIALE = "AAAAAAAAAA";
    private static final String UPDATED_RAGIONE_SOCIALE = "BBBBBBBBBB";

    private static final String DEFAULT_INDIRIZZO_SEDE = "AAAAAAAAAA";
    private static final String UPDATED_INDIRIZZO_SEDE = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCIA_SEDE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCIA_SEDE = "BBBBBBBBBB";

    private static final String DEFAULT_RAGIONE_SEDE = "AAAAAAAAAA";
    private static final String UPDATED_RAGIONE_SEDE = "BBBBBBBBBB";

    private static final String DEFAULT_CITTA_SEDE = "AAAAAAAAAA";
    private static final String UPDATED_CITTA_SEDE = "BBBBBBBBBB";

    private static final String DEFAULT_CAP_SEDE = "049664";
    private static final String UPDATED_CAP_SEDE = "8025";

    @Autowired
    private AziendaRepository aziendaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAziendaMockMvc;

    private Azienda azienda;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Azienda createEntity(EntityManager em) {
        Azienda azienda = new Azienda()
            .cf(DEFAULT_CF)
            .ragioneSociale(DEFAULT_RAGIONE_SOCIALE)
            .indirizzoSede(DEFAULT_INDIRIZZO_SEDE)
            .provinciaSede(DEFAULT_PROVINCIA_SEDE)
            .ragioneSede(DEFAULT_RAGIONE_SEDE)
            .cittaSede(DEFAULT_CITTA_SEDE)
            .capSede(DEFAULT_CAP_SEDE);
        return azienda;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Azienda createUpdatedEntity(EntityManager em) {
        Azienda azienda = new Azienda()
            .cf(UPDATED_CF)
            .ragioneSociale(UPDATED_RAGIONE_SOCIALE)
            .indirizzoSede(UPDATED_INDIRIZZO_SEDE)
            .provinciaSede(UPDATED_PROVINCIA_SEDE)
            .ragioneSede(UPDATED_RAGIONE_SEDE)
            .cittaSede(UPDATED_CITTA_SEDE)
            .capSede(UPDATED_CAP_SEDE);
        return azienda;
    }

    @BeforeEach
    public void initTest() {
        azienda = createEntity(em);
    }

    @Test
    @Transactional
    public void createAzienda() throws Exception {
        int databaseSizeBeforeCreate = aziendaRepository.findAll().size();
        // Create the Azienda
        restAziendaMockMvc.perform(post("/api/aziendas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(azienda)))
            .andExpect(status().isCreated());

        // Validate the Azienda in the database
        List<Azienda> aziendaList = aziendaRepository.findAll();
        assertThat(aziendaList).hasSize(databaseSizeBeforeCreate + 1);
        Azienda testAzienda = aziendaList.get(aziendaList.size() - 1);
        assertThat(testAzienda.getCf()).isEqualTo(DEFAULT_CF);
        assertThat(testAzienda.getRagioneSociale()).isEqualTo(DEFAULT_RAGIONE_SOCIALE);
        assertThat(testAzienda.getIndirizzoSede()).isEqualTo(DEFAULT_INDIRIZZO_SEDE);
        assertThat(testAzienda.getProvinciaSede()).isEqualTo(DEFAULT_PROVINCIA_SEDE);
        assertThat(testAzienda.getRagioneSede()).isEqualTo(DEFAULT_RAGIONE_SEDE);
        assertThat(testAzienda.getCittaSede()).isEqualTo(DEFAULT_CITTA_SEDE);
        assertThat(testAzienda.getCapSede()).isEqualTo(DEFAULT_CAP_SEDE);
    }

    @Test
    @Transactional
    public void createAziendaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aziendaRepository.findAll().size();

        // Create the Azienda with an existing ID
        azienda.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAziendaMockMvc.perform(post("/api/aziendas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(azienda)))
            .andExpect(status().isBadRequest());

        // Validate the Azienda in the database
        List<Azienda> aziendaList = aziendaRepository.findAll();
        assertThat(aziendaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAziendas() throws Exception {
        // Initialize the database
        aziendaRepository.saveAndFlush(azienda);

        // Get all the aziendaList
        restAziendaMockMvc.perform(get("/api/aziendas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(azienda.getId().intValue())))
            .andExpect(jsonPath("$.[*].cf").value(hasItem(DEFAULT_CF)))
            .andExpect(jsonPath("$.[*].ragioneSociale").value(hasItem(DEFAULT_RAGIONE_SOCIALE)))
            .andExpect(jsonPath("$.[*].indirizzoSede").value(hasItem(DEFAULT_INDIRIZZO_SEDE)))
            .andExpect(jsonPath("$.[*].provinciaSede").value(hasItem(DEFAULT_PROVINCIA_SEDE)))
            .andExpect(jsonPath("$.[*].ragioneSede").value(hasItem(DEFAULT_RAGIONE_SEDE)))
            .andExpect(jsonPath("$.[*].cittaSede").value(hasItem(DEFAULT_CITTA_SEDE)))
            .andExpect(jsonPath("$.[*].capSede").value(hasItem(DEFAULT_CAP_SEDE)));
    }
    
    @Test
    @Transactional
    public void getAzienda() throws Exception {
        // Initialize the database
        aziendaRepository.saveAndFlush(azienda);

        // Get the azienda
        restAziendaMockMvc.perform(get("/api/aziendas/{id}", azienda.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(azienda.getId().intValue()))
            .andExpect(jsonPath("$.cf").value(DEFAULT_CF))
            .andExpect(jsonPath("$.ragioneSociale").value(DEFAULT_RAGIONE_SOCIALE))
            .andExpect(jsonPath("$.indirizzoSede").value(DEFAULT_INDIRIZZO_SEDE))
            .andExpect(jsonPath("$.provinciaSede").value(DEFAULT_PROVINCIA_SEDE))
            .andExpect(jsonPath("$.ragioneSede").value(DEFAULT_RAGIONE_SEDE))
            .andExpect(jsonPath("$.cittaSede").value(DEFAULT_CITTA_SEDE))
            .andExpect(jsonPath("$.capSede").value(DEFAULT_CAP_SEDE));
    }
    @Test
    @Transactional
    public void getNonExistingAzienda() throws Exception {
        // Get the azienda
        restAziendaMockMvc.perform(get("/api/aziendas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAzienda() throws Exception {
        // Initialize the database
        aziendaRepository.saveAndFlush(azienda);

        int databaseSizeBeforeUpdate = aziendaRepository.findAll().size();

        // Update the azienda
        Azienda updatedAzienda = aziendaRepository.findById(azienda.getId()).get();
        // Disconnect from session so that the updates on updatedAzienda are not directly saved in db
        em.detach(updatedAzienda);
        updatedAzienda
            .cf(UPDATED_CF)
            .ragioneSociale(UPDATED_RAGIONE_SOCIALE)
            .indirizzoSede(UPDATED_INDIRIZZO_SEDE)
            .provinciaSede(UPDATED_PROVINCIA_SEDE)
            .ragioneSede(UPDATED_RAGIONE_SEDE)
            .cittaSede(UPDATED_CITTA_SEDE)
            .capSede(UPDATED_CAP_SEDE);

        restAziendaMockMvc.perform(put("/api/aziendas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAzienda)))
            .andExpect(status().isOk());

        // Validate the Azienda in the database
        List<Azienda> aziendaList = aziendaRepository.findAll();
        assertThat(aziendaList).hasSize(databaseSizeBeforeUpdate);
        Azienda testAzienda = aziendaList.get(aziendaList.size() - 1);
        assertThat(testAzienda.getCf()).isEqualTo(UPDATED_CF);
        assertThat(testAzienda.getRagioneSociale()).isEqualTo(UPDATED_RAGIONE_SOCIALE);
        assertThat(testAzienda.getIndirizzoSede()).isEqualTo(UPDATED_INDIRIZZO_SEDE);
        assertThat(testAzienda.getProvinciaSede()).isEqualTo(UPDATED_PROVINCIA_SEDE);
        assertThat(testAzienda.getRagioneSede()).isEqualTo(UPDATED_RAGIONE_SEDE);
        assertThat(testAzienda.getCittaSede()).isEqualTo(UPDATED_CITTA_SEDE);
        assertThat(testAzienda.getCapSede()).isEqualTo(UPDATED_CAP_SEDE);
    }

    @Test
    @Transactional
    public void updateNonExistingAzienda() throws Exception {
        int databaseSizeBeforeUpdate = aziendaRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAziendaMockMvc.perform(put("/api/aziendas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(azienda)))
            .andExpect(status().isBadRequest());

        // Validate the Azienda in the database
        List<Azienda> aziendaList = aziendaRepository.findAll();
        assertThat(aziendaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAzienda() throws Exception {
        // Initialize the database
        aziendaRepository.saveAndFlush(azienda);

        int databaseSizeBeforeDelete = aziendaRepository.findAll().size();

        // Delete the azienda
        restAziendaMockMvc.perform(delete("/api/aziendas/{id}", azienda.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Azienda> aziendaList = aziendaRepository.findAll();
        assertThat(aziendaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
