package it.dlvsystem.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Istruzione.
 */
@Entity
@Table(name = "istruzione")
public class Istruzione implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[0-9]+")
    @Column(name = "codice")
    private String codice;

    @Column(name = "codice_isced")
    private String codiceIsced;

    @Column(name = "codice_livello")
    private String codiceLivello;

    @Column(name = "nome")
    private String nome;

    @Column(name = "campo_studio")
    private String campoStudio;

    @Column(name = "sinonimi")
    private String sinonimi;

    @Column(name = "codice_istituto")
    private String codiceIstituto;

    @Column(name = "tipo_istituto")
    private String tipoIstituto;

    @OneToMany(mappedBy = "istruzione")
    private Set<Offerta> offertas = new HashSet<>();

    @OneToMany(mappedBy = "istruzione")
    private Set<CvIstruzione> cvIstruziones = new HashSet<>();

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

    public Istruzione codice(String codice) {
        this.codice = codice;
        return this;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getCodiceIsced() {
        return codiceIsced;
    }

    public Istruzione codiceIsced(String codiceIsced) {
        this.codiceIsced = codiceIsced;
        return this;
    }

    public void setCodiceIsced(String codiceIsced) {
        this.codiceIsced = codiceIsced;
    }

    public String getCodiceLivello() {
        return codiceLivello;
    }

    public Istruzione codiceLivello(String codiceLivello) {
        this.codiceLivello = codiceLivello;
        return this;
    }

    public void setCodiceLivello(String codiceLivello) {
        this.codiceLivello = codiceLivello;
    }

    public String getNome() {
        return nome;
    }

    public Istruzione nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCampoStudio() {
        return campoStudio;
    }

    public Istruzione campoStudio(String campoStudio) {
        this.campoStudio = campoStudio;
        return this;
    }

    public void setCampoStudio(String campoStudio) {
        this.campoStudio = campoStudio;
    }

    public String getSinonimi() {
        return sinonimi;
    }

    public Istruzione sinonimi(String sinonimi) {
        this.sinonimi = sinonimi;
        return this;
    }

    public void setSinonimi(String sinonimi) {
        this.sinonimi = sinonimi;
    }

    public String getCodiceIstituto() {
        return codiceIstituto;
    }

    public Istruzione codiceIstituto(String codiceIstituto) {
        this.codiceIstituto = codiceIstituto;
        return this;
    }

    public void setCodiceIstituto(String codiceIstituto) {
        this.codiceIstituto = codiceIstituto;
    }

    public String getTipoIstituto() {
        return tipoIstituto;
    }

    public Istruzione tipoIstituto(String tipoIstituto) {
        this.tipoIstituto = tipoIstituto;
        return this;
    }

    public void setTipoIstituto(String tipoIstituto) {
        this.tipoIstituto = tipoIstituto;
    }

    public Set<Offerta> getOffertas() {
        return offertas;
    }

    public Istruzione offertas(Set<Offerta> offertas) {
        this.offertas = offertas;
        return this;
    }

    public Istruzione addOfferta(Offerta offerta) {
        this.offertas.add(offerta);
        offerta.setIstruzione(this);
        return this;
    }

    public Istruzione removeOfferta(Offerta offerta) {
        this.offertas.remove(offerta);
        offerta.setIstruzione(null);
        return this;
    }

    public void setOffertas(Set<Offerta> offertas) {
        this.offertas = offertas;
    }

    public Set<CvIstruzione> getCvIstruziones() {
        return cvIstruziones;
    }

    public Istruzione cvIstruziones(Set<CvIstruzione> cvIstruziones) {
        this.cvIstruziones = cvIstruziones;
        return this;
    }

    public Istruzione addCvIstruzione(CvIstruzione cvIstruzione) {
        this.cvIstruziones.add(cvIstruzione);
        cvIstruzione.setIstruzione(this);
        return this;
    }

    public Istruzione removeCvIstruzione(CvIstruzione cvIstruzione) {
        this.cvIstruziones.remove(cvIstruzione);
        cvIstruzione.setIstruzione(null);
        return this;
    }

    public void setCvIstruziones(Set<CvIstruzione> cvIstruziones) {
        this.cvIstruziones = cvIstruziones;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Istruzione)) {
            return false;
        }
        return id != null && id.equals(((Istruzione) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Istruzione{" +
            "id=" + getId() +
            ", codice='" + getCodice() + "'" +
            ", codiceIsced='" + getCodiceIsced() + "'" +
            ", codiceLivello='" + getCodiceLivello() + "'" +
            ", nome='" + getNome() + "'" +
            ", campoStudio='" + getCampoStudio() + "'" +
            ", sinonimi='" + getSinonimi() + "'" +
            ", codiceIstituto='" + getCodiceIstituto() + "'" +
            ", tipoIstituto='" + getTipoIstituto() + "'" +
            "}";
    }
}
