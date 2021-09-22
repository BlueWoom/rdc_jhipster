package it.dlvsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A OffertaOccupazioneRichiesta.
 */
@Entity
@Table(name = "offerta_occupazione_richiesta")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OffertaOccupazioneRichiesta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[0-9]+")
    @Column(name = "codice_offerta")
    private String codiceOfferta;

    @Column(name = "codice_esco_occupazione")
    private String codiceEscoOccupazione;

    @Column(name = "anni")
    private Integer anni;

    @ManyToOne
    @JsonIgnoreProperties(value = "offertaOccupazioneRichiestas", allowSetters = true)
    private Offerta offerta;

    @ManyToOne
    @JsonIgnoreProperties(value = "offertaOccupazioneRichiestas", allowSetters = true)
    private Occupazione occupazione;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiceOfferta() {
        return codiceOfferta;
    }

    public OffertaOccupazioneRichiesta codiceOfferta(String codiceOfferta) {
        this.codiceOfferta = codiceOfferta;
        return this;
    }

    public void setCodiceOfferta(String codiceOfferta) {
        this.codiceOfferta = codiceOfferta;
    }

    public String getCodiceEscoOccupazione() {
        return codiceEscoOccupazione;
    }

    public OffertaOccupazioneRichiesta codiceEscoOccupazione(String codiceEscoOccupazione) {
        this.codiceEscoOccupazione = codiceEscoOccupazione;
        return this;
    }

    public void setCodiceEscoOccupazione(String codiceEscoOccupazione) {
        this.codiceEscoOccupazione = codiceEscoOccupazione;
    }

    public Integer getAnni() {
        return anni;
    }

    public OffertaOccupazioneRichiesta anni(Integer anni) {
        this.anni = anni;
        return this;
    }

    public void setAnni(Integer anni) {
        this.anni = anni;
    }

    public Offerta getOfferta() {
        return offerta;
    }

    public OffertaOccupazioneRichiesta offerta(Offerta offerta) {
        this.offerta = offerta;
        return this;
    }

    public void setOfferta(Offerta offerta) {
        this.offerta = offerta;
    }

    public Occupazione getOccupazione() {
        return occupazione;
    }

    public OffertaOccupazioneRichiesta occupazione(Occupazione occupazione) {
        this.occupazione = occupazione;
        return this;
    }

    public void setOccupazione(Occupazione occupazione) {
        this.occupazione = occupazione;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OffertaOccupazioneRichiesta)) {
            return false;
        }
        return id != null && id.equals(((OffertaOccupazioneRichiesta) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OffertaOccupazioneRichiesta{" +
            "id=" + getId() +
            ", codiceOfferta='" + getCodiceOfferta() + "'" +
            ", codiceEscoOccupazione='" + getCodiceEscoOccupazione() + "'" +
            ", anni=" + getAnni() +
            "}";
    }
}
