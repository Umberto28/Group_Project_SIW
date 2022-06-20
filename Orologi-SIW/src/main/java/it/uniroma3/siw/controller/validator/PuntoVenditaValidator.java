package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.PuntoVendita;
import it.uniroma3.siw.service.PuntoVenditaService;

@Controller
public class PuntoVenditaValidator implements Validator {
	
	@Autowired
	PuntoVenditaService puntoVenditaService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.puntoVenditaService.alreadyExists((PuntoVendita) o)) {
			errors.reject("puntoVendita.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return PuntoVendita.class.equals(aClass);
	}

}
