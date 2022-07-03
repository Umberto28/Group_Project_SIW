package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Custodia;

public interface CustodiaRepository extends CrudRepository<Custodia, Long> {

	public Custodia findByNome(String nome);
}
