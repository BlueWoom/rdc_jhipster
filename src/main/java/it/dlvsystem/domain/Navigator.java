package it.dlvsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Navigator.
 */
@Entity
@Table(name = "navigator")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Navigator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cf")
    private String cf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "data_nascita")
    private LocalDate dataNascita;

    @Column(name = "sesso")
    private String sesso;

    @Pattern(regexp = "[0-9]+")
    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "citta")
    private String citta;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Pattern(regexp = "[0-9]+")
    @Column(name = "cap")
    private String cap;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "regione")
    private String regione;

    @OneToMany(mappedBy = "navigator")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Candidato> candidatoes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "navigators", allowSetters = true)
    private Login login;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCf() {
        return cf;
    }

    public Navigator cf(String cf) {
        this.cf = cf;
        return this;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getNome() {
        return nome;
    }

    public Navigator nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Navigator cognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public Navigator dataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
        return this;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getSesso() {
        return sesso;
    }

    public Navigator sesso(String sesso) {
        this.sesso = sesso;
        return this;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getTelefono() {
        return telefono;
    }

    public Navigator telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public Navigator email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCitta() {
        return citta;
    }

    public Navigator citta(String citta) {
        this.citta = citta;
        return this;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Navigator indirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
        return this;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCap() {
        return cap;
    }

    public Navigator cap(String cap) {
        this.cap = cap;
        return this;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getProvincia() {
        return provincia;
    }

    public Navigator provincia(String provincia) {
        this.provincia = provincia;
        return this;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegione() {
        return regione;
    }

    public Navigator regione(String regione) {
        this.regione = regione;
        return this;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public Set<Candidato> getCandidatoes() {
        return candidatoes;
    }

    public Navigator candidatoes(Set<Candidato> candidatoes) {
        this.candidatoes = candidatoes;
        return this;
    }

    public Navigator addCandidato(Candidato candidato) {
        this.candidatoes.add(candidato);
        candidato.setNavigator(this);
        return this;
    }

    public Navigator removeCandidato(Candidato candidato) {
        this.candidatoes.remove(candidato);
        candidato.setNavigator(null);
        return this;
    }

    public void setCandidatoes(Set<Candidato> candidatoes) {
        this.candidatoes = candidatoes;
    }

    public Login getLogin() {
        return login;
    }

    public Navigator login(Login login) {
        this.login = login;
        return this;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Navigator)) {
            return false;
        }
        return id != null && id.equals(((Navigator) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Navigator{" +
            "id=" + getId() +
            ", cf='" + getCf() + "'" +
            ", nome='" + getNome() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", dataNascita='" + getDataNascita() + "'" +
            ", sesso='" + getSesso() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", email='" + getEmail() + "'" +
            ", citta='" + getCitta() + "'" +
            ", indirizzo='" + getIndirizzo() + "'" +
            ", cap='" + getCap() + "'" +
            ", provincia='" + getProvincia() + "'" +
            ", regione='" + getRegione() + "'" +
            "}";
    }
}
