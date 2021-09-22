package it.dlvsystem.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A GapAnalysis.
 */
@Entity
@Table(name = "gap_analysis")
public class GapAnalysis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codice_esco")
    private String codiceEsco;

    @Column(name = "nome")
    private String nome;

    @Column(name = "frequenza")
    private Integer frequenza;

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

    public GapAnalysis codiceEsco(String codiceEsco) {
        this.codiceEsco = codiceEsco;
        return this;
    }

    public void setCodiceEsco(String codiceEsco) {
        this.codiceEsco = codiceEsco;
    }

    public String getNome() {
        return nome;
    }

    public GapAnalysis nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getFrequenza() {
        return frequenza;
    }

    public GapAnalysis frequenza(Integer frequenza) {
        this.frequenza = frequenza;
        return this;
    }

    public void setFrequenza(Integer frequenza) {
        this.frequenza = frequenza;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GapAnalysis)) {
            return false;
        }
        return id != null && id.equals(((GapAnalysis) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GapAnalysis{" +
            "id=" + getId() +
            ", codiceEsco='" + getCodiceEsco() + "'" +
            ", nome='" + getNome() + "'" +
            ", frequenza=" + getFrequenza() +
            "}";
    }
}
