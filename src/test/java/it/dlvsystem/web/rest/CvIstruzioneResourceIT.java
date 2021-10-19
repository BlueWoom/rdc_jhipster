package it.dlvsystem.web.rest;

import it.dlvsystem.RdcJhipsterApp;
import it.dlvsystem.domain.CvIstruzione;
import it.dlvsystem.repository.CvIstruzioneRepository;

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
 * Integration tests for the {@link CvIstruzioneResource} REST controller.
 */
@SpringBootTest(classes = RdcJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CvIstruzioneResourceIT {

    private static final Integer DEFAULT_PUNTEGGIO = 1;
    private static final Integer UPDATED_PUNTEGGIO = 2;

    @Autowired
    private CvIstruzioneRepository cvIstruzioneRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCvIstruzioneMockMvc;

    private CvIstruzione cvIstruzione;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CvIstruzione createEntity(EntityManager em) {
        CvIstruzione cvIstruzione = new CvIstruzione()
            .punteggio(DEFAULT_PUNTEGGIO);
        return cvIstruzione;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CvIstruzione createUpdatedEntity(EntityManager em) {
        CvIstruzione cvIstruzione = new CvIstruzione()
            .punteggio(UPDATED_PUNTEGGIO);
        return cvIstruzione;
    }

    @BeforeEach
    public void initTest() {
        cvIstruzione = createEntity(em);
    }

    @Test
    @Transactional
    public void createCvIstruzione() throws Exception {
        int databaseSizeBeforeCreate = cvIstruzioneRepository.findAll().size();
        // Create the CvIstruzione
        restCvIstruzioneMockMvc.perform(post("/api/cv-istruziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cvIstruzione)))
            .andExpect(status().isCreated());

        // Validate the CvIstruzione in the database
        List<CvIstruzione> cvIstruzioneList = cvIstruzioneRepository.findAll();
        assertThat(cvIstruzioneList).hasSize(databaseSizeBeforeCreate + 1);
        CvIstruzione testCvIstruzione = cvIstruzioneList.get(cvIstruzioneList.size() - 1);
        assertThat(testCvIstruzione.getPunteggio()).isEqualTo(DEFAULT_PUNTEGGIO);
    }

    @Test
    @Transactional
    public void createCvIstruzioneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cvIstruzioneRepository.findAll().size();

        // Create the CvIstruzione with an existing ID
        cvIstruzione.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCvIstruzioneMockMvc.perform(post("/api/cv-istruziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cvIstruzione)))
            .andExpect(status().isBadRequest());

        // Validate the CvIstruzione in the database
        List<CvIstruzione> cvIstruzioneList = cvIstruzioneRepository.findAll();
        assertThat(cvIstruzioneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCvIstruziones() throws Exception {
        // Initialize the database
        cvIstruzioneRepository.saveAndFlush(cvIstruzione);

        // Get all the cvIstruzioneList
        restCvIstruzioneMockMvc.perform(get("/api/cv-istruziones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cvIstruzione.getId().intValue())))
            .andExpect(jsonPath("$.[*].punteggio").value(hasItem(DEFAULT_PUNTEGGIO)));
    }
    
    @Test
    @Transactional
    public void getCvIstruzione() throws Exception {
        // Initialize the database
        cvIstruzioneRepository.saveAndFlush(cvIstruzione);

        // Get the cvIstruzione
        restCvIstruzioneMockMvc.perform(get("/api/cv-istruziones/{id}", cvIstruzione.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cvIstruzione.getId().intValue()))
            .andExpect(jsonPath("$.punteggio").value(DEFAULT_PUNTEGGIO));
    }
    @Test
    @Transactional
    public void getNonExistingCvIstruzione() throws Exception {
        // Get the cvIstruzione
        restCvIstruzioneMockMvc.perform(get("/api/cv-istruziones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCvIstruzione() throws Exception {
        // Initialize the database
        cvIstruzioneRepository.saveAndFlush(cvIstruzione);

        int databaseSizeBeforeUpdate = cvIstruzioneRepository.findAll().size();

        // Update the cvIstruzione
        CvIstruzione updatedCvIstruzione = cvIstruzioneRepository.findById(cvIstruzione.getId()).get();
        // Disconnect from session so that the updates on updatedCvIstruzione are not directly saved in db
        em.detach(updatedCvIstruzione);
        updatedCvIstruzione
            .punteggio(UPDATED_PUNTEGGIO);

        restCvIstruzioneMockMvc.perform(put("/api/cv-istruziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCvIstruzione)))
            .andExpect(status().isOk());

        // Validate the CvIstruzione in the database
        List<CvIstruzione> cvIstruzioneList = cvIstruzioneRepository.findAll();
        assertThat(cvIstruzioneList).hasSize(databaseSizeBeforeUpdate);
        CvIstruzione testCvIstruzione = cvIstruzioneList.get(cvIstruzioneList.size() - 1);
        assertThat(testCvIstruzione.getPunteggio()).isEqualTo(UPDATED_PUNTEGGIO);
    }

    @Test
    @Transactional
    public void updateNonExistingCvIstruzione() throws Exception {
        int databaseSizeBeforeUpdate = cvIstruzioneRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCvIstruzioneMockMvc.perform(put("/api/cv-istruziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cvIstruzione)))
            .andExpect(status().isBadRequest());

        // Validate the CvIstruzione in the database
        List<CvIstruzione> cvIstruzioneList = cvIstruzioneRepository.findAll();
        assertThat(cvIstruzioneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCvIstruzione() throws Exception {
        // Initialize the database
        cvIstruzioneRepository.saveAndFlush(cvIstruzione);

        int databaseSizeBeforeDelete = cvIstruzioneRepository.findAll().size();

        // Delete the cvIstruzione
        restCvIstruzioneMockMvc.perform(delete("/api/cv-istruziones/{id}", cvIstruzione.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CvIstruzione> cvIstruzioneList = cvIstruzioneRepository.findAll();
        assertThat(cvIstruzioneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
