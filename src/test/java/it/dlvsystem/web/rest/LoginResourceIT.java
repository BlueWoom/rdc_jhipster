package it.dlvsystem.web.rest;

import it.dlvsystem.RdcJhipsterApp;
import it.dlvsystem.domain.Login;
import it.dlvsystem.repository.LoginRepository;

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
 * Integration tests for the {@link LoginResource} REST controller.
 */
@SpringBootTest(classes = RdcJhipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LoginResourceIT {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_RUOLO = "AAAAAAAAAA";
    private static final String UPDATED_RUOLO = "BBBBBBBBBB";

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLoginMockMvc;

    private Login login;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Login createEntity(EntityManager em) {
        Login login = new Login()
            .username(DEFAULT_USERNAME)
            .ruolo(DEFAULT_RUOLO);
        return login;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Login createUpdatedEntity(EntityManager em) {
        Login login = new Login()
            .username(UPDATED_USERNAME)
            .ruolo(UPDATED_RUOLO);
        return login;
    }

    @BeforeEach
    public void initTest() {
        login = createEntity(em);
    }

    @Test
    @Transactional
    public void createLogin() throws Exception {
        int databaseSizeBeforeCreate = loginRepository.findAll().size();
        // Create the Login
        restLoginMockMvc.perform(post("/api/logins")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(login)))
            .andExpect(status().isCreated());

        // Validate the Login in the database
        List<Login> loginList = loginRepository.findAll();
        assertThat(loginList).hasSize(databaseSizeBeforeCreate + 1);
        Login testLogin = loginList.get(loginList.size() - 1);
        assertThat(testLogin.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testLogin.getRuolo()).isEqualTo(DEFAULT_RUOLO);
    }

    @Test
    @Transactional
    public void createLoginWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loginRepository.findAll().size();

        // Create the Login with an existing ID
        login.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoginMockMvc.perform(post("/api/logins")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(login)))
            .andExpect(status().isBadRequest());

        // Validate the Login in the database
        List<Login> loginList = loginRepository.findAll();
        assertThat(loginList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLogins() throws Exception {
        // Initialize the database
        loginRepository.saveAndFlush(login);

        // Get all the loginList
        restLoginMockMvc.perform(get("/api/logins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(login.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].ruolo").value(hasItem(DEFAULT_RUOLO)));
    }
    
    @Test
    @Transactional
    public void getLogin() throws Exception {
        // Initialize the database
        loginRepository.saveAndFlush(login);

        // Get the login
        restLoginMockMvc.perform(get("/api/logins/{id}", login.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(login.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.ruolo").value(DEFAULT_RUOLO));
    }
    @Test
    @Transactional
    public void getNonExistingLogin() throws Exception {
        // Get the login
        restLoginMockMvc.perform(get("/api/logins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLogin() throws Exception {
        // Initialize the database
        loginRepository.saveAndFlush(login);

        int databaseSizeBeforeUpdate = loginRepository.findAll().size();

        // Update the login
        Login updatedLogin = loginRepository.findById(login.getId()).get();
        // Disconnect from session so that the updates on updatedLogin are not directly saved in db
        em.detach(updatedLogin);
        updatedLogin
            .username(UPDATED_USERNAME)
            .ruolo(UPDATED_RUOLO);

        restLoginMockMvc.perform(put("/api/logins")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLogin)))
            .andExpect(status().isOk());

        // Validate the Login in the database
        List<Login> loginList = loginRepository.findAll();
        assertThat(loginList).hasSize(databaseSizeBeforeUpdate);
        Login testLogin = loginList.get(loginList.size() - 1);
        assertThat(testLogin.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testLogin.getRuolo()).isEqualTo(UPDATED_RUOLO);
    }

    @Test
    @Transactional
    public void updateNonExistingLogin() throws Exception {
        int databaseSizeBeforeUpdate = loginRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoginMockMvc.perform(put("/api/logins")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(login)))
            .andExpect(status().isBadRequest());

        // Validate the Login in the database
        List<Login> loginList = loginRepository.findAll();
        assertThat(loginList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLogin() throws Exception {
        // Initialize the database
        loginRepository.saveAndFlush(login);

        int databaseSizeBeforeDelete = loginRepository.findAll().size();

        // Delete the login
        restLoginMockMvc.perform(delete("/api/logins/{id}", login.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Login> loginList = loginRepository.findAll();
        assertThat(loginList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
