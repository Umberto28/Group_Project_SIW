package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Orologio;
import it.uniroma3.siw.service.OrologioService;

@Controller
public class OrologioValidator implements Validator {
	
	@Autowired
	OrologioService orologioService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.orologioService.alreadyExists((Orologio) o)) {
			errors.reject("orologio.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Orologio.class.equals(aClass);
	}

}
