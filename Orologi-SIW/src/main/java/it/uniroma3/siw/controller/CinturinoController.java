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

import it.uniroma3.siw.controller.validator.CinturinoValidator;
import it.uniroma3.siw.model.Cinturino;
import it.uniroma3.siw.model.Custodia;
import it.uniroma3.siw.model.Orologio;
import it.uniroma3.siw.model.PuntoVendita;
import it.uniroma3.siw.service.CinturinoService;
import it.uniroma3.siw.service.CustodiaService;
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
	@Autowired
	CustodiaService custodiaService;

	@PostMapping("/admin/cinturino")
	public String addCinturino(@Valid @ModelAttribute("cinturino") Cinturino c,
			BindingResult bindingResult,
			@RequestParam(name = "orologioScelto") Long Oid,
			@RequestParam(name = "puntoVenditaScelto") Long PVid, Model model) {
		this.cinturinoValidator.validate(c, bindingResult);

		if (!bindingResult.hasErrors()) {

			Orologio o = this.orologioService.searchById(Oid);

			PuntoVendita pv = this.puntoVenditaService.searchById(PVid);

			c.setOrologio(o);
			c.setPuntoVenditaCinturini(pv);

			o.getCinturiniPosseduti().add(c);
			pv.getCinturiniInVendita().add(c);

			this.puntoVenditaService.inserisci(pv);
			this.orologioService.inserisci(o);

			model.addAttribute("cinturino", c);
			return "/Cinturino/cinturino.html";

		}
		model.addAttribute("cinturino", c);
		return "/Cinturino/cinturinoForm.html";

	}
	
	@GetMapping("/elencoAccessori")

	private String getAllCinturini(Model model) {
		List<Cinturino> elencoCinturini = this.cinturinoService.findAllCinturini();
		model.addAttribute("elencoCinturini", elencoCinturini);
		List<Custodia> elencoCustodie = this.custodiaService.findAllCustodie();
		model.addAttribute("elencoCustodie", elencoCustodie);
		return "/Cinturino/elencoAccessori.html";
	}

	@GetMapping("/admin/cinturinoForm")
	private String getCinturinoForm(Model model) {
		model.addAttribute("cinturino", new Cinturino());
		model.addAttribute("orologiDisponibili", this.orologioService.findAllOrologi());
		model.addAttribute("puntiVenditaDisponibili", this.puntoVenditaService.findAllPuntiVendita());
		return "/Cinturino/cinturinoForm.html";
	}

	@GetMapping("/cinturino/{id}")
	private String getCinturino(@PathVariable("id") Long id, Model model) {
		Cinturino cinturino = this.cinturinoService.searchById(id);
		model.addAttribute("cinturino", cinturino);
		return "/Cinturino/cinturino.html";
	}

	@GetMapping("/deleteCinturino")
	private String deleteCinturino(@RequestParam Long cinturinoId) {
		this.cinturinoService.rimuovi(cinturinoId);
		return "redirect:/elencoAccessori";
	}

	@GetMapping("/admin/updateCinturino")
	private String updateCinturinoForm(@RequestParam Long cinturinoId, Model model) {
		model.addAttribute("cinturino", this.cinturinoService.searchById(cinturinoId));
		// potremmo voler cambiare il punto vendita del cinturino
		model.addAttribute("puntiVenditaDisponibili", this.puntoVenditaService.findAllPuntiVendita());
		return "/Cinturino/cinturinoUpdateForm.html";
	}

	@PostMapping("/admin/cinturinoUpdate/{id}")
	private String updateCinturino(@Valid @ModelAttribute("cinturino") Cinturino c,
			@RequestParam(name = "puntoVenditaScelto") Long PVid,
			BindingResult bindingResult,
			Model model) {
		
		this.cinturinoValidator.validate(c, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			PuntoVendita PVNuovo = this.puntoVenditaService.searchById(PVid);
			PuntoVendita PVVecchio = c.getPuntoVenditaCinturini();
			
			if(PVVecchio!=null) {
				for(Cinturino cInList : PVVecchio.getCinturiniInVendita()) {
					if(cInList.getId() == c.getId()) {
						PVVecchio.getCinturiniInVendita().remove(cInList);
					}
				}
			}
		
			c.setPuntoVenditaCinturini(PVNuovo);
			
			PVNuovo.getCinturiniInVendita().add(c);
			
			this.puntoVenditaService.inserisci(PVNuovo);
			
			model.addAttribute("cinturino", c);
			return "/Cinturino/cinturino.html";
		}
		model.addAttribute("cinturino", c);
		return "/Cinturino/cinturinoUpdateForm.html";
	}

}
