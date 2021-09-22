package it.dlvsystem.web.rest;

import it.dlvsystem.RdcJhipsterApp;
import it.dlvsystem.domain.Cv;
import it.dlvsystem.repository.CvRepository;

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
 * Integration tests for the {@link CvResource} REST controller.
 */
@SpringBootTest(classes = RdcJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CvResourceIT {

    private static final String DEFAULT_CF_UTENTE = "AAAAAAAAAA";
    private static final String UPDATED_CF_UTENTE = "BBBBBBBBBB";

    private static final String DEFAULT_CODICE = "79762";
    private static final String UPDATED_CODICE = "845";

    private static final LocalDate DEFAULT_INSERIMENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INSERIMENTO = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private CvRepository cvRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCvMockMvc;

    private Cv cv;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cv createEntity(EntityManager em) {
        Cv cv = new Cv()
            .cfUtente(DEFAULT_CF_UTENTE)
            .codice(DEFAULT_CODICE)
            .inserimento(DEFAULT_INSERIMENTO);
        return cv;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cv createUpdatedEntity(EntityManager em) {
        Cv cv = new Cv()
            .cfUtente(UPDATED_CF_UTENTE)
            .codice(UPDATED_CODICE)
            .inserimento(UPDATED_INSERIMENTO);
        return cv;
    }

    @BeforeEach
    public void initTest() {
        cv = createEntity(em);
    }

    @Test
    @Transactional
    public void createCv() throws Exception {
        int databaseSizeBeforeCreate = cvRepository.findAll().size();
        // Create the Cv
        restCvMockMvc.perform(post("/api/cvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cv)))
            .andExpect(status().isCreated());

        // Validate the Cv in the database
        List<Cv> cvList = cvRepository.findAll();
        assertThat(cvList).hasSize(databaseSizeBeforeCreate + 1);
        Cv testCv = cvList.get(cvList.size() - 1);
        assertThat(testCv.getCfUtente()).isEqualTo(DEFAULT_CF_UTENTE);
        assertThat(testCv.getCodice()).isEqualTo(DEFAULT_CODICE);
        assertThat(testCv.getInserimento()).isEqualTo(DEFAULT_INSERIMENTO);
    }

    @Test
    @Transactional
    public void createCvWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cvRepository.findAll().size();

        // Create the Cv with an existing ID
        cv.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCvMockMvc.perform(post("/api/cvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cv)))
            .andExpect(status().isBadRequest());

        // Validate the Cv in the database
        List<Cv> cvList = cvRepository.findAll();
        assertThat(cvList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCvs() throws Exception {
        // Initialize the database
        cvRepository.saveAndFlush(cv);

        // Get all the cvList
        restCvMockMvc.perform(get("/api/cvs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cv.getId().intValue())))
            .andExpect(jsonPath("$.[*].cfUtente").value(hasItem(DEFAULT_CF_UTENTE)))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE)))
            .andExpect(jsonPath("$.[*].inserimento").value(hasItem(DEFAULT_INSERIMENTO.toString())));
    }
    
    @Test
    @Transactional
    public void getCv() throws Exception {
        // Initialize the database
        cvRepository.saveAndFlush(cv);

        // Get the cv
        restCvMockMvc.perform(get("/api/cvs/{id}", cv.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cv.getId().intValue()))
            .andExpect(jsonPath("$.cfUtente").value(DEFAULT_CF_UTENTE))
            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE))
            .andExpect(jsonPath("$.inserimento").value(DEFAULT_INSERIMENTO.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingCv() throws Exception {
        // Get the cv
        restCvMockMvc.perform(get("/api/cvs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCv() throws Exception {
        // Initialize the database
        cvRepository.saveAndFlush(cv);

        int databaseSizeBeforeUpdate = cvRepository.findAll().size();

        // Update the cv
        Cv updatedCv = cvRepository.findById(cv.getId()).get();
        // Disconnect from session so that the updates on updatedCv are not directly saved in db
        em.detach(updatedCv);
        updatedCv
            .cfUtente(UPDATED_CF_UTENTE)
            .codice(UPDATED_CODICE)
            .inserimento(UPDATED_INSERIMENTO);

        restCvMockMvc.perform(put("/api/cvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCv)))
            .andExpect(status().isOk());

        // Validate the Cv in the database
        List<Cv> cvList = cvRepository.findAll();
        assertThat(cvList).hasSize(databaseSizeBeforeUpdate);
        Cv testCv = cvList.get(cvList.size() - 1);
        assertThat(testCv.getCfUtente()).isEqualTo(UPDATED_CF_UTENTE);
        assertThat(testCv.getCodice()).isEqualTo(UPDATED_CODICE);
        assertThat(testCv.getInserimento()).isEqualTo(UPDATED_INSERIMENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingCv() throws Exception {
        int databaseSizeBeforeUpdate = cvRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCvMockMvc.perform(put("/api/cvs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cv)))
            .andExpect(status().isBadRequest());

        // Validate the Cv in the database
        List<Cv> cvList = cvRepository.findAll();
        assertThat(cvList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCv() throws Exception {
        // Initialize the database
        cvRepository.saveAndFlush(cv);

        int databaseSizeBeforeDelete = cvRepository.findAll().size();

        // Delete the cv
        restCvMockMvc.perform(delete("/api/cvs/{id}", cv.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cv> cvList = cvRepository.findAll();
        assertThat(cvList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
