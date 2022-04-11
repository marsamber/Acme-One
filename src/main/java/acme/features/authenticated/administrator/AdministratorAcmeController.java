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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import acme.entities.Item;
import acme.entities.Item.Type;
import acme.entities.Patronage;
import acme.entities.Patronage.Status;
import acme.features.anonymous.item.AnonymousItemListAllComponentsService;
import acme.forms.AdministratorDashboard;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;
import acme.framework.roles.Authenticated;
import acme.services.PatronageShowService;

@Controller
@RequestMapping("/administrator")
public class AdministratorAcmeController extends AbstractController<Authenticated, Administrator> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorShowService	administratorService;
	@Autowired
	protected PatronageShowService	patronageService;
	@Autowired
	protected AnonymousItemListAllComponentsService	componentsService;


	// Constructors -----------------------------------------------------------

	private AdministratorDashboard createAdministratorDashboard() {
		
		Collection<Patronage> patronagesProposed=this.patronageService.findPatronagesByStatus(Status.PROPOSED); // TODO Llamada a la funcion servicio que recoja los patrocinios propuestos de este patrocinador
		Collection<Patronage> patronagesAccepted=this.patronageService.findPatronagesByStatus(Status.ACCEPTED);; // TODO Llamada a la funcion servicio que recoja los patrocinios aceptados de este patrocinador
		Collection<Patronage> patronagesDenied=this.patronageService.findPatronagesByStatus(Status.DENIED);;
		
		List<Collection<Patronage>> patronages= new ArrayList<Collection<Patronage>>();
		
		patronages.add(patronagesProposed);
		patronages.add(patronagesAccepted);
		patronages.add(patronagesDenied);
		
		Collection<Item> components =this.componentsService.findItemsByType(Type.COMPONENT);
		AdministratorDashboard patronDashboard= new AdministratorDashboard(patronages, components);
		
		return patronDashboard;
	}
	
	@GetMapping("/dashboard")
	public ModelAndView patronDashboardController() {
		final ModelAndView result = new ModelAndView();
		result.setViewName("authenticated/administrator/administrator-dashboard");
		AdministratorDashboard administratorDashboard= this.createAdministratorDashboard();
		
		this.addStatsToModel(result,administratorDashboard);
		return result;
	}
	private void addStatsToModel(ModelAndView result,AdministratorDashboard administratorDashboard) {
		this.addPatronagesStats(result, administratorDashboard);
		
		
	}
	
	private void addPatronagesStats(ModelAndView result,AdministratorDashboard administratorDashboard) {
		result.addObject("dashboard", administratorDashboard);
		//Average
		result.addObject("averageAcceptedEUR",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.ACCEPTED,"EUR")));
		result.addObject("averageAcceptedUSD",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.ACCEPTED,"USD")));
		result.addObject("averageAcceptedGBP",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.ACCEPTED,"GBP")));
		result.addObject("averageProposedEUR",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.PROPOSED,"EUR")));
		result.addObject("averageProposedUSD",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.PROPOSED,"USD")));
		result.addObject("averageProposedGBP",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.PROPOSED,"GBP")));
		result.addObject("averageDeniedEUR",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.DENIED,"EUR")));
		result.addObject("averageDeniedUSD",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.DENIED,"USD")));
		result.addObject("averageDeniedGBP",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.DENIED,"GBP")));
		//Deviation
		result.addObject("deviationAcceptedEUR",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.ACCEPTED,"EUR")));
		result.addObject("deviationAcceptedUSD",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.ACCEPTED,"USD")));
		result.addObject("deviationAcceptedGBP",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.ACCEPTED,"GBP")));
		result.addObject("deviationProposedEUR",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.PROPOSED,"EUR")));
		result.addObject("deviationProposedUSD",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.PROPOSED,"USD")));
		result.addObject("deviationProposedGBP",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.PROPOSED,"GBP")));
		result.addObject("deviationDeniedEUR",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.DENIED,"EUR")));
		result.addObject("deviationDeniedUSD",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.DENIED,"USD")));
		result.addObject("deviationDeniedGBP",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.DENIED,"GBP")));
		//Max
		result.addObject("maxAcceptedEUR",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.ACCEPTED,"EUR")));
		result.addObject("maxAcceptedUSD",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.ACCEPTED,"USD")));
		result.addObject("maxAcceptedGBP",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.ACCEPTED,"GBP")));
		result.addObject("maxProposedEUR",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.PROPOSED,"EUR")));
		result.addObject("maxProposedUSD",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.PROPOSED,"USD")));
		result.addObject("maxProposedGBP",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.PROPOSED,"GBP")));
		result.addObject("maxDeniedEUR",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.DENIED,"EUR")));
		result.addObject("maxDeniedUSD",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.DENIED,"USD")));
		result.addObject("maxDeniedGBP",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.DENIED,"GBP")));
		//Min
		result.addObject("minAcceptedEUR",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.ACCEPTED,"EUR")));
		result.addObject("minAcceptedUSD",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.ACCEPTED,"USD")));
		result.addObject("minAcceptedGBP",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.ACCEPTED,"GBP")));
		result.addObject("minProposedEUR",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.PROPOSED,"EUR")));
		result.addObject("minProposedUSD",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.PROPOSED,"USD")));
		result.addObject("minProposedGBP",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.PROPOSED,"GBP")));
		result.addObject("minDeniedEUR",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.DENIED,"EUR")));
		result.addObject("minDeniedUSD",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.DENIED,"USD")));
		result.addObject("minDeniedGBP",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.DENIED,"GBP")));

	}

	

}
