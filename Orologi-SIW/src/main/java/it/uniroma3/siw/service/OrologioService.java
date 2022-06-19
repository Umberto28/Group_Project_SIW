package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Orologio;
import it.uniroma3.siw.repository.OrologioRepository;

@Service
public class OrologioService {
	
	@Autowired
	private OrologioRepository orologioRepository;
	
	public boolean alreadyExists(Orologio o) {
		return this.findAllOrologi().contains(o);
	}
	
	@Transactional
	public Orologio inserisci(Orologio orologio) {
		return this.orologioRepository.save(orologio);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.orologioRepository.deleteById(id);
	}
	
	@Transactional
	public void clear() {
		this.orologioRepository.deleteAll();
	}
	
	public Orologio searchById(Long id) {
		return this.orologioRepository.findById(id).get();
	}
	
	public List<Orologio> findAllOrologi(){
		List<Orologio> elencoOrologio = new ArrayList<Orologio>();
		for (Orologio o : this.orologioRepository.findAll()) {
			elencoOrologio.add(o);
		}
		return elencoOrologio;
	}

}
