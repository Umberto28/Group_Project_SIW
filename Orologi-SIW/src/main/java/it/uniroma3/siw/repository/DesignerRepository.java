package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Designer;

public interface DesignerRepository extends CrudRepository<Designer, Long> {

	public Designer findByNomeAndCognome(String nome, String cognome);
}
