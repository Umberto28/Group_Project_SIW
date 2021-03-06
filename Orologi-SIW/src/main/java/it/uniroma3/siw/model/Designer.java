package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Designer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotBlank
	private String nome;
	@NotBlank
	private String cognome;
	@NotBlank
	private String nazionalita;
	
	@OneToMany(mappedBy ="designer", cascade = {CascadeType.REMOVE})
	private List<Orologio> orologiCreati;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	public List<Orologio> getOrologiCreati() {
		return orologiCreati;
	}
	public void setOrologiCreati(List<Orologio> orologiCreati) {
		this.orologiCreati = orologiCreati;
	}
	
	
	
	
	

}
