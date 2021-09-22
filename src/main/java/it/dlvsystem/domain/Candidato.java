package it.dlvsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Candidato.
 */
@Entity
@Table(name = "candidato")
public class Candidato implements Serializable {

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

    @Column(name = "luogo_nascita")
    private String luogoNascita;

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

    @OneToMany(mappedBy = "candidato")
    private Set<SkillUtente> skillUtentes = new HashSet<>();

    @OneToMany(mappedBy = "candidato")
    private Set<Cv> cvs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "candidatoes", allowSetters = true)
    private Navigator navigator;

    @ManyToOne
    @JsonIgnoreProperties(value = "candidatoes", allowSetters = true)
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

    public Candidato cf(String cf) {
        this.cf = cf;
        return this;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getNome() {
        return nome;
    }

    public Candidato nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Candidato cognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public Candidato dataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
        return this;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public Candidato luogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
        return this;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public String getSesso() {
        return sesso;
    }

    public Candidato sesso(String sesso) {
        this.sesso = sesso;
        return this;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getTelefono() {
        return telefono;
    }

    public Candidato telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public Candidato email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCitta() {
        return citta;
    }

    public Candidato citta(String citta) {
        this.citta = citta;
        return this;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Candidato indirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
        return this;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCap() {
        return cap;
    }

    public Candidato cap(String cap) {
        this.cap = cap;
        return this;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getProvincia() {
        return provincia;
    }

    public Candidato provincia(String provincia) {
        this.provincia = provincia;
        return this;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegione() {
        return regione;
    }

    public Candidato regione(String regione) {
        this.regione = regione;
        return this;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public Set<SkillUtente> getSkillUtentes() {
        return skillUtentes;
    }

    public Candidato skillUtentes(Set<SkillUtente> skillUtentes) {
        this.skillUtentes = skillUtentes;
        return this;
    }

    public Candidato addSkillUtente(SkillUtente skillUtente) {
        this.skillUtentes.add(skillUtente);
        skillUtente.setCandidato(this);
        return this;
    }

    public Candidato removeSkillUtente(SkillUtente skillUtente) {
        this.skillUtentes.remove(skillUtente);
        skillUtente.setCandidato(null);
        return this;
    }

    public void setSkillUtentes(Set<SkillUtente> skillUtentes) {
        this.skillUtentes = skillUtentes;
    }

    public Set<Cv> getCvs() {
        return cvs;
    }

    public Candidato cvs(Set<Cv> cvs) {
        this.cvs = cvs;
        return this;
    }

    public Candidato addCv(Cv cv) {
        this.cvs.add(cv);
        cv.setCandidato(this);
        return this;
    }

    public Candidato removeCv(Cv cv) {
        this.cvs.remove(cv);
        cv.setCandidato(null);
        return this;
    }

    public void setCvs(Set<Cv> cvs) {
        this.cvs = cvs;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public Candidato navigator(Navigator navigator) {
        this.navigator = navigator;
        return this;
    }

    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    public Login getLogin() {
        return login;
    }

    public Candidato login(Login login) {
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
        if (!(o instanceof Candidato)) {
            return false;
        }
        return id != null && id.equals(((Candidato) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Candidato{" +
            "id=" + getId() +
            ", cf='" + getCf() + "'" +
            ", nome='" + getNome() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", dataNascita='" + getDataNascita() + "'" +
            ", luogoNascita='" + getLuogoNascita() + "'" +
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
