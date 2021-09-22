package it.dlvsystem.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Login.
 */
@Entity
@Table(name = "login")
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "ruolo")
    private String ruolo;

    @OneToMany(mappedBy = "login")
    private Set<Navigator> navigators = new HashSet<>();

    @OneToMany(mappedBy = "login")
    private Set<Azienda> aziendas = new HashSet<>();

    @OneToMany(mappedBy = "login")
    private Set<Candidato> candidatoes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Login username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRuolo() {
        return ruolo;
    }

    public Login ruolo(String ruolo) {
        this.ruolo = ruolo;
        return this;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public Set<Navigator> getNavigators() {
        return navigators;
    }

    public Login navigators(Set<Navigator> navigators) {
        this.navigators = navigators;
        return this;
    }

    public Login addNavigator(Navigator navigator) {
        this.navigators.add(navigator);
        navigator.setLogin(this);
        return this;
    }

    public Login removeNavigator(Navigator navigator) {
        this.navigators.remove(navigator);
        navigator.setLogin(null);
        return this;
    }

    public void setNavigators(Set<Navigator> navigators) {
        this.navigators = navigators;
    }

    public Set<Azienda> getAziendas() {
        return aziendas;
    }

    public Login aziendas(Set<Azienda> aziendas) {
        this.aziendas = aziendas;
        return this;
    }

    public Login addAzienda(Azienda azienda) {
        this.aziendas.add(azienda);
        azienda.setLogin(this);
        return this;
    }

    public Login removeAzienda(Azienda azienda) {
        this.aziendas.remove(azienda);
        azienda.setLogin(null);
        return this;
    }

    public void setAziendas(Set<Azienda> aziendas) {
        this.aziendas = aziendas;
    }

    public Set<Candidato> getCandidatoes() {
        return candidatoes;
    }

    public Login candidatoes(Set<Candidato> candidatoes) {
        this.candidatoes = candidatoes;
        return this;
    }

    public Login addCandidato(Candidato candidato) {
        this.candidatoes.add(candidato);
        candidato.setLogin(this);
        return this;
    }

    public Login removeCandidato(Candidato candidato) {
        this.candidatoes.remove(candidato);
        candidato.setLogin(null);
        return this;
    }

    public void setCandidatoes(Set<Candidato> candidatoes) {
        this.candidatoes = candidatoes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Login)) {
            return false;
        }
        return id != null && id.equals(((Login) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Login{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", ruolo='" + getRuolo() + "'" +
            "}";
    }
}
