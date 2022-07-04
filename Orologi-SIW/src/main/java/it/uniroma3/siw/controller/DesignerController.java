package it.uniroma3.siw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.validator.DesignerValidator;
import it.uniroma3.siw.model.Designer;
import it.uniroma3.siw.service.DesignerService;

@Controller
public class DesignerController {

	@Autowired
	DesignerService designerService;
	@Autowired
	DesignerValidator designerValidator;

	@PostMapping("/admin/designer")
	public String addDesigner(@Valid @ModelAttribute("designer") Designer d,
			BindingResult bindingResult, Model model) {

		this.designerValidator.validate(d, bindingResult);

		if (!bindingResult.hasErrors()) {
			this.designerService.inserisci(d);

			model.addAttribute("elencoDesigner", this.designerService.findAllDesigner());
			return "/Designer/elencoDesigner.html";

		}
		model.addAttribute("designer", d);
		return "/Designer/designerForm.html";

	}

	@GetMapping("/elencoDesigner")
	private String getAllDesigner(Model model) {
		List<Designer> elencoDesigner = this.designerService.findAllDesigner();
		model.addAttribute("elencoDesigner", elencoDesigner);
		return "/Designer/elencoDesigner.html";
	}

	@GetMapping("/admin/designerForm")
	private String getDesignerForm(Model model) {
		model.addAttribute("designer", new Designer());
		return "/Designer/designerForm.html";
	}

	@GetMapping("/designer/{id}")
	private String getDesigner(@PathVariable("id") Long id, Model model) {
		Designer designer = this.designerService.searchById(id);
		model.addAttribute("designer", designer);
		model.addAttribute("elencoOrologiCreati", designer.getOrologiCreati());
		return "/Designer/designer.html";
	}

	@GetMapping("/deleteDesigner")
	private String deleteDesigner(@RequestParam Long designerId) {
		this.designerService.rimuovi(designerId);
		return "redirect:/elencoDesigner";
	}

	@GetMapping("/admin/updateDesigner")
	private String updateDesignerForm(@RequestParam Long designerId, Model model) {
		model.addAttribute("designer", this.designerService.searchById(designerId));
		return "/Designer/designerUpdateForm.html";
	}

	@GetMapping("/admin/designerUpdate/{id}")
	private String updateDesigner(@Valid @ModelAttribute("designer") Designer d, BindingResult bindingResult,
			Model model) {
		this.designerValidator.validate(d, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.designerService.inserisci(d);
			model.addAttribute("designer", d);
			model.addAttribute("elencoOrologiCreati", d.getOrologiCreati());
			return "/Designer/designer.html";
		}

		model.addAttribute("designer", d);
		return "/Designer/designerUpdateForm.html";
	}

}
