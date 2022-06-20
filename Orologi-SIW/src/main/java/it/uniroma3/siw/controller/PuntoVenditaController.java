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

import it.uniroma3.siw.controller.validator.PuntoVenditaValidator;
import it.uniroma3.siw.model.PuntoVendita;
import it.uniroma3.siw.service.PuntoVenditaService;

@Controller
public class PuntoVenditaController {
	
	@Autowired
	PuntoVenditaService puntoVenditaService;
	@Autowired
	PuntoVenditaValidator puntoVenditaValidator;
	
	@PostMapping("/admin/puntoVendita")
	public String addDesigner(@Valid @ModelAttribute("puntoVendita") PuntoVendita pv, 
			BindingResult bindingResult, Model model) {
		
		this.puntoVenditaValidator.validate(pv, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			
			model.addAttribute("elencoOrologiInVendita", pv.getOrologiInVendita());
			model.addAttribute("elencoCinturiniInVendita", pv.getCinturiniInVendita());
			model.addAttribute("elencoCustodieInVendita", pv.getCustodieInVendita());
			model.addAttribute("puntoVendita", pv);
			return "puntoVendita.html";

		} 
		model.addAttribute("puntoVendita", pv);
		return "puntoVenditaForm.html";
		
	}
	
	@GetMapping("/elencoStore")
	private String getAllOrologi(Model model) {
		List<PuntoVendita> elencoPuntivendita = this.puntoVenditaService.findAllPuntiVendita();
		model.addAttribute("elencoPuntivendita", elencoPuntivendita);
		return "elencoPuntiVendita.html";
	}
	
	@GetMapping("/admin/puntoVenditaForm")
	private String getPuntoVenditaForm(Model model) {
		model.addAttribute("puntoVendita", new PuntoVendita());
		return "puntoVendita.html";
	}
	
	@GetMapping("/puntoVendita/{id}")
	private String getPuntoVendita(@PathVariable("id") Long id, Model model) {
		PuntoVendita puntoVendita =this.puntoVenditaService.searchById(id);
		model.addAttribute("puntoVendita", puntoVendita);
		model.addAttribute("elencoCinturiniInVendita", puntoVendita.getCinturiniInVendita());
		model.addAttribute("elencoOrologiInVendita", puntoVendita.getOrologiInVendita());
		model.addAttribute("elencoCustodieInVendita", puntoVendita.getCustodieInVendita());
		return "orologio.html";
	}

}
