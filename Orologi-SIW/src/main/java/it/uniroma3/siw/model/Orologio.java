package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.CascadeType;
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
public class Orologio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@NotBlank
	private String nome;
	
	@Min(0)
	@Max(999)
	private float prezzo;
	
	private String descrizione;
	
	@ManyToOne
	private PuntoVendita puntoVenditaOrologi;
	
	@ManyToOne
	private Designer designer;
	
	@OneToMany(cascade = {CascadeType.REMOVE}, mappedBy ="orologioConCinturino")
	private List<Cinturino> cinturiniPosseduti;	
	
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
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
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
	
	public List<Cinturino> getCinturiniPosseduti() {
		return cinturiniPosseduti;
	}
	public void setCinturiniPosseduti(List<Cinturino> cinturini) {
		this.cinturiniPosseduti = cinturini;
	}
	
	
	
	
	
	
}
