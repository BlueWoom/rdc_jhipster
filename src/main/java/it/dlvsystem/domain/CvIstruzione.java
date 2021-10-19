package it.dlvsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CvIstruzione.
 */
@Entity
@Table(name = "cv_istruzione")
public class CvIstruzione implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "punteggio")
    private Integer punteggio;

    @ManyToOne
    @JsonIgnoreProperties(value = "cvIstruziones", allowSetters = true)
    private Istruzione istruzione;

    @ManyToOne
    @JsonIgnoreProperties(value = "cvIstruziones", allowSetters = true)
    private Cv cv;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPunteggio() {
        return punteggio;
    }

    public CvIstruzione punteggio(Integer punteggio) {
        this.punteggio = punteggio;
        return this;
    }

    public void setPunteggio(Integer punteggio) {
        this.punteggio = punteggio;
    }

    public Istruzione getIstruzione() {
        return istruzione;
    }

    public CvIstruzione istruzione(Istruzione istruzione) {
        this.istruzione = istruzione;
        return this;
    }

    public void setIstruzione(Istruzione istruzione) {
        this.istruzione = istruzione;
    }

    public Cv getCv() {
        return cv;
    }

    public CvIstruzione cv(Cv cv) {
        this.cv = cv;
        return this;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CvIstruzione)) {
            return false;
        }
        return id != null && id.equals(((CvIstruzione) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CvIstruzione{" +
            "id=" + getId() +
            ", punteggio=" + getPunteggio() +
            "}";
    }
}
