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
			this.puntoVenditaService.inserisci(pv);

			model.addAttribute("elencoPuntiVendita", this.puntoVenditaService.findAllPuntiVendita());
			return "/PuntoVendita/elencoPuntiVendita.html";

		}
		model.addAttribute("puntoVendita", pv);
		return "/PuntoVendita/puntoVenditaForm.html";

	}

	@GetMapping("/elencoStore")
	private String getAllOrologi(Model model) {
		List<PuntoVendita> elencoPuntiVendita = this.puntoVenditaService.findAllPuntiVendita();
		model.addAttribute("elencoPuntiVendita", elencoPuntiVendita);
		return "/PuntoVendita/elencoPuntiVendita.html";
	}

	@GetMapping("/admin/puntoVenditaForm")
	private String getPuntoVenditaForm(Model model) {
		model.addAttribute("puntoVendita", new PuntoVendita());
		return "/PuntoVendita/puntoVenditaForm.html";
	}

	@GetMapping("/puntoVendita/{id}")
	private String getPuntoVendita(@PathVariable("id") Long id, Model model) {
		PuntoVendita puntoVendita = this.puntoVenditaService.searchById(id);
		model.addAttribute("puntoVendita", puntoVendita);
		model.addAttribute("elencoCinturiniInVendita", puntoVendita.getCinturiniInVendita());
		model.addAttribute("elencoOrologiInVendita", puntoVendita.getOrologiInVendita());
		model.addAttribute("elencoCustodieInVendita", puntoVendita.getCustodieInVendita());
		return "/PuntoVendita/puntoVendita.html";
	}

	@GetMapping("/deletePuntoVendita")
	private String deletePuntoVendita(@RequestParam Long puntoVenditaId) {
		this.puntoVenditaService.rimuovi(puntoVenditaId);
		return "redirect:/elencoStore";
	}

	@GetMapping("/admin/updatePuntoVendita")
	private String updatePuntoVenditaForm(@RequestParam Long puntoVenditaId, Model model) {
		model.addAttribute("puntoVendita", this.puntoVenditaService.searchById(puntoVenditaId));
		return "/PuntoVendita/puntoVenditaUpdateForm.html";
	}

	@PostMapping("/admin/puntoVenditaUpdate/{id}")
	private String updatePuntoVendita(@Valid @ModelAttribute("puntoVendita") PuntoVendita pv,
			BindingResult bindingResult, Model model) {
		this.puntoVenditaValidator.validate(pv, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.puntoVenditaService.inserisci(pv);
			model.addAttribute("puntoVendita", pv);
			model.addAttribute("elencoOrologiInVendita", pv.getOrologiInVendita());
			model.addAttribute("elencoCinturiniInVendita", pv.getCinturiniInVendita());
			model.addAttribute("elencoCustodieInVendita", pv.getCustodieInVendita());
			return "/PuntoVendita/puntoVendita.html";
		}
		model.addAttribute("puntoVendita", pv);
		return "/PuntoVendita/puntoVenditaUpdateForm.html";
	}

}
