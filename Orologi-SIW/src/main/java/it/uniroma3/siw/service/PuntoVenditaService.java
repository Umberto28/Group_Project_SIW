package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.PuntoVendita;
import it.uniroma3.siw.repository.PuntoVenditaRepository;

public class PuntoVenditaService {
	
	@Autowired
	PuntoVenditaRepository puntoVenditaRepository;
	

	public boolean alreadyExists(PuntoVendita pv) {
		return this.findAllPuntiVendita().contains(pv);
	}
	
	@Transactional
	public PuntoVendita inserisci(PuntoVendita pv) {
		return this.puntoVenditaRepository.save(pv);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.puntoVenditaRepository.deleteById(id);
	}
	
	@Transactional
	public void clear() {
		this.puntoVenditaRepository.deleteAll();
	}
	
	public PuntoVendita searchById(Long id) {
		return this.puntoVenditaRepository.findById(id).get();
	}
	
	public List<PuntoVendita> findAllPuntiVendita(){
		List<PuntoVendita> elencoPuntiVendita = new ArrayList<PuntoVendita>();
		for (PuntoVendita pv : this.puntoVenditaRepository.findAll()) {
			elencoPuntiVendita.add(pv);
		}
		return elencoPuntiVendita;
	}

}
