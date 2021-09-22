package it.dlvsystem.web.rest;

import it.dlvsystem.RdcJhipsterApp;
import it.dlvsystem.domain.OffertaSkill;
import it.dlvsystem.repository.OffertaSkillRepository;

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
 * Integration tests for the {@link OffertaSkillResource} REST controller.
 */
@SpringBootTest(classes = RdcJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class OffertaSkillResourceIT {

    private static final String DEFAULT_CODICE_OFFERTA = "966";
    private static final String UPDATED_CODICE_OFFERTA = "7";

    private static final String DEFAULT_CODICE_ESCO_SKILL = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_ESCO_SKILL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_OPTIONAL = false;
    private static final Boolean UPDATED_OPTIONAL = true;

    @Autowired
    private OffertaSkillRepository offertaSkillRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOffertaSkillMockMvc;

    private OffertaSkill offertaSkill;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OffertaSkill createEntity(EntityManager em) {
        OffertaSkill offertaSkill = new OffertaSkill()
            .codiceOfferta(DEFAULT_CODICE_OFFERTA)
            .codiceEscoSkill(DEFAULT_CODICE_ESCO_SKILL)
            .optional(DEFAULT_OPTIONAL);
        return offertaSkill;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OffertaSkill createUpdatedEntity(EntityManager em) {
        OffertaSkill offertaSkill = new OffertaSkill()
            .codiceOfferta(UPDATED_CODICE_OFFERTA)
            .codiceEscoSkill(UPDATED_CODICE_ESCO_SKILL)
            .optional(UPDATED_OPTIONAL);
        return offertaSkill;
    }

    @BeforeEach
    public void initTest() {
        offertaSkill = createEntity(em);
    }

    @Test
    @Transactional
    public void createOffertaSkill() throws Exception {
        int databaseSizeBeforeCreate = offertaSkillRepository.findAll().size();
        // Create the OffertaSkill
        restOffertaSkillMockMvc.perform(post("/api/offerta-skills")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offertaSkill)))
            .andExpect(status().isCreated());

        // Validate the OffertaSkill in the database
        List<OffertaSkill> offertaSkillList = offertaSkillRepository.findAll();
        assertThat(offertaSkillList).hasSize(databaseSizeBeforeCreate + 1);
        OffertaSkill testOffertaSkill = offertaSkillList.get(offertaSkillList.size() - 1);
        assertThat(testOffertaSkill.getCodiceOfferta()).isEqualTo(DEFAULT_CODICE_OFFERTA);
        assertThat(testOffertaSkill.getCodiceEscoSkill()).isEqualTo(DEFAULT_CODICE_ESCO_SKILL);
        assertThat(testOffertaSkill.isOptional()).isEqualTo(DEFAULT_OPTIONAL);
    }

    @Test
    @Transactional
    public void createOffertaSkillWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = offertaSkillRepository.findAll().size();

        // Create the OffertaSkill with an existing ID
        offertaSkill.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOffertaSkillMockMvc.perform(post("/api/offerta-skills")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offertaSkill)))
            .andExpect(status().isBadRequest());

        // Validate the OffertaSkill in the database
        List<OffertaSkill> offertaSkillList = offertaSkillRepository.findAll();
        assertThat(offertaSkillList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOffertaSkills() throws Exception {
        // Initialize the database
        offertaSkillRepository.saveAndFlush(offertaSkill);

        // Get all the offertaSkillList
        restOffertaSkillMockMvc.perform(get("/api/offerta-skills?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offertaSkill.getId().intValue())))
            .andExpect(jsonPath("$.[*].codiceOfferta").value(hasItem(DEFAULT_CODICE_OFFERTA)))
            .andExpect(jsonPath("$.[*].codiceEscoSkill").value(hasItem(DEFAULT_CODICE_ESCO_SKILL)))
            .andExpect(jsonPath("$.[*].optional").value(hasItem(DEFAULT_OPTIONAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getOffertaSkill() throws Exception {
        // Initialize the database
        offertaSkillRepository.saveAndFlush(offertaSkill);

        // Get the offertaSkill
        restOffertaSkillMockMvc.perform(get("/api/offerta-skills/{id}", offertaSkill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offertaSkill.getId().intValue()))
            .andExpect(jsonPath("$.codiceOfferta").value(DEFAULT_CODICE_OFFERTA))
            .andExpect(jsonPath("$.codiceEscoSkill").value(DEFAULT_CODICE_ESCO_SKILL))
            .andExpect(jsonPath("$.optional").value(DEFAULT_OPTIONAL.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingOffertaSkill() throws Exception {
        // Get the offertaSkill
        restOffertaSkillMockMvc.perform(get("/api/offerta-skills/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOffertaSkill() throws Exception {
        // Initialize the database
        offertaSkillRepository.saveAndFlush(offertaSkill);

        int databaseSizeBeforeUpdate = offertaSkillRepository.findAll().size();

        // Update the offertaSkill
        OffertaSkill updatedOffertaSkill = offertaSkillRepository.findById(offertaSkill.getId()).get();
        // Disconnect from session so that the updates on updatedOffertaSkill are not directly saved in db
        em.detach(updatedOffertaSkill);
        updatedOffertaSkill
            .codiceOfferta(UPDATED_CODICE_OFFERTA)
            .codiceEscoSkill(UPDATED_CODICE_ESCO_SKILL)
            .optional(UPDATED_OPTIONAL);

        restOffertaSkillMockMvc.perform(put("/api/offerta-skills")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOffertaSkill)))
            .andExpect(status().isOk());

        // Validate the OffertaSkill in the database
        List<OffertaSkill> offertaSkillList = offertaSkillRepository.findAll();
        assertThat(offertaSkillList).hasSize(databaseSizeBeforeUpdate);
        OffertaSkill testOffertaSkill = offertaSkillList.get(offertaSkillList.size() - 1);
        assertThat(testOffertaSkill.getCodiceOfferta()).isEqualTo(UPDATED_CODICE_OFFERTA);
        assertThat(testOffertaSkill.getCodiceEscoSkill()).isEqualTo(UPDATED_CODICE_ESCO_SKILL);
        assertThat(testOffertaSkill.isOptional()).isEqualTo(UPDATED_OPTIONAL);
    }

    @Test
    @Transactional
    public void updateNonExistingOffertaSkill() throws Exception {
        int databaseSizeBeforeUpdate = offertaSkillRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOffertaSkillMockMvc.perform(put("/api/offerta-skills")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(offertaSkill)))
            .andExpect(status().isBadRequest());

        // Validate the OffertaSkill in the database
        List<OffertaSkill> offertaSkillList = offertaSkillRepository.findAll();
        assertThat(offertaSkillList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOffertaSkill() throws Exception {
        // Initialize the database
        offertaSkillRepository.saveAndFlush(offertaSkill);

        int databaseSizeBeforeDelete = offertaSkillRepository.findAll().size();

        // Delete the offertaSkill
        restOffertaSkillMockMvc.perform(delete("/api/offerta-skills/{id}", offertaSkill.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OffertaSkill> offertaSkillList = offertaSkillRepository.findAll();
        assertThat(offertaSkillList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
