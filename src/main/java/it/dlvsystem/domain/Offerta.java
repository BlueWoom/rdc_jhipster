package it.dlvsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Offerta.
 */
@Entity
@Table(name = "offerta")
public class Offerta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[0-9]+")
    @Column(name = "codice")
    private String codice;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "indirizzo_sede")
    private String indirizzoSede;

    @Column(name = "citta_sede")
    private String cittaSede;

    @Pattern(regexp = "[0-9]+")
    @Column(name = "cap_sede")
    private String capSede;

    @Column(name = "provincia_sede")
    private String provinciaSede;

    @OneToMany(mappedBy = "offerta")
    private Set<OffertaSkill> offertaSkills = new HashSet<>();

    @OneToMany(mappedBy = "offerta")
    private Set<OffertaOccupazioneRichiesta> offertaOccupazioneRichiestas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "offertas", allowSetters = true)
    private Istruzione istruzione;

    @ManyToOne
    @JsonIgnoreProperties(value = "offertas", allowSetters = true)
    private Azienda azienda;

    @ManyToMany(mappedBy = "offertas")
    @JsonIgnore
    private Set<Occupazione> occupaziones = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public Offerta codice(String codice) {
        this.codice = codice;
        return this;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public LocalDate getData() {
        return data;
    }

    public Offerta data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getIndirizzoSede() {
        return indirizzoSede;
    }

    public Offerta indirizzoSede(String indirizzoSede) {
        this.indirizzoSede = indirizzoSede;
        return this;
    }

    public void setIndirizzoSede(String indirizzoSede) {
        this.indirizzoSede = indirizzoSede;
    }

    public String getCittaSede() {
        return cittaSede;
    }

    public Offerta cittaSede(String cittaSede) {
        this.cittaSede = cittaSede;
        return this;
    }

    public void setCittaSede(String cittaSede) {
        this.cittaSede = cittaSede;
    }

    public String getCapSede() {
        return capSede;
    }

    public Offerta capSede(String capSede) {
        this.capSede = capSede;
        return this;
    }

    public void setCapSede(String capSede) {
        this.capSede = capSede;
    }

    public String getProvinciaSede() {
        return provinciaSede;
    }

    public Offerta provinciaSede(String provinciaSede) {
        this.provinciaSede = provinciaSede;
        return this;
    }

    public void setProvinciaSede(String provinciaSede) {
        this.provinciaSede = provinciaSede;
    }

    public Set<OffertaSkill> getOffertaSkills() {
        return offertaSkills;
    }

    public Offerta offertaSkills(Set<OffertaSkill> offertaSkills) {
        this.offertaSkills = offertaSkills;
        return this;
    }

    public Offerta addOffertaSkill(OffertaSkill offertaSkill) {
        this.offertaSkills.add(offertaSkill);
        offertaSkill.setOfferta(this);
        return this;
    }

    public Offerta removeOffertaSkill(OffertaSkill offertaSkill) {
        this.offertaSkills.remove(offertaSkill);
        offertaSkill.setOfferta(null);
        return this;
    }

    public void setOffertaSkills(Set<OffertaSkill> offertaSkills) {
        this.offertaSkills = offertaSkills;
    }

    public Set<OffertaOccupazioneRichiesta> getOffertaOccupazioneRichiestas() {
        return offertaOccupazioneRichiestas;
    }

    public Offerta offertaOccupazioneRichiestas(Set<OffertaOccupazioneRichiesta> offertaOccupazioneRichiestas) {
        this.offertaOccupazioneRichiestas = offertaOccupazioneRichiestas;
        return this;
    }

    public Offerta addOffertaOccupazioneRichiesta(OffertaOccupazioneRichiesta offertaOccupazioneRichiesta) {
        this.offertaOccupazioneRichiestas.add(offertaOccupazioneRichiesta);
        offertaOccupazioneRichiesta.setOfferta(this);
        return this;
    }

    public Offerta removeOffertaOccupazioneRichiesta(OffertaOccupazioneRichiesta offertaOccupazioneRichiesta) {
        this.offertaOccupazioneRichiestas.remove(offertaOccupazioneRichiesta);
        offertaOccupazioneRichiesta.setOfferta(null);
        return this;
    }

    public void setOffertaOccupazioneRichiestas(Set<OffertaOccupazioneRichiesta> offertaOccupazioneRichiestas) {
        this.offertaOccupazioneRichiestas = offertaOccupazioneRichiestas;
    }

    public Istruzione getIstruzione() {
        return istruzione;
    }

    public Offerta istruzione(Istruzione istruzione) {
        this.istruzione = istruzione;
        return this;
    }

    public void setIstruzione(Istruzione istruzione) {
        this.istruzione = istruzione;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public Offerta azienda(Azienda azienda) {
        this.azienda = azienda;
        return this;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public Set<Occupazione> getOccupaziones() {
        return occupaziones;
    }

    public Offerta occupaziones(Set<Occupazione> occupaziones) {
        this.occupaziones = occupaziones;
        return this;
    }

    public Offerta addOccupazione(Occupazione occupazione) {
        this.occupaziones.add(occupazione);
        occupazione.getOffertas().add(this);
        return this;
    }

    public Offerta removeOccupazione(Occupazione occupazione) {
        this.occupaziones.remove(occupazione);
        occupazione.getOffertas().remove(this);
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
        if (!(o instanceof Offerta)) {
            return false;
        }
        return id != null && id.equals(((Offerta) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Offerta{" +
            "id=" + getId() +
            ", codice='" + getCodice() + "'" +
            ", data='" + getData() + "'" +
            ", indirizzoSede='" + getIndirizzoSede() + "'" +
            ", cittaSede='" + getCittaSede() + "'" +
            ", capSede='" + getCapSede() + "'" +
            ", provinciaSede='" + getProvinciaSede() + "'" +
            "}";
    }
}
