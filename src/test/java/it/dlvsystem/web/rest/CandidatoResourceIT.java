package it.dlvsystem.web.rest;

import it.dlvsystem.RdcJhipsterApp;
import it.dlvsystem.domain.Candidato;
import it.dlvsystem.repository.CandidatoRepository;

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
 * Integration tests for the {@link CandidatoResource} REST controller.
 */
@SpringBootTest(classes = RdcJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CandidatoResourceIT {

    private static final String DEFAULT_CF = "AAAAAAAAAA";
    private static final String UPDATED_CF = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_COGNOME = "AAAAAAAAAA";
    private static final String UPDATED_COGNOME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_NASCITA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_NASCITA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LUOGO_NASCITA = "AAAAAAAAAA";
    private static final String UPDATED_LUOGO_NASCITA = "BBBBBBBBBB";

    private static final String DEFAULT_SESSO = "AAAAAAAAAA";
    private static final String UPDATED_SESSO = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "47350";
    private static final String UPDATED_TELEFONO = "23";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CITTA = "AAAAAAAAAA";
    private static final String UPDATED_CITTA = "BBBBBBBBBB";

    private static final String DEFAULT_INDIRIZZO = "AAAAAAAAAA";
    private static final String UPDATED_INDIRIZZO = "BBBBBBBBBB";

    private static final String DEFAULT_CAP = "888";
    private static final String UPDATED_CAP = "3282";

    private static final String DEFAULT_PROVINCIA = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCIA = "BBBBBBBBBB";

    private static final String DEFAULT_REGIONE = "AAAAAAAAAA";
    private static final String UPDATED_REGIONE = "BBBBBBBBBB";

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCandidatoMockMvc;

    private Candidato candidato;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Candidato createEntity(EntityManager em) {
        Candidato candidato = new Candidato()
            .cf(DEFAULT_CF)
            .nome(DEFAULT_NOME)
            .cognome(DEFAULT_COGNOME)
            .dataNascita(DEFAULT_DATA_NASCITA)
            .luogoNascita(DEFAULT_LUOGO_NASCITA)
            .sesso(DEFAULT_SESSO)
            .telefono(DEFAULT_TELEFONO)
            .email(DEFAULT_EMAIL)
            .citta(DEFAULT_CITTA)
            .indirizzo(DEFAULT_INDIRIZZO)
            .cap(DEFAULT_CAP)
            .provincia(DEFAULT_PROVINCIA)
            .regione(DEFAULT_REGIONE);
        return candidato;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Candidato createUpdatedEntity(EntityManager em) {
        Candidato candidato = new Candidato()
            .cf(UPDATED_CF)
            .nome(UPDATED_NOME)
            .cognome(UPDATED_COGNOME)
            .dataNascita(UPDATED_DATA_NASCITA)
            .luogoNascita(UPDATED_LUOGO_NASCITA)
            .sesso(UPDATED_SESSO)
            .telefono(UPDATED_TELEFONO)
            .email(UPDATED_EMAIL)
            .citta(UPDATED_CITTA)
            .indirizzo(UPDATED_INDIRIZZO)
            .cap(UPDATED_CAP)
            .provincia(UPDATED_PROVINCIA)
            .regione(UPDATED_REGIONE);
        return candidato;
    }

    @BeforeEach
    public void initTest() {
        candidato = createEntity(em);
    }

    @Test
    @Transactional
    public void createCandidato() throws Exception {
        int databaseSizeBeforeCreate = candidatoRepository.findAll().size();
        // Create the Candidato
        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidato)))
            .andExpect(status().isCreated());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeCreate + 1);
        Candidato testCandidato = candidatoList.get(candidatoList.size() - 1);
        assertThat(testCandidato.getCf()).isEqualTo(DEFAULT_CF);
        assertThat(testCandidato.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testCandidato.getCognome()).isEqualTo(DEFAULT_COGNOME);
        assertThat(testCandidato.getDataNascita()).isEqualTo(DEFAULT_DATA_NASCITA);
        assertThat(testCandidato.getLuogoNascita()).isEqualTo(DEFAULT_LUOGO_NASCITA);
        assertThat(testCandidato.getSesso()).isEqualTo(DEFAULT_SESSO);
        assertThat(testCandidato.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testCandidato.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCandidato.getCitta()).isEqualTo(DEFAULT_CITTA);
        assertThat(testCandidato.getIndirizzo()).isEqualTo(DEFAULT_INDIRIZZO);
        assertThat(testCandidato.getCap()).isEqualTo(DEFAULT_CAP);
        assertThat(testCandidato.getProvincia()).isEqualTo(DEFAULT_PROVINCIA);
        assertThat(testCandidato.getRegione()).isEqualTo(DEFAULT_REGIONE);
    }

    @Test
    @Transactional
    public void createCandidatoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = candidatoRepository.findAll().size();

        // Create the Candidato with an existing ID
        candidato.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCandidatoMockMvc.perform(post("/api/candidatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidato)))
            .andExpect(status().isBadRequest());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCandidatoes() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get all the candidatoList
        restCandidatoMockMvc.perform(get("/api/candidatoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(candidato.getId().intValue())))
            .andExpect(jsonPath("$.[*].cf").value(hasItem(DEFAULT_CF)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME)))
            .andExpect(jsonPath("$.[*].dataNascita").value(hasItem(DEFAULT_DATA_NASCITA.toString())))
            .andExpect(jsonPath("$.[*].luogoNascita").value(hasItem(DEFAULT_LUOGO_NASCITA)))
            .andExpect(jsonPath("$.[*].sesso").value(hasItem(DEFAULT_SESSO)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].citta").value(hasItem(DEFAULT_CITTA)))
            .andExpect(jsonPath("$.[*].indirizzo").value(hasItem(DEFAULT_INDIRIZZO)))
            .andExpect(jsonPath("$.[*].cap").value(hasItem(DEFAULT_CAP)))
            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_PROVINCIA)))
            .andExpect(jsonPath("$.[*].regione").value(hasItem(DEFAULT_REGIONE)));
    }
    
    @Test
    @Transactional
    public void getCandidato() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        // Get the candidato
        restCandidatoMockMvc.perform(get("/api/candidatoes/{id}", candidato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(candidato.getId().intValue()))
            .andExpect(jsonPath("$.cf").value(DEFAULT_CF))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.cognome").value(DEFAULT_COGNOME))
            .andExpect(jsonPath("$.dataNascita").value(DEFAULT_DATA_NASCITA.toString()))
            .andExpect(jsonPath("$.luogoNascita").value(DEFAULT_LUOGO_NASCITA))
            .andExpect(jsonPath("$.sesso").value(DEFAULT_SESSO))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.citta").value(DEFAULT_CITTA))
            .andExpect(jsonPath("$.indirizzo").value(DEFAULT_INDIRIZZO))
            .andExpect(jsonPath("$.cap").value(DEFAULT_CAP))
            .andExpect(jsonPath("$.provincia").value(DEFAULT_PROVINCIA))
            .andExpect(jsonPath("$.regione").value(DEFAULT_REGIONE));
    }
    @Test
    @Transactional
    public void getNonExistingCandidato() throws Exception {
        // Get the candidato
        restCandidatoMockMvc.perform(get("/api/candidatoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCandidato() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        int databaseSizeBeforeUpdate = candidatoRepository.findAll().size();

        // Update the candidato
        Candidato updatedCandidato = candidatoRepository.findById(candidato.getId()).get();
        // Disconnect from session so that the updates on updatedCandidato are not directly saved in db
        em.detach(updatedCandidato);
        updatedCandidato
            .cf(UPDATED_CF)
            .nome(UPDATED_NOME)
            .cognome(UPDATED_COGNOME)
            .dataNascita(UPDATED_DATA_NASCITA)
            .luogoNascita(UPDATED_LUOGO_NASCITA)
            .sesso(UPDATED_SESSO)
            .telefono(UPDATED_TELEFONO)
            .email(UPDATED_EMAIL)
            .citta(UPDATED_CITTA)
            .indirizzo(UPDATED_INDIRIZZO)
            .cap(UPDATED_CAP)
            .provincia(UPDATED_PROVINCIA)
            .regione(UPDATED_REGIONE);

        restCandidatoMockMvc.perform(put("/api/candidatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCandidato)))
            .andExpect(status().isOk());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeUpdate);
        Candidato testCandidato = candidatoList.get(candidatoList.size() - 1);
        assertThat(testCandidato.getCf()).isEqualTo(UPDATED_CF);
        assertThat(testCandidato.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testCandidato.getCognome()).isEqualTo(UPDATED_COGNOME);
        assertThat(testCandidato.getDataNascita()).isEqualTo(UPDATED_DATA_NASCITA);
        assertThat(testCandidato.getLuogoNascita()).isEqualTo(UPDATED_LUOGO_NASCITA);
        assertThat(testCandidato.getSesso()).isEqualTo(UPDATED_SESSO);
        assertThat(testCandidato.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testCandidato.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCandidato.getCitta()).isEqualTo(UPDATED_CITTA);
        assertThat(testCandidato.getIndirizzo()).isEqualTo(UPDATED_INDIRIZZO);
        assertThat(testCandidato.getCap()).isEqualTo(UPDATED_CAP);
        assertThat(testCandidato.getProvincia()).isEqualTo(UPDATED_PROVINCIA);
        assertThat(testCandidato.getRegione()).isEqualTo(UPDATED_REGIONE);
    }

    @Test
    @Transactional
    public void updateNonExistingCandidato() throws Exception {
        int databaseSizeBeforeUpdate = candidatoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCandidatoMockMvc.perform(put("/api/candidatoes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(candidato)))
            .andExpect(status().isBadRequest());

        // Validate the Candidato in the database
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCandidato() throws Exception {
        // Initialize the database
        candidatoRepository.saveAndFlush(candidato);

        int databaseSizeBeforeDelete = candidatoRepository.findAll().size();

        // Delete the candidato
        restCandidatoMockMvc.perform(delete("/api/candidatoes/{id}", candidato.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Candidato> candidatoList = candidatoRepository.findAll();
        assertThat(candidatoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
