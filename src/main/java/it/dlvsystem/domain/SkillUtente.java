package it.dlvsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SkillUtente.
 */
@Entity
@Table(name = "skill_utente")
public class SkillUtente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cf_utente")
    private String cfUtente;

    @Column(name = "codice_esco_skill")
    private String codiceEscoSkill;

    @ManyToOne
    @JsonIgnoreProperties(value = "skillUtentes", allowSetters = true)
    private Skill skill;

    @ManyToOne
    @JsonIgnoreProperties(value = "skillUtentes", allowSetters = true)
    private Candidato candidato;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCfUtente() {
        return cfUtente;
    }

    public SkillUtente cfUtente(String cfUtente) {
        this.cfUtente = cfUtente;
        return this;
    }

    public void setCfUtente(String cfUtente) {
        this.cfUtente = cfUtente;
    }

    public String getCodiceEscoSkill() {
        return codiceEscoSkill;
    }

    public SkillUtente codiceEscoSkill(String codiceEscoSkill) {
        this.codiceEscoSkill = codiceEscoSkill;
        return this;
    }

    public void setCodiceEscoSkill(String codiceEscoSkill) {
        this.codiceEscoSkill = codiceEscoSkill;
    }

    public Skill getSkill() {
        return skill;
    }

    public SkillUtente skill(Skill skill) {
        this.skill = skill;
        return this;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public SkillUtente candidato(Candidato candidato) {
        this.candidato = candidato;
        return this;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SkillUtente)) {
            return false;
        }
        return id != null && id.equals(((SkillUtente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SkillUtente{" +
            "id=" + getId() +
            ", cfUtente='" + getCfUtente() + "'" +
            ", codiceEscoSkill='" + getCodiceEscoSkill() + "'" +
            "}";
    }
}
