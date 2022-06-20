package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.controller.validator.CinturinoValidator;
import it.uniroma3.siw.model.Cinturino;
import it.uniroma3.siw.service.CinturinoService;
import it.uniroma3.siw.service.OrologioService;
import it.uniroma3.siw.service.PuntoVenditaService;

@Controller
public class CinturinoController {
	
	@Autowired
	CinturinoService cinturinoService;
	@Autowired
	CinturinoValidator cinturinoValidator;
	@Autowired
	OrologioService orologioService;
	@Autowired
	PuntoVenditaService puntoVenditaService;
	
	@GetMapping("/elencoCinturini")
	private String getAllCinturini(Model model) {
		List<Cinturino> elencoCinturini = this.cinturinoService.findAllCinturini();
		model.addAttribute("elencoCinturini", elencoCinturini);
		return "elencoCinturini.html";
	}
	
	@GetMapping("/admin/cinturinoForm")
	private String getCinturinoForm(Model model) {
		model.addAttribute("cinturino", new Cinturino());
		model.addAttribute("orologiDisponibili",this.orologioService.findAllOrologi());
		model.addAttribute("puntiVenditaDisponibili",this.puntoVenditaService.findAllPuntiVendita());
		return "cinturinoForm.html";
	}
	
	
}
