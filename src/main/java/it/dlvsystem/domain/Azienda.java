package it.dlvsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Azienda.
 */
@Entity
@Table(name = "azienda")
public class Azienda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cf")
    private String cf;

    @Column(name = "ragione_sociale")
    private String ragioneSociale;

    @Column(name = "indirizzo_sede")
    private String indirizzoSede;

    @Column(name = "provincia_sede")
    private String provinciaSede;

    @Column(name = "ragione_sede")
    private String ragioneSede;

    @Pattern(regexp = "[0-9]+")
    @Column(name = "cap_sede")
    private String capSede;

    @OneToMany(mappedBy = "azienda")
    private Set<Offerta> offertas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "aziendas", allowSetters = true)
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

    public Azienda cf(String cf) {
        this.cf = cf;
        return this;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public Azienda ragioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
        return this;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getIndirizzoSede() {
        return indirizzoSede;
    }

    public Azienda indirizzoSede(String indirizzoSede) {
        this.indirizzoSede = indirizzoSede;
        return this;
    }

    public void setIndirizzoSede(String indirizzoSede) {
        this.indirizzoSede = indirizzoSede;
    }

    public String getProvinciaSede() {
        return provinciaSede;
    }

    public Azienda provinciaSede(String provinciaSede) {
        this.provinciaSede = provinciaSede;
        return this;
    }

    public void setProvinciaSede(String provinciaSede) {
        this.provinciaSede = provinciaSede;
    }

    public String getRagioneSede() {
        return ragioneSede;
    }

    public Azienda ragioneSede(String ragioneSede) {
        this.ragioneSede = ragioneSede;
        return this;
    }

    public void setRagioneSede(String ragioneSede) {
        this.ragioneSede = ragioneSede;
    }

    public String getCapSede() {
        return capSede;
    }

    public Azienda capSede(String capSede) {
        this.capSede = capSede;
        return this;
    }

    public void setCapSede(String capSede) {
        this.capSede = capSede;
    }

    public Set<Offerta> getOffertas() {
        return offertas;
    }

    public Azienda offertas(Set<Offerta> offertas) {
        this.offertas = offertas;
        return this;
    }

    public Azienda addOfferta(Offerta offerta) {
        this.offertas.add(offerta);
        offerta.setAzienda(this);
        return this;
    }

    public Azienda removeOfferta(Offerta offerta) {
        this.offertas.remove(offerta);
        offerta.setAzienda(null);
        return this;
    }

    public void setOffertas(Set<Offerta> offertas) {
        this.offertas = offertas;
    }

    public Login getLogin() {
        return login;
    }

    public Azienda login(Login login) {
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
        if (!(o instanceof Azienda)) {
            return false;
        }
        return id != null && id.equals(((Azienda) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Azienda{" +
            "id=" + getId() +
            ", cf='" + getCf() + "'" +
            ", ragioneSociale='" + getRagioneSociale() + "'" +
            ", indirizzoSede='" + getIndirizzoSede() + "'" +
            ", provinciaSede='" + getProvinciaSede() + "'" +
            ", ragioneSede='" + getRagioneSede() + "'" +
            ", capSede='" + getCapSede() + "'" +
            "}";
    }
}
