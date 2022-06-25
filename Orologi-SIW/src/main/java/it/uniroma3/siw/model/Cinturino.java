package it.uniroma3.siw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Cinturino {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotBlank
	private String colore;
	
	private int misura;
	
	private int prezzo;
	
	@ManyToOne
	private Orologio orologioConCinturino;
	
	@ManyToOne
	private PuntoVendita puntoVenditaCinturini;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public int getMisura() {
		return misura;
	}
	public void setMisura(int misura) {
		this.misura = misura;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	public Orologio getOrologio() {
		return orologioConCinturino;
	}
	public void setOrologio(Orologio orologio) {
		this.orologioConCinturino = orologio;
	}
	public PuntoVendita getPuntoVenditaCinturini() {
		return puntoVenditaCinturini;
	}
	public void setPuntoVenditaCinturini(PuntoVendita puntoVenditaCinturini) {
		this.puntoVenditaCinturini = puntoVenditaCinturini;
	}
	
	
	
	

}
