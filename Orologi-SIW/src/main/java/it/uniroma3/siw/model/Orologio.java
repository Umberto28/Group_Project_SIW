package it.uniroma3.siw.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Orologio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotBlank
	private String nome;
	@NotBlank
	private int prezzo;
	@NotBlank
	private String descrizione;
	
	
	@ManyToOne
	private PuntoVendita puntoVenditaOrologi;
	
	@ManyToOne
	private Designer designer;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy ="orologioConCinturino")
	private Cinturino cinturino;
	
	
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
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public PuntoVendita getPuntoVenditaOrologi() {
		return puntoVenditaOrologi;
	}
	public void setPuntoVenditaOrologi(PuntoVendita puntoVenditaOrologi) {
		this.puntoVenditaOrologi = puntoVenditaOrologi;
	}
	public Designer getDesigner() {
		return designer;
	}
	public void setDesigner(Designer designer) {
		this.designer = designer;
	}
	public Cinturino getCinturino() {
		return cinturino;
	}
	public void setCinturino(Cinturino cinturino) {
		this.cinturino = cinturino;
	}
	
	
	
}
