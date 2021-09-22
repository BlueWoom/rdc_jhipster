package it.dlvsystem.web.rest;

import it.dlvsystem.RdcApp;
import it.dlvsystem.domain.SkillUtente;
import it.dlvsystem.repository.SkillUtenteRepository;

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
 * Integration tests for the {@link SkillUtenteResource} REST controller.
 */
@SpringBootTest(classes = RdcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SkillUtenteResourceIT {

    private static final String DEFAULT_CF_UTENTE = "AAAAAAAAAA";
    private static final String UPDATED_CF_UTENTE = "BBBBBBBBBB";

    private static final String DEFAULT_CODICE_ESCO_SKILL = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_ESCO_SKILL = "BBBBBBBBBB";

    @Autowired
    private SkillUtenteRepository skillUtenteRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSkillUtenteMockMvc;

    private SkillUtente skillUtente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SkillUtente createEntity(EntityManager em) {
        SkillUtente skillUtente = new SkillUtente()
            .cfUtente(DEFAULT_CF_UTENTE)
            .codiceEscoSkill(DEFAULT_CODICE_ESCO_SKILL);
        return skillUtente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SkillUtente createUpdatedEntity(EntityManager em) {
        SkillUtente skillUtente = new SkillUtente()
            .cfUtente(UPDATED_CF_UTENTE)
            .codiceEscoSkill(UPDATED_CODICE_ESCO_SKILL);
        return skillUtente;
    }

    @BeforeEach
    public void initTest() {
        skillUtente = createEntity(em);
    }

    @Test
    @Transactional
    public void createSkillUtente() throws Exception {
        int databaseSizeBeforeCreate = skillUtenteRepository.findAll().size();
        // Create the SkillUtente
        restSkillUtenteMockMvc.perform(post("/api/skill-utentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillUtente)))
            .andExpect(status().isCreated());

        // Validate the SkillUtente in the database
        List<SkillUtente> skillUtenteList = skillUtenteRepository.findAll();
        assertThat(skillUtenteList).hasSize(databaseSizeBeforeCreate + 1);
        SkillUtente testSkillUtente = skillUtenteList.get(skillUtenteList.size() - 1);
        assertThat(testSkillUtente.getCfUtente()).isEqualTo(DEFAULT_CF_UTENTE);
        assertThat(testSkillUtente.getCodiceEscoSkill()).isEqualTo(DEFAULT_CODICE_ESCO_SKILL);
    }

    @Test
    @Transactional
    public void createSkillUtenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = skillUtenteRepository.findAll().size();

        // Create the SkillUtente with an existing ID
        skillUtente.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSkillUtenteMockMvc.perform(post("/api/skill-utentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillUtente)))
            .andExpect(status().isBadRequest());

        // Validate the SkillUtente in the database
        List<SkillUtente> skillUtenteList = skillUtenteRepository.findAll();
        assertThat(skillUtenteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSkillUtentes() throws Exception {
        // Initialize the database
        skillUtenteRepository.saveAndFlush(skillUtente);

        // Get all the skillUtenteList
        restSkillUtenteMockMvc.perform(get("/api/skill-utentes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(skillUtente.getId().intValue())))
            .andExpect(jsonPath("$.[*].cfUtente").value(hasItem(DEFAULT_CF_UTENTE)))
            .andExpect(jsonPath("$.[*].codiceEscoSkill").value(hasItem(DEFAULT_CODICE_ESCO_SKILL)));
    }
    
    @Test
    @Transactional
    public void getSkillUtente() throws Exception {
        // Initialize the database
        skillUtenteRepository.saveAndFlush(skillUtente);

        // Get the skillUtente
        restSkillUtenteMockMvc.perform(get("/api/skill-utentes/{id}", skillUtente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(skillUtente.getId().intValue()))
            .andExpect(jsonPath("$.cfUtente").value(DEFAULT_CF_UTENTE))
            .andExpect(jsonPath("$.codiceEscoSkill").value(DEFAULT_CODICE_ESCO_SKILL));
    }
    @Test
    @Transactional
    public void getNonExistingSkillUtente() throws Exception {
        // Get the skillUtente
        restSkillUtenteMockMvc.perform(get("/api/skill-utentes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSkillUtente() throws Exception {
        // Initialize the database
        skillUtenteRepository.saveAndFlush(skillUtente);

        int databaseSizeBeforeUpdate = skillUtenteRepository.findAll().size();

        // Update the skillUtente
        SkillUtente updatedSkillUtente = skillUtenteRepository.findById(skillUtente.getId()).get();
        // Disconnect from session so that the updates on updatedSkillUtente are not directly saved in db
        em.detach(updatedSkillUtente);
        updatedSkillUtente
            .cfUtente(UPDATED_CF_UTENTE)
            .codiceEscoSkill(UPDATED_CODICE_ESCO_SKILL);

        restSkillUtenteMockMvc.perform(put("/api/skill-utentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSkillUtente)))
            .andExpect(status().isOk());

        // Validate the SkillUtente in the database
        List<SkillUtente> skillUtenteList = skillUtenteRepository.findAll();
        assertThat(skillUtenteList).hasSize(databaseSizeBeforeUpdate);
        SkillUtente testSkillUtente = skillUtenteList.get(skillUtenteList.size() - 1);
        assertThat(testSkillUtente.getCfUtente()).isEqualTo(UPDATED_CF_UTENTE);
        assertThat(testSkillUtente.getCodiceEscoSkill()).isEqualTo(UPDATED_CODICE_ESCO_SKILL);
    }

    @Test
    @Transactional
    public void updateNonExistingSkillUtente() throws Exception {
        int databaseSizeBeforeUpdate = skillUtenteRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSkillUtenteMockMvc.perform(put("/api/skill-utentes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(skillUtente)))
            .andExpect(status().isBadRequest());

        // Validate the SkillUtente in the database
        List<SkillUtente> skillUtenteList = skillUtenteRepository.findAll();
        assertThat(skillUtenteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSkillUtente() throws Exception {
        // Initialize the database
        skillUtenteRepository.saveAndFlush(skillUtente);

        int databaseSizeBeforeDelete = skillUtenteRepository.findAll().size();

        // Delete the skillUtente
        restSkillUtenteMockMvc.perform(delete("/api/skill-utentes/{id}", skillUtente.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SkillUtente> skillUtenteList = skillUtenteRepository.findAll();
        assertThat(skillUtenteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
