package it.uniroma3.siw.controller;

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

import it.uniroma3.siw.controller.validator.CustodiaValidator;
import it.uniroma3.siw.model.Custodia;
import it.uniroma3.siw.model.PuntoVendita;
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
	
	@PostMapping("/admin/custodia")
	public String addCustodia(@Valid @ModelAttribute("custodia") Custodia c, 
			BindingResult bindingResult,
			/*@RequestParam(name = "puntoVenditaScelto") Long PVid,*/ Model model) {
		
		this.custodiaValidator.validate(c, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			
			
			/*PuntoVendita pv = this.puntoVenditaService.searchById(PVid);
			
			
			c.setPuntoVenditaCustodie(pv);
			
			
			pv.getCustodieInVendita().add(c);
			
			this.puntoVenditaService.inserisci(pv);*/
			this.custodiaService.inserisci(c);			
			
			model.addAttribute("custodia", c);
			return "/Custodia/custodia.html";

		} 
		model.addAttribute("custodia", c);
		return "/Custodia/custodiaForm.html";
		
	}
	
	@GetMapping("/admin/custodiaForm")
	private String getCustodiaForm(Model model) {
		model.addAttribute("custodia", new Custodia());
		//model.addAttribute("puntiVenditaDisponibili",this.puntoVenditaService.findAllPuntiVendita());
		return "/Custodia/custodiaForm.html";
	}
	
	@GetMapping("/custodia/{id}")
	private String getCustodia(@PathVariable("id") Long id, Model model) {
		Custodia custodia =this.custodiaService.searchById(id);
		model.addAttribute("custodia", custodia);
		return "/Custodia/custodia.html";
	}
	
	@GetMapping("/deleteCustodia")
	private String deleteCustodia(@RequestParam Long custodiaId) {
		this.custodiaService.rimuovi(custodiaId);
		return "redirect:/elencoCustodie";
	}
	
	@GetMapping("/admin/updateCustodia")
	private String updateCustodiaForm(@RequestParam Long custodiaId, Model model) {
		model.addAttribute("custodia", this.custodiaService.searchById(custodiaId));
		//potremmo voler cambiare il punto vendita della custodia
		model.addAttribute("puntiVenditaDisponibili",this.puntoVenditaService.findAllPuntiVendita());
		return "/Custodia/custodiaUpdateForm.html";
	}
	
	@GetMapping("/custodiaUpdate/{id}")
	private String updateCustodia(@Valid @ModelAttribute("custodia") Custodia c, BindingResult bindingResult, Model model) {
		this.custodiaValidator.validate(c, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.custodiaService.inserisci(c);
			model.addAttribute("custodia", c);
			return "/Custodia/custodia.html";
		}
		model.addAttribute("custodia", c);
		return "/Custodia/custodiaUpdateForm.html";
	}

}
