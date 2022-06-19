package it.uniroma3.siw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.CredentialsValidator;
import it.uniroma3.siw.controller.validator.UserValidator;
import it.uniroma3.siw.model.Cinturino;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Designer;
import it.uniroma3.siw.model.Orologio;
import it.uniroma3.siw.model.PuntoVendita;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CinturinoService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.DesignerService;
import it.uniroma3.siw.service.OrologioService;
import it.uniroma3.siw.service.PuntoVenditaService;

@Controller

public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private CredentialsValidator credentialsValidator;
	
	@Autowired
	OrologioService os;
	@Autowired
	DesignerService ds;
	@Autowired
	CinturinoService cs;
	@Autowired
	PuntoVenditaService pvs;
	
	
	
	@GetMapping("/register") 
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "register.html";
	}
	
	@GetMapping("/login") 
	public String showLoginForm (Model model) {
		return "login.html";
	}
	
	
	@GetMapping("/logout") 
	public String logout(Model model) {
		return "index.html";
	}
	
	@GetMapping("/admin/features")
	public String features(Model model) {
		List<Cinturino> elencoCinturini = this.cs.findAllCinturini();
		List<Designer> elencoDesigner = this.ds.findAllDesigner();
		List<Orologio> elencoOrologi = this.os.findAllOrologi();
		List<PuntoVendita> elencoPuntiVendita = this.pvs.findAllPuntiVendita();
		model.addAttribute("elencoCinturini", elencoCinturini);
		model.addAttribute("elencoDesigner", elencoDesigner);
		model.addAttribute("elencoOrologi", elencoOrologi);
		model.addAttribute("elencoPuntiVendita", elencoPuntiVendita);
		return "adminFeatures.html";
	}
	
	
    @GetMapping("/default")
    public String defaultAfterLogin(Model model) {
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			return "adminPage.html";
		}
		return "index.html";
	}
	
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                 BindingResult userBindingResult,
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {

        // validate user and credentials fields
        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            return "registrationSuccessful.html";
        }
        return "login.html";
    }
}