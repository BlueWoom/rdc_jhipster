package it.dlvsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A OffertaSkill.
 */
@Entity
@Table(name = "offerta_skill")
public class OffertaSkill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "optional")
    private Boolean optional;

    @ManyToOne
    @JsonIgnoreProperties(value = "offertaSkills", allowSetters = true)
    private Skill skill;

    @ManyToOne
    @JsonIgnoreProperties(value = "offertaSkills", allowSetters = true)
    private Offerta offerta;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isOptional() {
        return optional;
    }

    public OffertaSkill optional(Boolean optional) {
        this.optional = optional;
        return this;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public Skill getSkill() {
        return skill;
    }

    public OffertaSkill skill(Skill skill) {
        this.skill = skill;
        return this;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Offerta getOfferta() {
        return offerta;
    }

    public OffertaSkill offerta(Offerta offerta) {
        this.offerta = offerta;
        return this;
    }

    public void setOfferta(Offerta offerta) {
        this.offerta = offerta;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OffertaSkill)) {
            return false;
        }
        return id != null && id.equals(((OffertaSkill) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OffertaSkill{" +
            "id=" + getId() +
            ", optional='" + isOptional() + "'" +
            "}";
    }
}
