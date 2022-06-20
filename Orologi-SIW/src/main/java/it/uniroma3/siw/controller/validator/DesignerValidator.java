package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Designer;
import it.uniroma3.siw.service.DesignerService;

@Controller
public class DesignerValidator implements Validator {
	
	@Autowired
	DesignerService designerService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.designerService.alreadyExists((Designer) o)) {
			errors.reject("designer.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Designer.class.equals(aClass);
	}

}
