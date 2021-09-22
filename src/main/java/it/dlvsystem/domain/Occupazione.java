package it.dlvsystem.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Occupazione.
 */
@Entity
@Table(name = "occupazione")
public class Occupazione implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "codice_esco", nullable = false, unique = true)
    private String codiceEsco;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "occupazione")
    private Set<OffertaOccupazioneRichiesta> offertaOccupazioneRichiestas = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "occupazione_esperienza",
               joinColumns = @JoinColumn(name = "occupazione_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "esperienza_id", referencedColumnName = "id"))
    private Set<Esperienza> esperienzas = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "occupazione_offerta",
               joinColumns = @JoinColumn(name = "occupazione_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "offerta_id", referencedColumnName = "id"))
    private Set<Offerta> offertas = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "occupazione_skill",
               joinColumns = @JoinColumn(name = "occupazione_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"))
    private Set<Skill> skills = new HashSet<>();

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

    public Occupazione codiceEsco(String codiceEsco) {
        this.codiceEsco = codiceEsco;
        return this;
    }

    public void setCodiceEsco(String codiceEsco) {
        this.codiceEsco = codiceEsco;
    }

    public String getNome() {
        return nome;
    }

    public Occupazione nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<OffertaOccupazioneRichiesta> getOffertaOccupazioneRichiestas() {
        return offertaOccupazioneRichiestas;
    }

    public Occupazione offertaOccupazioneRichiestas(Set<OffertaOccupazioneRichiesta> offertaOccupazioneRichiestas) {
        this.offertaOccupazioneRichiestas = offertaOccupazioneRichiestas;
        return this;
    }

    public Occupazione addOffertaOccupazioneRichiesta(OffertaOccupazioneRichiesta offertaOccupazioneRichiesta) {
        this.offertaOccupazioneRichiestas.add(offertaOccupazioneRichiesta);
        offertaOccupazioneRichiesta.setOccupazione(this);
        return this;
    }

    public Occupazione removeOffertaOccupazioneRichiesta(OffertaOccupazioneRichiesta offertaOccupazioneRichiesta) {
        this.offertaOccupazioneRichiestas.remove(offertaOccupazioneRichiesta);
        offertaOccupazioneRichiesta.setOccupazione(null);
        return this;
    }

    public void setOffertaOccupazioneRichiestas(Set<OffertaOccupazioneRichiesta> offertaOccupazioneRichiestas) {
        this.offertaOccupazioneRichiestas = offertaOccupazioneRichiestas;
    }

    public Set<Esperienza> getEsperienzas() {
        return esperienzas;
    }

    public Occupazione esperienzas(Set<Esperienza> esperienzas) {
        this.esperienzas = esperienzas;
        return this;
    }

    public Occupazione addEsperienza(Esperienza esperienza) {
        this.esperienzas.add(esperienza);
        esperienza.getOccupaziones().add(this);
        return this;
    }

    public Occupazione removeEsperienza(Esperienza esperienza) {
        this.esperienzas.remove(esperienza);
        esperienza.getOccupaziones().remove(this);
        return this;
    }

    public void setEsperienzas(Set<Esperienza> esperienzas) {
        this.esperienzas = esperienzas;
    }

    public Set<Offerta> getOffertas() {
        return offertas;
    }

    public Occupazione offertas(Set<Offerta> offertas) {
        this.offertas = offertas;
        return this;
    }

    public Occupazione addOfferta(Offerta offerta) {
        this.offertas.add(offerta);
        offerta.getOccupaziones().add(this);
        return this;
    }

    public Occupazione removeOfferta(Offerta offerta) {
        this.offertas.remove(offerta);
        offerta.getOccupaziones().remove(this);
        return this;
    }

    public void setOffertas(Set<Offerta> offertas) {
        this.offertas = offertas;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public Occupazione skills(Set<Skill> skills) {
        this.skills = skills;
        return this;
    }

    public Occupazione addSkill(Skill skill) {
        this.skills.add(skill);
        skill.getOccupaziones().add(this);
        return this;
    }

    public Occupazione removeSkill(Skill skill) {
        this.skills.remove(skill);
        skill.getOccupaziones().remove(this);
        return this;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Occupazione)) {
            return false;
        }
        return id != null && id.equals(((Occupazione) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Occupazione{" +
            "id=" + getId() +
            ", codiceEsco='" + getCodiceEsco() + "'" +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
