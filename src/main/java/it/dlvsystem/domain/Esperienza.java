package it.dlvsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Esperienza.
 */
@Entity
@Table(name = "esperienza")
public class Esperienza implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attivita")
    private String attivita;

    @Column(name = "dal")
    private LocalDate dal;

    @Column(name = "al")
    private LocalDate al;

    @Column(name = "datore_lavoro")
    private String datoreLavoro;

    @Column(name = "sede_lavoro")
    private String sedeLavoro;

    @ManyToOne
    @JsonIgnoreProperties(value = "esperienzas", allowSetters = true)
    private Cv cv;

    @ManyToMany(mappedBy = "esperienzas")
    @JsonIgnore
    private Set<Occupazione> occupaziones = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttivita() {
        return attivita;
    }

    public Esperienza attivita(String attivita) {
        this.attivita = attivita;
        return this;
    }

    public void setAttivita(String attivita) {
        this.attivita = attivita;
    }

    public LocalDate getDal() {
        return dal;
    }

    public Esperienza dal(LocalDate dal) {
        this.dal = dal;
        return this;
    }

    public void setDal(LocalDate dal) {
        this.dal = dal;
    }

    public LocalDate getAl() {
        return al;
    }

    public Esperienza al(LocalDate al) {
        this.al = al;
        return this;
    }

    public void setAl(LocalDate al) {
        this.al = al;
    }

    public String getDatoreLavoro() {
        return datoreLavoro;
    }

    public Esperienza datoreLavoro(String datoreLavoro) {
        this.datoreLavoro = datoreLavoro;
        return this;
    }

    public void setDatoreLavoro(String datoreLavoro) {
        this.datoreLavoro = datoreLavoro;
    }

    public String getSedeLavoro() {
        return sedeLavoro;
    }

    public Esperienza sedeLavoro(String sedeLavoro) {
        this.sedeLavoro = sedeLavoro;
        return this;
    }

    public void setSedeLavoro(String sedeLavoro) {
        this.sedeLavoro = sedeLavoro;
    }

    public Cv getCv() {
        return cv;
    }

    public Esperienza cv(Cv cv) {
        this.cv = cv;
        return this;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }

    public Set<Occupazione> getOccupaziones() {
        return occupaziones;
    }

    public Esperienza occupaziones(Set<Occupazione> occupaziones) {
        this.occupaziones = occupaziones;
        return this;
    }

    public Esperienza addOccupazione(Occupazione occupazione) {
        this.occupaziones.add(occupazione);
        occupazione.getEsperienzas().add(this);
        return this;
    }

    public Esperienza removeOccupazione(Occupazione occupazione) {
        this.occupaziones.remove(occupazione);
        occupazione.getEsperienzas().remove(this);
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
        if (!(o instanceof Esperienza)) {
            return false;
        }
        return id != null && id.equals(((Esperienza) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Esperienza{" +
            "id=" + getId() +
            ", attivita='" + getAttivita() + "'" +
            ", dal='" + getDal() + "'" +
            ", al='" + getAl() + "'" +
            ", datoreLavoro='" + getDatoreLavoro() + "'" +
            ", sedeLavoro='" + getSedeLavoro() + "'" +
            "}";
    }
}
