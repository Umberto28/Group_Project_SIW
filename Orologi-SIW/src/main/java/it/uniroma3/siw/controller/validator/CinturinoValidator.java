package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Cinturino;
import it.uniroma3.siw.service.CinturinoService;



@Controller
public class CinturinoValidator implements Validator {

	@Autowired
	CinturinoService cinturinoService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.cinturinoService.alreadyExists((Cinturino) o)) {
			errors.reject("cinturino.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Cinturino.class.equals(aClass);
	}

	
}
