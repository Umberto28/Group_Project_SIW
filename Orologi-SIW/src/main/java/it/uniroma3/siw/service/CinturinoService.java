package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Cinturino;

import it.uniroma3.siw.repository.CinturinoRepository;

public class CinturinoService {
	
	@Autowired
	CinturinoRepository cinturinoRepository;
	

	public boolean alreadyExists(Cinturino c) {
		return this.findAllCinturini().contains(c);
	}
	
	@Transactional
	public Cinturino inserisci(Cinturino cint) {
		return this.cinturinoRepository.save(cint);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.cinturinoRepository.deleteById(id);
	}
	
	@Transactional
	public void clear() {
		this.cinturinoRepository.deleteAll();
	}
	
	public Cinturino searchById(Long id) {
		return this.cinturinoRepository.findById(id).get();
	}
	
	public List<Cinturino> findAllCinturini(){
		List<Cinturino> elencoCinturini = new ArrayList<Cinturino>();
		for (Cinturino c : this.cinturinoRepository.findAll()) {
			elencoCinturini.add(c);
		}
		return elencoCinturini;
	}

}
