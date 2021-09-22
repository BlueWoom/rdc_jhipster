package it.dlvsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Cv.
 */
@Entity
@Table(name = "cv")
public class Cv implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cf_utente")
	private String cfUtente;

	@Pattern(regexp = "[0-9]+")
	@Column(name = "codice")
	private String codice;

	@Column(name = "inserimento")
	private LocalDate inserimento;

	@OneToMany(mappedBy = "cv")
	private Set<CvIstruzione> cvIstruziones = new HashSet<>();

	@OneToMany(mappedBy = "cv")
	private Set<Esperienza> esperienzas = new HashSet<>();

	@ManyToOne
	@JsonIgnoreProperties(value = "cvs", allowSetters = true)
	private Candidato candidato;

	// jhipster-needle-entity-add-field - JHipster will add fields here
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCfUtente() {
		return cfUtente;
	}

	public Cv cfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
		return this;
	}

	public void setCfUtente(String cfUtente) {
		this.cfUtente = cfUtente;
	}

	public String getCodice() {
		return codice;
	}

	public Cv codice(String codice) {
		this.codice = codice;
		return this;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public LocalDate getInserimento() {
		return inserimento;
	}

	public Cv inserimento(LocalDate inserimento) {
		this.inserimento = inserimento;
		return this;
	}

	public void setInserimento(LocalDate inserimento) {
		this.inserimento = inserimento;
	}

	public Set<CvIstruzione> getCvIstruziones() {
		return cvIstruziones;
	}

	public Cv cvIstruziones(Set<CvIstruzione> cvIstruziones) {
		this.cvIstruziones = cvIstruziones;
		return this;
	}

	public Cv addCvIstruzione(CvIstruzione cvIstruzione) {
		this.cvIstruziones.add(cvIstruzione);
		cvIstruzione.setCv(this);
		return this;
	}

	public Cv removeCvIstruzione(CvIstruzione cvIstruzione) {
		this.cvIstruziones.remove(cvIstruzione);
		cvIstruzione.setCv(null);
		return this;
	}

	public void setCvIstruziones(Set<CvIstruzione> cvIstruziones) {
		this.cvIstruziones = cvIstruziones;
	}

	public Set<Esperienza> getEsperienzas() {
		return esperienzas;
	}

	public Cv esperienzas(Set<Esperienza> esperienzas) {
		this.esperienzas = esperienzas;
		return this;
	}

	public Cv addEsperienza(Esperienza esperienza) {
		this.esperienzas.add(esperienza);
		esperienza.setCv(this);
		return this;
	}

	public Cv removeEsperienza(Esperienza esperienza) {
		this.esperienzas.remove(esperienza);
		esperienza.setCv(null);
		return this;
	}

	public void setEsperienzas(Set<Esperienza> esperienzas) {
		this.esperienzas = esperienzas;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public Cv candidato(Candidato candidato) {
		this.candidato = candidato;
		return this;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Cv)) {
			return false;
		}
		return id != null && id.equals(((Cv) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	// prettier-ignore
	@Override
	public String toString() {
		return "Cv{" + "id=" + getId() + ", cfUtente='" + getCfUtente() + "'" + ", codice='" + getCodice() + "'"
				+ ", inserimento='" + getInserimento() + "'" + "}";
	}
}
