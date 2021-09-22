package it.dlvsystem.web.rest;

import it.dlvsystem.RdcJhipsterApp;
import it.dlvsystem.domain.Occupazione;
import it.dlvsystem.repository.OccupazioneRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link OccupazioneResource} REST controller.
 */
@SpringBootTest(classes = RdcJhipsterApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class OccupazioneResourceIT {

    private static final String DEFAULT_CODICE_ESCO = "AAAAAAAAAA";
    private static final String UPDATED_CODICE_ESCO = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private OccupazioneRepository occupazioneRepository;

    @Mock
    private OccupazioneRepository occupazioneRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOccupazioneMockMvc;

    private Occupazione occupazione;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Occupazione createEntity(EntityManager em) {
        Occupazione occupazione = new Occupazione()
            .codiceEsco(DEFAULT_CODICE_ESCO)
            .nome(DEFAULT_NOME);
        return occupazione;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Occupazione createUpdatedEntity(EntityManager em) {
        Occupazione occupazione = new Occupazione()
            .codiceEsco(UPDATED_CODICE_ESCO)
            .nome(UPDATED_NOME);
        return occupazione;
    }

    @BeforeEach
    public void initTest() {
        occupazione = createEntity(em);
    }

    @Test
    @Transactional
    public void createOccupazione() throws Exception {
        int databaseSizeBeforeCreate = occupazioneRepository.findAll().size();
        // Create the Occupazione
        restOccupazioneMockMvc.perform(post("/api/occupaziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(occupazione)))
            .andExpect(status().isCreated());

        // Validate the Occupazione in the database
        List<Occupazione> occupazioneList = occupazioneRepository.findAll();
        assertThat(occupazioneList).hasSize(databaseSizeBeforeCreate + 1);
        Occupazione testOccupazione = occupazioneList.get(occupazioneList.size() - 1);
        assertThat(testOccupazione.getCodiceEsco()).isEqualTo(DEFAULT_CODICE_ESCO);
        assertThat(testOccupazione.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createOccupazioneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = occupazioneRepository.findAll().size();

        // Create the Occupazione with an existing ID
        occupazione.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOccupazioneMockMvc.perform(post("/api/occupaziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(occupazione)))
            .andExpect(status().isBadRequest());

        // Validate the Occupazione in the database
        List<Occupazione> occupazioneList = occupazioneRepository.findAll();
        assertThat(occupazioneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOccupaziones() throws Exception {
        // Initialize the database
        occupazioneRepository.saveAndFlush(occupazione);

        // Get all the occupazioneList
        restOccupazioneMockMvc.perform(get("/api/occupaziones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(occupazione.getId().intValue())))
            .andExpect(jsonPath("$.[*].codiceEsco").value(hasItem(DEFAULT_CODICE_ESCO)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllOccupazionesWithEagerRelationshipsIsEnabled() throws Exception {
        when(occupazioneRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOccupazioneMockMvc.perform(get("/api/occupaziones?eagerload=true"))
            .andExpect(status().isOk());

        verify(occupazioneRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllOccupazionesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(occupazioneRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restOccupazioneMockMvc.perform(get("/api/occupaziones?eagerload=true"))
            .andExpect(status().isOk());

        verify(occupazioneRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getOccupazione() throws Exception {
        // Initialize the database
        occupazioneRepository.saveAndFlush(occupazione);

        // Get the occupazione
        restOccupazioneMockMvc.perform(get("/api/occupaziones/{id}", occupazione.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(occupazione.getId().intValue()))
            .andExpect(jsonPath("$.codiceEsco").value(DEFAULT_CODICE_ESCO))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME));
    }
    @Test
    @Transactional
    public void getNonExistingOccupazione() throws Exception {
        // Get the occupazione
        restOccupazioneMockMvc.perform(get("/api/occupaziones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOccupazione() throws Exception {
        // Initialize the database
        occupazioneRepository.saveAndFlush(occupazione);

        int databaseSizeBeforeUpdate = occupazioneRepository.findAll().size();

        // Update the occupazione
        Occupazione updatedOccupazione = occupazioneRepository.findById(occupazione.getId()).get();
        // Disconnect from session so that the updates on updatedOccupazione are not directly saved in db
        em.detach(updatedOccupazione);
        updatedOccupazione
            .codiceEsco(UPDATED_CODICE_ESCO)
            .nome(UPDATED_NOME);

        restOccupazioneMockMvc.perform(put("/api/occupaziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedOccupazione)))
            .andExpect(status().isOk());

        // Validate the Occupazione in the database
        List<Occupazione> occupazioneList = occupazioneRepository.findAll();
        assertThat(occupazioneList).hasSize(databaseSizeBeforeUpdate);
        Occupazione testOccupazione = occupazioneList.get(occupazioneList.size() - 1);
        assertThat(testOccupazione.getCodiceEsco()).isEqualTo(UPDATED_CODICE_ESCO);
        assertThat(testOccupazione.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingOccupazione() throws Exception {
        int databaseSizeBeforeUpdate = occupazioneRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOccupazioneMockMvc.perform(put("/api/occupaziones")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(occupazione)))
            .andExpect(status().isBadRequest());

        // Validate the Occupazione in the database
        List<Occupazione> occupazioneList = occupazioneRepository.findAll();
        assertThat(occupazioneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOccupazione() throws Exception {
        // Initialize the database
        occupazioneRepository.saveAndFlush(occupazione);

        int databaseSizeBeforeDelete = occupazioneRepository.findAll().size();

        // Delete the occupazione
        restOccupazioneMockMvc.perform(delete("/api/occupaziones/{id}", occupazione.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Occupazione> occupazioneList = occupazioneRepository.findAll();
        assertThat(occupazioneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
