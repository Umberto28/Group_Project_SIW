package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.controller.validator.OrologioValidator;
import it.uniroma3.siw.model.Orologio;
import it.uniroma3.siw.service.DesignerService;
import it.uniroma3.siw.service.OrologioService;
import it.uniroma3.siw.service.PuntoVenditaService;

@Controller
public class OrologioController {
	
	@Autowired
	OrologioService orologioService;
	@Autowired
	OrologioValidator orologioValidator;
	@Autowired
	DesignerService designerService;
	@Autowired
	PuntoVenditaService puntoVenditaService;
	
	@GetMapping("/elencoOrologi")
	private String getAllOrologi(Model model) {
		List<Orologio> elencoOrologi = this.orologioService.findAllOrologi();
		model.addAttribute("elencoOrologi", elencoOrologi);
		return "elencoOrologi.html";
	}
	
	@GetMapping("/admin/orologioForm")
	private String getOrologioForm(Model model) {
		model.addAttribute("orologio", new Orologio());
		model.addAttribute("designerDisponibili",this.designerService.findAllDesigner());
		model.addAttribute("puntiVenditaDisponibili",this.puntoVenditaService.findAllPuntiVendita());
		return "orologioForm.html";
	}

}
