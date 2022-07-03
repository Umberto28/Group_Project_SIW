package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Custodia;
import it.uniroma3.siw.service.CustodiaService;

@Controller
public class CustodiaValidator implements Validator{
	
	@Autowired
	CustodiaService custodiaService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.custodiaService.alreadyExists((Custodia) o)) {
			errors.reject("custodia.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Custodia.class.equals(aClass);
	}

	public void valPV(Long id, Errors errors) {
		if(id < 0) {
			errors.reject("NotNull.cinturino.puntoVenditaCinturini");
		}
	}
}
