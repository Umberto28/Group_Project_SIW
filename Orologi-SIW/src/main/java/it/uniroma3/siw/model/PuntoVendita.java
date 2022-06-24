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
public class PuntoVendita {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	
	private String nome;
	@NotBlank
	private String indirizzo;
	
	private String nomeTitolare;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy ="puntoVenditaOrologi")
	private List<Orologio> orologiInVendita;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy ="puntoVenditaCustodie") 
	private List<Custodia> custodieInVendita;
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy ="puntoVenditaCinturini") 
	private List<Cinturino> cinturiniInVendita;
	
	
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
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getNomeTitolare() {
		return nomeTitolare;
	}
	public void setNomeTitolare(String nomeTitolare) {
		this.nomeTitolare = nomeTitolare;
	}
	public List<Orologio> getOrologiInVendita() {
		return orologiInVendita;
	}
	public void setOrologiInVendita(List<Orologio> orologiInVendita) {
		this.orologiInVendita = orologiInVendita;
	}
	public List<Custodia> getCustodieInVendita() {
		return custodieInVendita;
	}
	public void setCustodieInVendita(List<Custodia> custodieInVendita) {
		this.custodieInVendita = custodieInVendita;
	}
	public List<Cinturino> getCinturiniInVendita() {
		return cinturiniInVendita;
	}
	public void setCinturiniInVendita(List<Cinturino> cinturiniInVendita) {
		this.cinturiniInVendita = cinturiniInVendita;
	}
	
	
	
	
	

}
