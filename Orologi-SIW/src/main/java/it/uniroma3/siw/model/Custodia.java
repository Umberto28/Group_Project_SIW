package it.uniroma3.siw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Custodia {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	private String nome;
	
	private String materiale;
	
	private int numeroOrologi;
	
	private String colore;
	
	@ManyToOne
	private PuntoVendita puntoVenditaCustodie;

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
	public String getMateriale() {
		return materiale;
	}
	public void setMateriale(String materiale) {
		this.materiale = materiale;
	}
	public int getNumeroOrologi() {
		return numeroOrologi;
	}
	public void setNumeroOrologi(int numeroOrologi) {
		this.numeroOrologi = numeroOrologi;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public PuntoVendita getPuntoVenditaCustodie() {
		return puntoVenditaCustodie;
	}
	public void setPuntoVenditaCustodie(PuntoVendita puntoVendita) {
		this.puntoVenditaCustodie = puntoVendita;
	}
	
	
	
}
