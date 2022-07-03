package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Custodia;
import it.uniroma3.siw.repository.CustodiaRepository;

@Service
public class CustodiaService {
	
	@Autowired
	CustodiaRepository custodiaRepository;
	

	public boolean alreadyExists(Custodia c) {
		//return this.findAllCustodie().contains(c);
		if(this.custodiaRepository.findByNome(c.getNome()) != null) {
			if(this.custodiaRepository.findByNome(c.getNome()).getId() != c.getId()) {
				return true;
			}
		}
		return false;
	}
	
	@Transactional
	public Custodia inserisci(Custodia custodia) {
		return this.custodiaRepository.save(custodia);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.custodiaRepository.deleteById(id);
	}
	
	@Transactional
	public void clear() {
		this.custodiaRepository.deleteAll();
	}
	
	public Custodia searchById(Long id) {
		return this.custodiaRepository.findById(id).get();
	}
	
	public List<Custodia> findAllCustodie(){
		List<Custodia> elencoCustodie = new ArrayList<Custodia>();
		for (Custodia c : this.custodiaRepository.findAll()) {
			elencoCustodie.add(c);
		}
		return elencoCustodie;
	}

}
