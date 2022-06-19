package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Designer;
import it.uniroma3.siw.repository.DesignerRepository;

@Service
public class DesignerService {
	
	@Autowired
	DesignerRepository designerRepository;
	

	public boolean alreadyExists(Designer d) {
		return this.findAllDesigner().contains(d);
	}
	
	@Transactional
	public Designer inserisci(Designer design) {
		return this.designerRepository.save(design);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.designerRepository.deleteById(id);
	}
	
	@Transactional
	public void clear() {
		this.designerRepository.deleteAll();
	}
	
	public Designer searchById(Long id) {
		return this.designerRepository.findById(id).get();
	}
	
	public List<Designer> findAllDesigner(){
		List<Designer> elencoDesigner = new ArrayList<Designer>();
		for (Designer d : this.designerRepository.findAll()) {
			elencoDesigner.add(d);
		}
		return elencoDesigner;
	}

}
