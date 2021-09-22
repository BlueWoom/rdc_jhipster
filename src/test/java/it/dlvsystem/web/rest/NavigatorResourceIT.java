package it.dlvsystem.web.rest;

import it.dlvsystem.RdcJhipsterApp;
import it.dlvsystem.domain.Navigator;
import it.dlvsystem.repository.NavigatorRepository;

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
 * Integration tests for the {@link NavigatorResource} REST controller.
 */
@SpringBootTest(classes = RdcJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NavigatorResourceIT {

    private static final String DEFAULT_CF = "AAAAAAAAAA";
    private static final String UPDATED_CF = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_COGNOME = "AAAAAAAAAA";
    private static final String UPDATED_COGNOME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_NASCITA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_NASCITA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SESSO = "AAAAAAAAAA";
    private static final String UPDATED_SESSO = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "95";
    private static final String UPDATED_TELEFONO = "612";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CITTA = "AAAAAAAAAA";
    private static final String UPDATED_CITTA = "BBBBBBBBBB";

    private static final String DEFAULT_INDIRIZZO = "AAAAAAAAAA";
    private static final String UPDATED_INDIRIZZO = "BBBBBBBBBB";

    private static final String DEFAULT_CAP = "7930";
    private static final String UPDATED_CAP = "539";

    private static final String DEFAULT_PROVINCIA = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCIA = "BBBBBBBBBB";

    private static final String DEFAULT_REGIONE = "AAAAAAAAAA";
    private static final String UPDATED_REGIONE = "BBBBBBBBBB";

    @Autowired
    private NavigatorRepository navigatorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNavigatorMockMvc;

    private Navigator navigator;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Navigator createEntity(EntityManager em) {
        Navigator navigator = new Navigator()
            .cf(DEFAULT_CF)
            .nome(DEFAULT_NOME)
            .cognome(DEFAULT_COGNOME)
            .dataNascita(DEFAULT_DATA_NASCITA)
            .sesso(DEFAULT_SESSO)
            .telefono(DEFAULT_TELEFONO)
            .email(DEFAULT_EMAIL)
            .citta(DEFAULT_CITTA)
            .indirizzo(DEFAULT_INDIRIZZO)
            .cap(DEFAULT_CAP)
            .provincia(DEFAULT_PROVINCIA)
            .regione(DEFAULT_REGIONE);
        return navigator;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Navigator createUpdatedEntity(EntityManager em) {
        Navigator navigator = new Navigator()
            .cf(UPDATED_CF)
            .nome(UPDATED_NOME)
            .cognome(UPDATED_COGNOME)
            .dataNascita(UPDATED_DATA_NASCITA)
            .sesso(UPDATED_SESSO)
            .telefono(UPDATED_TELEFONO)
            .email(UPDATED_EMAIL)
            .citta(UPDATED_CITTA)
            .indirizzo(UPDATED_INDIRIZZO)
            .cap(UPDATED_CAP)
            .provincia(UPDATED_PROVINCIA)
            .regione(UPDATED_REGIONE);
        return navigator;
    }

    @BeforeEach
    public void initTest() {
        navigator = createEntity(em);
    }

    @Test
    @Transactional
    public void createNavigator() throws Exception {
        int databaseSizeBeforeCreate = navigatorRepository.findAll().size();
        // Create the Navigator
        restNavigatorMockMvc.perform(post("/api/navigators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(navigator)))
            .andExpect(status().isCreated());

        // Validate the Navigator in the database
        List<Navigator> navigatorList = navigatorRepository.findAll();
        assertThat(navigatorList).hasSize(databaseSizeBeforeCreate + 1);
        Navigator testNavigator = navigatorList.get(navigatorList.size() - 1);
        assertThat(testNavigator.getCf()).isEqualTo(DEFAULT_CF);
        assertThat(testNavigator.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testNavigator.getCognome()).isEqualTo(DEFAULT_COGNOME);
        assertThat(testNavigator.getDataNascita()).isEqualTo(DEFAULT_DATA_NASCITA);
        assertThat(testNavigator.getSesso()).isEqualTo(DEFAULT_SESSO);
        assertThat(testNavigator.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testNavigator.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testNavigator.getCitta()).isEqualTo(DEFAULT_CITTA);
        assertThat(testNavigator.getIndirizzo()).isEqualTo(DEFAULT_INDIRIZZO);
        assertThat(testNavigator.getCap()).isEqualTo(DEFAULT_CAP);
        assertThat(testNavigator.getProvincia()).isEqualTo(DEFAULT_PROVINCIA);
        assertThat(testNavigator.getRegione()).isEqualTo(DEFAULT_REGIONE);
    }

    @Test
    @Transactional
    public void createNavigatorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = navigatorRepository.findAll().size();

        // Create the Navigator with an existing ID
        navigator.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNavigatorMockMvc.perform(post("/api/navigators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(navigator)))
            .andExpect(status().isBadRequest());

        // Validate the Navigator in the database
        List<Navigator> navigatorList = navigatorRepository.findAll();
        assertThat(navigatorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNavigators() throws Exception {
        // Initialize the database
        navigatorRepository.saveAndFlush(navigator);

        // Get all the navigatorList
        restNavigatorMockMvc.perform(get("/api/navigators?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(navigator.getId().intValue())))
            .andExpect(jsonPath("$.[*].cf").value(hasItem(DEFAULT_CF)))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cognome").value(hasItem(DEFAULT_COGNOME)))
            .andExpect(jsonPath("$.[*].dataNascita").value(hasItem(DEFAULT_DATA_NASCITA.toString())))
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
    public void getNavigator() throws Exception {
        // Initialize the database
        navigatorRepository.saveAndFlush(navigator);

        // Get the navigator
        restNavigatorMockMvc.perform(get("/api/navigators/{id}", navigator.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(navigator.getId().intValue()))
            .andExpect(jsonPath("$.cf").value(DEFAULT_CF))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.cognome").value(DEFAULT_COGNOME))
            .andExpect(jsonPath("$.dataNascita").value(DEFAULT_DATA_NASCITA.toString()))
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
    public void getNonExistingNavigator() throws Exception {
        // Get the navigator
        restNavigatorMockMvc.perform(get("/api/navigators/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNavigator() throws Exception {
        // Initialize the database
        navigatorRepository.saveAndFlush(navigator);

        int databaseSizeBeforeUpdate = navigatorRepository.findAll().size();

        // Update the navigator
        Navigator updatedNavigator = navigatorRepository.findById(navigator.getId()).get();
        // Disconnect from session so that the updates on updatedNavigator are not directly saved in db
        em.detach(updatedNavigator);
        updatedNavigator
            .cf(UPDATED_CF)
            .nome(UPDATED_NOME)
            .cognome(UPDATED_COGNOME)
            .dataNascita(UPDATED_DATA_NASCITA)
            .sesso(UPDATED_SESSO)
            .telefono(UPDATED_TELEFONO)
            .email(UPDATED_EMAIL)
            .citta(UPDATED_CITTA)
            .indirizzo(UPDATED_INDIRIZZO)
            .cap(UPDATED_CAP)
            .provincia(UPDATED_PROVINCIA)
            .regione(UPDATED_REGIONE);

        restNavigatorMockMvc.perform(put("/api/navigators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedNavigator)))
            .andExpect(status().isOk());

        // Validate the Navigator in the database
        List<Navigator> navigatorList = navigatorRepository.findAll();
        assertThat(navigatorList).hasSize(databaseSizeBeforeUpdate);
        Navigator testNavigator = navigatorList.get(navigatorList.size() - 1);
        assertThat(testNavigator.getCf()).isEqualTo(UPDATED_CF);
        assertThat(testNavigator.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testNavigator.getCognome()).isEqualTo(UPDATED_COGNOME);
        assertThat(testNavigator.getDataNascita()).isEqualTo(UPDATED_DATA_NASCITA);
        assertThat(testNavigator.getSesso()).isEqualTo(UPDATED_SESSO);
        assertThat(testNavigator.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testNavigator.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testNavigator.getCitta()).isEqualTo(UPDATED_CITTA);
        assertThat(testNavigator.getIndirizzo()).isEqualTo(UPDATED_INDIRIZZO);
        assertThat(testNavigator.getCap()).isEqualTo(UPDATED_CAP);
        assertThat(testNavigator.getProvincia()).isEqualTo(UPDATED_PROVINCIA);
        assertThat(testNavigator.getRegione()).isEqualTo(UPDATED_REGIONE);
    }

    @Test
    @Transactional
    public void updateNonExistingNavigator() throws Exception {
        int databaseSizeBeforeUpdate = navigatorRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNavigatorMockMvc.perform(put("/api/navigators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(navigator)))
            .andExpect(status().isBadRequest());

        // Validate the Navigator in the database
        List<Navigator> navigatorList = navigatorRepository.findAll();
        assertThat(navigatorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNavigator() throws Exception {
        // Initialize the database
        navigatorRepository.saveAndFlush(navigator);

        int databaseSizeBeforeDelete = navigatorRepository.findAll().size();

        // Delete the navigator
        restNavigatorMockMvc.perform(delete("/api/navigators/{id}", navigator.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Navigator> navigatorList = navigatorRepository.findAll();
        assertThat(navigatorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
