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

package acme.features.authenticated.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import acme.entities.Patronage.Status;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;
import acme.framework.roles.Authenticated;

@Controller
@RequestMapping("/administrator")
public class AdministratorAcmeController extends AbstractController<Authenticated, Administrator> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorService	administratorService;


	// Constructors -----------------------------------------------------------


	
	@GetMapping("/dashboard")
	public ModelAndView patronDashboardController() {
		final ModelAndView result = new ModelAndView();
		result.setViewName("authenticated/administrator/administrator-dashboard");
		result.addObject("totalProposed", this.administratorService.NumberOfPatronageByStatus(Status.PROPOSED));
		
		return result;
	}

}
