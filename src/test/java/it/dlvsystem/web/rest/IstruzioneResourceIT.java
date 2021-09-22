package it.dlvsystem.web.rest;

import it.dlvsystem.RdcApp;
import it.dlvsystem.domain.Istruzione;
import it.dlvsystem.repository.IstruzioneRepository;

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
 * Integration tests for the {@link IstruzioneResource} REST controller.
 */
@SpringBootTest(classes = RdcApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class IstruzioneResourceIT {

    private static final String DEFAULT_CODICE = "72890";
    private static final String UPDATED_CODICE = "4708";

    private static final String DEFAULT_CODICE_ISCED = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_ISCED = "BBBBBBBBBB";

    private static final String DEFAULT_CODICE_LIVELLO = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_LIVELLO = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_CAMPO_STUDIO = "AAAAAAAAAA";
    private static final String UPDATED_CAMPO_STUDIO = "BBBBBBBBBB";

    private static final String DEFAULT_SINONIMI = "AAAAAAAAAA";
    private static final String UPDATED_SINONIMI = "BBBBBBBBBB";

    private static final String DEFAULT_CODICE_ISTITUTO = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_ISTITUTO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_ISTITUTO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_ISTITUTO = "BBBBBBBBBB";

    @Autowired
    private IstruzioneRepository istruzioneRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIstruzioneMockMvc;

    private Istruzione istruzione;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Istruzione createEntity(EntityManager em) {
        Istruzione istruzione = new Istruzione()
            .codice(DEFAULT_CODICE)
            .codiceIsced(DEFAULT_CODICE_ISCED)
            .codiceLivello(DEFAULT_CODICE_LIVELLO)
            .nome(DEFAULT_NOME)
            .campoStudio(DEFAULT_CAMPO_STUDIO)
            .sinonimi(DEFAULT_SINONIMI)
            .codiceIstituto(DEFAULT_CODICE_ISTITUTO)
            .tipoIstituto(DEFAULT_TIPO_ISTITUTO);
        return istruzione;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Istruzione createUpdatedEntity(EntityManager em) {
        Istruzione istruzione = new Istruzione()
            .codice(UPDATED_CODICE)
            .codiceIsced(UPDATED_CODICE_ISCED)
            .codiceLivello(UPDATED_CODICE_LIVELLO)
            .nome(UPDATED_NOME)
            .campoStudio(UPDATED_CAMPO_STUDIO)
            .sinonimi(UPDATED_SINONIMI)
            .codiceIstituto(UPDATED_CODICE_ISTITUTO)
            .tipoIstituto(UPDATED_TIPO_ISTITUTO);
        return istruzione;
    }

    @BeforeEach
    public void initTest() {
        istruzione = createEntity(em);
    }

    @Test
    @Transactional
    public void createIstruzione() throws Exception {
        int databaseSizeBeforeCreate = istruzioneRepository.findAll().size();
        // Create the Istruzione
        restIstruzioneMockMvc.perform(post("/api/istruziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(istruzione)))
            .andExpect(status().isCreated());

        // Validate the Istruzione in the database
        List<Istruzione> istruzioneList = istruzioneRepository.findAll();
        assertThat(istruzioneList).hasSize(databaseSizeBeforeCreate + 1);
        Istruzione testIstruzione = istruzioneList.get(istruzioneList.size() - 1);
        assertThat(testIstruzione.getCodice()).isEqualTo(DEFAULT_CODICE);
        assertThat(testIstruzione.getCodiceIsced()).isEqualTo(DEFAULT_CODICE_ISCED);
        assertThat(testIstruzione.getCodiceLivello()).isEqualTo(DEFAULT_CODICE_LIVELLO);
        assertThat(testIstruzione.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testIstruzione.getCampoStudio()).isEqualTo(DEFAULT_CAMPO_STUDIO);
        assertThat(testIstruzione.getSinonimi()).isEqualTo(DEFAULT_SINONIMI);
        assertThat(testIstruzione.getCodiceIstituto()).isEqualTo(DEFAULT_CODICE_ISTITUTO);
        assertThat(testIstruzione.getTipoIstituto()).isEqualTo(DEFAULT_TIPO_ISTITUTO);
    }

    @Test
    @Transactional
    public void createIstruzioneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = istruzioneRepository.findAll().size();

        // Create the Istruzione with an existing ID
        istruzione.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIstruzioneMockMvc.perform(post("/api/istruziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(istruzione)))
            .andExpect(status().isBadRequest());

        // Validate the Istruzione in the database
        List<Istruzione> istruzioneList = istruzioneRepository.findAll();
        assertThat(istruzioneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllIstruziones() throws Exception {
        // Initialize the database
        istruzioneRepository.saveAndFlush(istruzione);

        // Get all the istruzioneList
        restIstruzioneMockMvc.perform(get("/api/istruziones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(istruzione.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE)))
            .andExpect(jsonPath("$.[*].codiceIsced").value(hasItem(DEFAULT_CODICE_ISCED)))
            .andExpect(jsonPath("$.[*].codiceLivello").value(hasItem(DEFAULT_CODICE_LIVELLO)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].campoStudio").value(hasItem(DEFAULT_CAMPO_STUDIO)))
            .andExpect(jsonPath("$.[*].sinonimi").value(hasItem(DEFAULT_SINONIMI)))
            .andExpect(jsonPath("$.[*].codiceIstituto").value(hasItem(DEFAULT_CODICE_ISTITUTO)))
            .andExpect(jsonPath("$.[*].tipoIstituto").value(hasItem(DEFAULT_TIPO_ISTITUTO)));
    }
    
    @Test
    @Transactional
    public void getIstruzione() throws Exception {
        // Initialize the database
        istruzioneRepository.saveAndFlush(istruzione);

        // Get the istruzione
        restIstruzioneMockMvc.perform(get("/api/istruziones/{id}", istruzione.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(istruzione.getId().intValue()))
            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE))
            .andExpect(jsonPath("$.codiceIsced").value(DEFAULT_CODICE_ISCED))
            .andExpect(jsonPath("$.codiceLivello").value(DEFAULT_CODICE_LIVELLO))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.campoStudio").value(DEFAULT_CAMPO_STUDIO))
            .andExpect(jsonPath("$.sinonimi").value(DEFAULT_SINONIMI))
            .andExpect(jsonPath("$.codiceIstituto").value(DEFAULT_CODICE_ISTITUTO))
            .andExpect(jsonPath("$.tipoIstituto").value(DEFAULT_TIPO_ISTITUTO));
    }
    @Test
    @Transactional
    public void getNonExistingIstruzione() throws Exception {
        // Get the istruzione
        restIstruzioneMockMvc.perform(get("/api/istruziones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIstruzione() throws Exception {
        // Initialize the database
        istruzioneRepository.saveAndFlush(istruzione);

        int databaseSizeBeforeUpdate = istruzioneRepository.findAll().size();

        // Update the istruzione
        Istruzione updatedIstruzione = istruzioneRepository.findById(istruzione.getId()).get();
        // Disconnect from session so that the updates on updatedIstruzione are not directly saved in db
        em.detach(updatedIstruzione);
        updatedIstruzione
            .codice(UPDATED_CODICE)
            .codiceIsced(UPDATED_CODICE_ISCED)
            .codiceLivello(UPDATED_CODICE_LIVELLO)
            .nome(UPDATED_NOME)
            .campoStudio(UPDATED_CAMPO_STUDIO)
            .sinonimi(UPDATED_SINONIMI)
            .codiceIstituto(UPDATED_CODICE_ISTITUTO)
            .tipoIstituto(UPDATED_TIPO_ISTITUTO);

        restIstruzioneMockMvc.perform(put("/api/istruziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedIstruzione)))
            .andExpect(status().isOk());

        // Validate the Istruzione in the database
        List<Istruzione> istruzioneList = istruzioneRepository.findAll();
        assertThat(istruzioneList).hasSize(databaseSizeBeforeUpdate);
        Istruzione testIstruzione = istruzioneList.get(istruzioneList.size() - 1);
        assertThat(testIstruzione.getCodice()).isEqualTo(UPDATED_CODICE);
        assertThat(testIstruzione.getCodiceIsced()).isEqualTo(UPDATED_CODICE_ISCED);
        assertThat(testIstruzione.getCodiceLivello()).isEqualTo(UPDATED_CODICE_LIVELLO);
        assertThat(testIstruzione.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testIstruzione.getCampoStudio()).isEqualTo(UPDATED_CAMPO_STUDIO);
        assertThat(testIstruzione.getSinonimi()).isEqualTo(UPDATED_SINONIMI);
        assertThat(testIstruzione.getCodiceIstituto()).isEqualTo(UPDATED_CODICE_ISTITUTO);
        assertThat(testIstruzione.getTipoIstituto()).isEqualTo(UPDATED_TIPO_ISTITUTO);
    }

    @Test
    @Transactional
    public void updateNonExistingIstruzione() throws Exception {
        int databaseSizeBeforeUpdate = istruzioneRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIstruzioneMockMvc.perform(put("/api/istruziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(istruzione)))
            .andExpect(status().isBadRequest());

        // Validate the Istruzione in the database
        List<Istruzione> istruzioneList = istruzioneRepository.findAll();
        assertThat(istruzioneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIstruzione() throws Exception {
        // Initialize the database
        istruzioneRepository.saveAndFlush(istruzione);

        int databaseSizeBeforeDelete = istruzioneRepository.findAll().size();

        // Delete the istruzione
        restIstruzioneMockMvc.perform(delete("/api/istruziones/{id}", istruzione.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Istruzione> istruzioneList = istruzioneRepository.findAll();
        assertThat(istruzioneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
