package it.dlvsystem.web.rest;

import it.dlvsystem.RdcJhipsterApp;
import it.dlvsystem.domain.GapAnalysis;
import it.dlvsystem.repository.GapAnalysisRepository;

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
 * Integration tests for the {@link GapAnalysisResource} REST controller.
 */
@SpringBootTest(classes = RdcJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class GapAnalysisResourceIT {

    private static final String DEFAULT_CODICE_ESCO = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_ESCO = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Integer DEFAULT_FREQUENZA = 1;
    private static final Integer UPDATED_FREQUENZA = 2;

    @Autowired
    private GapAnalysisRepository gapAnalysisRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restGapAnalysisMockMvc;

    private GapAnalysis gapAnalysis;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GapAnalysis createEntity(EntityManager em) {
        GapAnalysis gapAnalysis = new GapAnalysis()
            .codiceEsco(DEFAULT_CODICE_ESCO)
            .nome(DEFAULT_NOME)
            .frequenza(DEFAULT_FREQUENZA);
        return gapAnalysis;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GapAnalysis createUpdatedEntity(EntityManager em) {
        GapAnalysis gapAnalysis = new GapAnalysis()
            .codiceEsco(UPDATED_CODICE_ESCO)
            .nome(UPDATED_NOME)
            .frequenza(UPDATED_FREQUENZA);
        return gapAnalysis;
    }

    @BeforeEach
    public void initTest() {
        gapAnalysis = createEntity(em);
    }

    @Test
    @Transactional
    public void createGapAnalysis() throws Exception {
        int databaseSizeBeforeCreate = gapAnalysisRepository.findAll().size();
        // Create the GapAnalysis
        restGapAnalysisMockMvc.perform(post("/api/gap-analyses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gapAnalysis)))
            .andExpect(status().isCreated());

        // Validate the GapAnalysis in the database
        List<GapAnalysis> gapAnalysisList = gapAnalysisRepository.findAll();
        assertThat(gapAnalysisList).hasSize(databaseSizeBeforeCreate + 1);
        GapAnalysis testGapAnalysis = gapAnalysisList.get(gapAnalysisList.size() - 1);
        assertThat(testGapAnalysis.getCodiceEsco()).isEqualTo(DEFAULT_CODICE_ESCO);
        assertThat(testGapAnalysis.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testGapAnalysis.getFrequenza()).isEqualTo(DEFAULT_FREQUENZA);
    }

    @Test
    @Transactional
    public void createGapAnalysisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = gapAnalysisRepository.findAll().size();

        // Create the GapAnalysis with an existing ID
        gapAnalysis.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGapAnalysisMockMvc.perform(post("/api/gap-analyses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gapAnalysis)))
            .andExpect(status().isBadRequest());

        // Validate the GapAnalysis in the database
        List<GapAnalysis> gapAnalysisList = gapAnalysisRepository.findAll();
        assertThat(gapAnalysisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGapAnalyses() throws Exception {
        // Initialize the database
        gapAnalysisRepository.saveAndFlush(gapAnalysis);

        // Get all the gapAnalysisList
        restGapAnalysisMockMvc.perform(get("/api/gap-analyses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(gapAnalysis.getId().intValue())))
            .andExpect(jsonPath("$.[*].codiceEsco").value(hasItem(DEFAULT_CODICE_ESCO)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].frequenza").value(hasItem(DEFAULT_FREQUENZA)));
    }
    
    @Test
    @Transactional
    public void getGapAnalysis() throws Exception {
        // Initialize the database
        gapAnalysisRepository.saveAndFlush(gapAnalysis);

        // Get the gapAnalysis
        restGapAnalysisMockMvc.perform(get("/api/gap-analyses/{id}", gapAnalysis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(gapAnalysis.getId().intValue()))
            .andExpect(jsonPath("$.codiceEsco").value(DEFAULT_CODICE_ESCO))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.frequenza").value(DEFAULT_FREQUENZA));
    }
    @Test
    @Transactional
    public void getNonExistingGapAnalysis() throws Exception {
        // Get the gapAnalysis
        restGapAnalysisMockMvc.perform(get("/api/gap-analyses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGapAnalysis() throws Exception {
        // Initialize the database
        gapAnalysisRepository.saveAndFlush(gapAnalysis);

        int databaseSizeBeforeUpdate = gapAnalysisRepository.findAll().size();

        // Update the gapAnalysis
        GapAnalysis updatedGapAnalysis = gapAnalysisRepository.findById(gapAnalysis.getId()).get();
        // Disconnect from session so that the updates on updatedGapAnalysis are not directly saved in db
        em.detach(updatedGapAnalysis);
        updatedGapAnalysis
            .codiceEsco(UPDATED_CODICE_ESCO)
            .nome(UPDATED_NOME)
            .frequenza(UPDATED_FREQUENZA);

        restGapAnalysisMockMvc.perform(put("/api/gap-analyses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedGapAnalysis)))
            .andExpect(status().isOk());

        // Validate the GapAnalysis in the database
        List<GapAnalysis> gapAnalysisList = gapAnalysisRepository.findAll();
        assertThat(gapAnalysisList).hasSize(databaseSizeBeforeUpdate);
        GapAnalysis testGapAnalysis = gapAnalysisList.get(gapAnalysisList.size() - 1);
        assertThat(testGapAnalysis.getCodiceEsco()).isEqualTo(UPDATED_CODICE_ESCO);
        assertThat(testGapAnalysis.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testGapAnalysis.getFrequenza()).isEqualTo(UPDATED_FREQUENZA);
    }

    @Test
    @Transactional
    public void updateNonExistingGapAnalysis() throws Exception {
        int databaseSizeBeforeUpdate = gapAnalysisRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGapAnalysisMockMvc.perform(put("/api/gap-analyses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(gapAnalysis)))
            .andExpect(status().isBadRequest());

        // Validate the GapAnalysis in the database
        List<GapAnalysis> gapAnalysisList = gapAnalysisRepository.findAll();
        assertThat(gapAnalysisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGapAnalysis() throws Exception {
        // Initialize the database
        gapAnalysisRepository.saveAndFlush(gapAnalysis);

        int databaseSizeBeforeDelete = gapAnalysisRepository.findAll().size();

        // Delete the gapAnalysis
        restGapAnalysisMockMvc.perform(delete("/api/gap-analyses/{id}", gapAnalysis.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GapAnalysis> gapAnalysisList = gapAnalysisRepository.findAll();
        assertThat(gapAnalysisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
