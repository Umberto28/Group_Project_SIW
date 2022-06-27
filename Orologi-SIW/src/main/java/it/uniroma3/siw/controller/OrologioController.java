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

import it.uniroma3.siw.controller.validator.OrologioValidator;
import it.uniroma3.siw.model.Designer;
import it.uniroma3.siw.model.Orologio;
import it.uniroma3.siw.model.PuntoVendita;
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

	@PostMapping("/admin/orologio")

	public String addOrologio(@Valid @ModelAttribute("orologio") Orologio o,
			BindingResult bindingResult,
			@RequestParam(name = "designerScelto") Long Did,
			@RequestParam(name = "puntoVenditaScelto") Long PVid, Model model) {


		this.orologioValidator.validate(o, bindingResult);

		if (!bindingResult.hasErrors()) {


			Designer d = this.designerService.searchById(Did);

			PuntoVendita pv = this.puntoVenditaService.searchById(PVid);

			o.setDesigner(d);
			o.setPuntoVenditaOrologi(pv);

			d.getOrologiCreati().add(o);
			pv.getOrologiInVendita().add(o);

			this.designerService.inserisci(d);

			this.puntoVenditaService.inserisci(pv);


			model.addAttribute("orologio", o);
			model.addAttribute("elencoCinturiniPosseduti", o.getCinturiniPosseduti());
			return "/Orologio/orologio.html";

		}
		model.addAttribute("orologio", o);
		return "/Orologio/orologioForm.html";

	}

	@GetMapping("/elencoOrologi")
	private String getAllOrologi(Model model) {
		List<Orologio> elencoOrologi = this.orologioService.findAllOrologi();
		model.addAttribute("elencoOrologi", elencoOrologi);
		return "/Orologio/elencoOrologi.html";
	}

	@GetMapping("/admin/orologioForm")
	private String getOrologioForm(Model model) {
		model.addAttribute("orologio", new Orologio());
		model.addAttribute("designerDisponibili", this.designerService.findAllDesigner());
		model.addAttribute("puntiVenditaDisponibili", this.puntoVenditaService.findAllPuntiVendita());
		return "/Orologio/orologioForm.html";
	}

	@GetMapping("/orologio/{id}")
	private String getOrologio(@PathVariable("id") Long id, Model model) {
		Orologio orologio = this.orologioService.searchById(id);
		model.addAttribute("orologio", orologio);
		model.addAttribute("elencoCinturiniPosseduti", orologio.getCinturiniPosseduti());
		return "/Orologio/orologio.html";
	}

	@GetMapping("/deleteOrologio")
	private String deleteOrologio(@RequestParam Long orologioId) {
		this.orologioService.rimuovi(orologioId);
		return "redirect:/elencoOrologi";
	}

	@GetMapping("/admin/updateOrologio")
	private String updateOrologioForm(@RequestParam Long orologioId, Model model) {
		model.addAttribute("orologio", this.orologioService.searchById(orologioId));
		// potremmo voler cambiare il punto vendita dell'orologio
		model.addAttribute("puntiVenditaDisponibili", this.puntoVenditaService.findAllPuntiVendita());
		return "/Orologio/orologioUpdateForm.html";
	}

	@PostMapping("admin/orologioUpdate/{id}")
	private String updateOrologio(@Valid @ModelAttribute("orologio") Orologio o, 
			@RequestParam(name = "puntoVenditaScelto") Long PVid,
			BindingResult bindingResult,
			Model model) {
		
		this.orologioValidator.validate(o, bindingResult);
		if (!bindingResult.hasErrors()) {
			PuntoVendita PVNuovo = this.puntoVenditaService.searchById(PVid);
			PuntoVendita PVVecchio = o.getPuntoVenditaOrologi();
			
			if(PVVecchio!=null) {
				for(Orologio oInList : PVVecchio.getOrologiInVendita()) {
					if(oInList.getId() == o.getId()) {
						PVVecchio.getOrologiInVendita().remove(oInList);
					}
				}
			}
				
	
			
			
			
			
		
			o.setPuntoVenditaOrologi(PVNuovo);
			PVNuovo.getOrologiInVendita().add(o);
			this.puntoVenditaService.inserisci(PVNuovo);
			//this.orologioService.inserisci(o);
			model.addAttribute("orologio", o);
			model.addAttribute("elencoCinturiniPosseduti", o.getCinturiniPosseduti());
			return "/Orologio/orologio.html";
		}
		model.addAttribute("orologio", o);
		return "/Orologio/orologioUpdateForm.html";
	}
}
