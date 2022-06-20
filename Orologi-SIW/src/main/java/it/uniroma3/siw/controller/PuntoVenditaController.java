package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.controller.validator.PuntoVenditaValidator;
import it.uniroma3.siw.model.PuntoVendita;
import it.uniroma3.siw.service.PuntoVenditaService;

@Controller
public class PuntoVenditaController {
	
	@Autowired
	PuntoVenditaService puntoVenditaService;
	@Autowired
	PuntoVenditaValidator puntoVenditaValidator;
	
	@GetMapping("/elencoStore")
	private String getAllOrologi(Model model) {
		List<PuntoVendita> elencoPuntivendita = this.puntoVenditaService.findAllPuntiVendita();
		model.addAttribute("elencoPuntivendita", elencoPuntivendita);
		return "elencoPuntivendita.html";
	}
	
	@GetMapping("/admin/puntoVenditaForm")
	private String getPuntoVenditaForm(Model model) {
		model.addAttribute("puntoVendita", new PuntoVendita());
		return "puntoVendita.html";
	}

}
