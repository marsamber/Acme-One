/*
 * AuthenticatedProviderController.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.patron;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import acme.entities.Patronage;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;
import acme.roles.Patron;
import acme.services.PatronageShowService;

@Controller
@RequestMapping("/authenticated/patron")
public class AuthenticatedPatronController extends AbstractController<Authenticated, Patron> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronageShowService	patronageService;

	//@Autowired
	//protected AuthenticatedPatronUpdateService	updateService;

	// Constructors -----------------------------------------------------------

	/*
	@PostConstruct
	protected void initialise() {
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
	}
	*/
	
	@GetMapping("/dashboard")
	public ModelAndView patronDashboardController() {
		ModelAndView result;
		
		result = new ModelAndView();
		result.setViewName("authenticated/patron/patron-dashboard");
		Collection<Patronage> patronagesByPatronAndProposed=this.patronageService.findPatronagesByPatronAndStatus(null, null); // TODO Llamada a la funcion servicio que recoja los patrocinios propuestos de este patrocinador
		Collection<Patronage> patronagesByPatronAndAccepted=this.patronageService.findPatronagesByPatronAndStatus(null, null);; // TODO Llamada a la funcion servicio que recoja los patrocinios aceptados de este patrocinador
		Collection<Patronage> patronagesByPatronAndDennied=this.patronageService.findPatronagesByPatronAndStatus(null, null);;
		
		List<Collection<Patronage>> patronages= new ArrayList<Collection<Patronage>>();
		
		patronages.add(patronagesByPatronAndProposed);
		patronages.add(patronagesByPatronAndAccepted);
		patronages.add(patronagesByPatronAndDennied);
		//PatronDashboard patronDashboard= new PatronDashboard(patronages);
		
		//result.addObject("dashboard", patronDashboard);
		
		return result;
	}

}
