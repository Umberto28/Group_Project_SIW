package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Custodia {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	private String nome;
	@NotBlank
	private String materiale;
	
	@Min(0)
	@Max(12)
	private int numeroOrologi;
	
	@NotBlank
	private String colore;
	
	@Min(0)
	@Max(999)
	private float prezzo;
	
	@ManyToOne
	public PuntoVendita puntoVenditaCustodie;
	
	@OneToMany
	private List<Orologio> orologiAdattiAllaCustodia;

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
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public List<Orologio> getOrologiAdattiAllaCustodia() {
		return orologiAdattiAllaCustodia;
	}
	public void setOrologiAdattiAllaCustodia(List<Orologio> orologiAdattiAllaCustodia) {
		this.orologiAdattiAllaCustodia = orologiAdattiAllaCustodia;
	}
	
	
	
	
}
