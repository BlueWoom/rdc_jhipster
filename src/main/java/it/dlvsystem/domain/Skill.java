package it.dlvsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Skill.
 */
@Entity
@Table(name = "skill")
public class Skill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[0-9]+")
    @Column(name = "codice_esco")
    private String codiceEsco;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private String tipo;

    @OneToMany(mappedBy = "skill")
    private Set<SkillUtente> skillUtentes = new HashSet<>();

    @OneToMany(mappedBy = "skill")
    private Set<OffertaSkill> offertaSkills = new HashSet<>();

    @ManyToMany(mappedBy = "skills")
    @JsonIgnore
    private Set<Occupazione> occupaziones = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiceEsco() {
        return codiceEsco;
    }

    public Skill codiceEsco(String codiceEsco) {
        this.codiceEsco = codiceEsco;
        return this;
    }

    public void setCodiceEsco(String codiceEsco) {
        this.codiceEsco = codiceEsco;
    }

    public String getNome() {
        return nome;
    }

    public Skill nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Skill tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<SkillUtente> getSkillUtentes() {
        return skillUtentes;
    }

    public Skill skillUtentes(Set<SkillUtente> skillUtentes) {
        this.skillUtentes = skillUtentes;
        return this;
    }

    public Skill addSkillUtente(SkillUtente skillUtente) {
        this.skillUtentes.add(skillUtente);
        skillUtente.setSkill(this);
        return this;
    }

    public Skill removeSkillUtente(SkillUtente skillUtente) {
        this.skillUtentes.remove(skillUtente);
        skillUtente.setSkill(null);
        return this;
    }

    public void setSkillUtentes(Set<SkillUtente> skillUtentes) {
        this.skillUtentes = skillUtentes;
    }

    public Set<OffertaSkill> getOffertaSkills() {
        return offertaSkills;
    }

    public Skill offertaSkills(Set<OffertaSkill> offertaSkills) {
        this.offertaSkills = offertaSkills;
        return this;
    }

    public Skill addOffertaSkill(OffertaSkill offertaSkill) {
        this.offertaSkills.add(offertaSkill);
        offertaSkill.setSkill(this);
        return this;
    }

    public Skill removeOffertaSkill(OffertaSkill offertaSkill) {
        this.offertaSkills.remove(offertaSkill);
        offertaSkill.setSkill(null);
        return this;
    }

    public void setOffertaSkills(Set<OffertaSkill> offertaSkills) {
        this.offertaSkills = offertaSkills;
    }

    public Set<Occupazione> getOccupaziones() {
        return occupaziones;
    }

    public Skill occupaziones(Set<Occupazione> occupaziones) {
        this.occupaziones = occupaziones;
        return this;
    }

    public Skill addOccupazione(Occupazione occupazione) {
        this.occupaziones.add(occupazione);
        occupazione.getSkills().add(this);
        return this;
    }

    public Skill removeOccupazione(Occupazione occupazione) {
        this.occupaziones.remove(occupazione);
        occupazione.getSkills().remove(this);
        return this;
    }

    public void setOccupaziones(Set<Occupazione> occupaziones) {
        this.occupaziones = occupaziones;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Skill)) {
            return false;
        }
        return id != null && id.equals(((Skill) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Skill{" +
            "id=" + getId() +
            ", codiceEsco='" + getCodiceEsco() + "'" +
            ", nome='" + getNome() + "'" +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}
