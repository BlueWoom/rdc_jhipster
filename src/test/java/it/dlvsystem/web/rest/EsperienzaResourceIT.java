package it.dlvsystem.web.rest;

import it.dlvsystem.RdcApp;
import it.dlvsystem.domain.Esperienza;
import it.dlvsystem.repository.EsperienzaRepository;

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
 * Integration tests for the {@link EsperienzaResource} REST controller.
 */
@SpringBootTest(classes = RdcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EsperienzaResourceIT {

    private static final String DEFAULT_CODICE = "703";
    private static final String UPDATED_CODICE = "462591";

    private static final String DEFAULT_ATTIVITA = "AAAAAAAAAA";
    private static final String UPDATED_ATTIVITA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DAL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAL = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_AL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AL = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DATORE_LAVORO = "AAAAAAAAAA";
    private static final String UPDATED_DATORE_LAVORO = "BBBBBBBBBB";

    private static final String DEFAULT_SEDE_LAVORO = "AAAAAAAAAA";
    private static final String UPDATED_SEDE_LAVORO = "BBBBBBBBBB";

    @Autowired
    private EsperienzaRepository esperienzaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEsperienzaMockMvc;

    private Esperienza esperienza;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Esperienza createEntity(EntityManager em) {
        Esperienza esperienza = new Esperienza()
            .codice(DEFAULT_CODICE)
            .attivita(DEFAULT_ATTIVITA)
            .dal(DEFAULT_DAL)
            .al(DEFAULT_AL)
            .datoreLavoro(DEFAULT_DATORE_LAVORO)
            .sedeLavoro(DEFAULT_SEDE_LAVORO);
        return esperienza;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Esperienza createUpdatedEntity(EntityManager em) {
        Esperienza esperienza = new Esperienza()
            .codice(UPDATED_CODICE)
            .attivita(UPDATED_ATTIVITA)
            .dal(UPDATED_DAL)
            .al(UPDATED_AL)
            .datoreLavoro(UPDATED_DATORE_LAVORO)
            .sedeLavoro(UPDATED_SEDE_LAVORO);
        return esperienza;
    }

    @BeforeEach
    public void initTest() {
        esperienza = createEntity(em);
    }

    @Test
    @Transactional
    public void createEsperienza() throws Exception {
        int databaseSizeBeforeCreate = esperienzaRepository.findAll().size();
        // Create the Esperienza
        restEsperienzaMockMvc.perform(post("/api/esperienzas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(esperienza)))
            .andExpect(status().isCreated());

        // Validate the Esperienza in the database
        List<Esperienza> esperienzaList = esperienzaRepository.findAll();
        assertThat(esperienzaList).hasSize(databaseSizeBeforeCreate + 1);
        Esperienza testEsperienza = esperienzaList.get(esperienzaList.size() - 1);
        assertThat(testEsperienza.getCodice()).isEqualTo(DEFAULT_CODICE);
        assertThat(testEsperienza.getAttivita()).isEqualTo(DEFAULT_ATTIVITA);
        assertThat(testEsperienza.getDal()).isEqualTo(DEFAULT_DAL);
        assertThat(testEsperienza.getAl()).isEqualTo(DEFAULT_AL);
        assertThat(testEsperienza.getDatoreLavoro()).isEqualTo(DEFAULT_DATORE_LAVORO);
        assertThat(testEsperienza.getSedeLavoro()).isEqualTo(DEFAULT_SEDE_LAVORO);
    }

    @Test
    @Transactional
    public void createEsperienzaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = esperienzaRepository.findAll().size();

        // Create the Esperienza with an existing ID
        esperienza.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEsperienzaMockMvc.perform(post("/api/esperienzas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(esperienza)))
            .andExpect(status().isBadRequest());

        // Validate the Esperienza in the database
        List<Esperienza> esperienzaList = esperienzaRepository.findAll();
        assertThat(esperienzaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEsperienzas() throws Exception {
        // Initialize the database
        esperienzaRepository.saveAndFlush(esperienza);

        // Get all the esperienzaList
        restEsperienzaMockMvc.perform(get("/api/esperienzas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(esperienza.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE)))
            .andExpect(jsonPath("$.[*].attivita").value(hasItem(DEFAULT_ATTIVITA)))
            .andExpect(jsonPath("$.[*].dal").value(hasItem(DEFAULT_DAL.toString())))
            .andExpect(jsonPath("$.[*].al").value(hasItem(DEFAULT_AL.toString())))
            .andExpect(jsonPath("$.[*].datoreLavoro").value(hasItem(DEFAULT_DATORE_LAVORO)))
            .andExpect(jsonPath("$.[*].sedeLavoro").value(hasItem(DEFAULT_SEDE_LAVORO)));
    }
    
    @Test
    @Transactional
    public void getEsperienza() throws Exception {
        // Initialize the database
        esperienzaRepository.saveAndFlush(esperienza);

        // Get the esperienza
        restEsperienzaMockMvc.perform(get("/api/esperienzas/{id}", esperienza.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(esperienza.getId().intValue()))
            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE))
            .andExpect(jsonPath("$.attivita").value(DEFAULT_ATTIVITA))
            .andExpect(jsonPath("$.dal").value(DEFAULT_DAL.toString()))
            .andExpect(jsonPath("$.al").value(DEFAULT_AL.toString()))
            .andExpect(jsonPath("$.datoreLavoro").value(DEFAULT_DATORE_LAVORO))
            .andExpect(jsonPath("$.sedeLavoro").value(DEFAULT_SEDE_LAVORO));
    }
    @Test
    @Transactional
    public void getNonExistingEsperienza() throws Exception {
        // Get the esperienza
        restEsperienzaMockMvc.perform(get("/api/esperienzas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEsperienza() throws Exception {
        // Initialize the database
        esperienzaRepository.saveAndFlush(esperienza);

        int databaseSizeBeforeUpdate = esperienzaRepository.findAll().size();

        // Update the esperienza
        Esperienza updatedEsperienza = esperienzaRepository.findById(esperienza.getId()).get();
        // Disconnect from session so that the updates on updatedEsperienza are not directly saved in db
        em.detach(updatedEsperienza);
        updatedEsperienza
            .codice(UPDATED_CODICE)
            .attivita(UPDATED_ATTIVITA)
            .dal(UPDATED_DAL)
            .al(UPDATED_AL)
            .datoreLavoro(UPDATED_DATORE_LAVORO)
            .sedeLavoro(UPDATED_SEDE_LAVORO);

        restEsperienzaMockMvc.perform(put("/api/esperienzas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEsperienza)))
            .andExpect(status().isOk());

        // Validate the Esperienza in the database
        List<Esperienza> esperienzaList = esperienzaRepository.findAll();
        assertThat(esperienzaList).hasSize(databaseSizeBeforeUpdate);
        Esperienza testEsperienza = esperienzaList.get(esperienzaList.size() - 1);
        assertThat(testEsperienza.getCodice()).isEqualTo(UPDATED_CODICE);
        assertThat(testEsperienza.getAttivita()).isEqualTo(UPDATED_ATTIVITA);
        assertThat(testEsperienza.getDal()).isEqualTo(UPDATED_DAL);
        assertThat(testEsperienza.getAl()).isEqualTo(UPDATED_AL);
        assertThat(testEsperienza.getDatoreLavoro()).isEqualTo(UPDATED_DATORE_LAVORO);
        assertThat(testEsperienza.getSedeLavoro()).isEqualTo(UPDATED_SEDE_LAVORO);
    }

    @Test
    @Transactional
    public void updateNonExistingEsperienza() throws Exception {
        int databaseSizeBeforeUpdate = esperienzaRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEsperienzaMockMvc.perform(put("/api/esperienzas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(esperienza)))
            .andExpect(status().isBadRequest());

        // Validate the Esperienza in the database
        List<Esperienza> esperienzaList = esperienzaRepository.findAll();
        assertThat(esperienzaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEsperienza() throws Exception {
        // Initialize the database
        esperienzaRepository.saveAndFlush(esperienza);

        int databaseSizeBeforeDelete = esperienzaRepository.findAll().size();

        // Delete the esperienza
        restEsperienzaMockMvc.perform(delete("/api/esperienzas/{id}", esperienza.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Esperienza> esperienzaList = esperienzaRepository.findAll();
        assertThat(esperienzaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
