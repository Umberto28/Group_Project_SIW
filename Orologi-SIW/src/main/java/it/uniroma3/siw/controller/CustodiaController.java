package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.controller.validator.CustodiaValidator;
import it.uniroma3.siw.model.Custodia;
import it.uniroma3.siw.service.CustodiaService;
import it.uniroma3.siw.service.PuntoVenditaService;

@Controller
public class CustodiaController {
	
	@Autowired
	CustodiaService custodiaService;
	@Autowired
	CustodiaValidator custodiaValidator;
	@Autowired
	PuntoVenditaService puntoVenditaService;
	
	@GetMapping("/elencoCustodie")
	private String getAllCustodie(Model model) {
		List<Custodia> elencoCustodie = this.custodiaService.findAllCustodie();
		model.addAttribute("elencoCustodie", elencoCustodie);
		return "elencoCustodie.html";
	}
	
	@GetMapping("/admin/custodiaForm")
	private String getCustodiaForm(Model model) {
		model.addAttribute("custodia", new Custodia());
		model.addAttribute("puntiVenditaDisponibili",this.puntoVenditaService.findAllPuntiVendita());
		return "custodiaForm.html";
	}

}
