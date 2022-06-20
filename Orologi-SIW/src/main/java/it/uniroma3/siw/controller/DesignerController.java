package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.controller.validator.DesignerValidator;
import it.uniroma3.siw.model.Designer;
import it.uniroma3.siw.service.DesignerService;

@Controller
public class DesignerController {
	

	@Autowired
	DesignerService designerService;
	@Autowired
	DesignerValidator designerValidator;
	
	@GetMapping("/elencoDesigner")
	private String getAllDesigner(Model model) {
		List<Designer> elencoDesigner = this.designerService.findAllDesigner();
		model.addAttribute("elencoDesigner", elencoDesigner);
		return "elencoDesigner.html";
	}
	
	@GetMapping("/admin/designerForm")
	private String getDesignerForm(Model model) {
		model.addAttribute("designer", new Designer());
		return "designerForm.html";
	}

}
