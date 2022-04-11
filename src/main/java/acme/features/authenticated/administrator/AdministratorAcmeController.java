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
		
		Collection<Patronage> patronagesProposed=this.patronageService.findPatronagesByStatus(Status.PROPOSED); 
		Collection<Patronage> patronagesAccepted=this.patronageService.findPatronagesByStatus(Status.ACCEPTED);
		Collection<Patronage> patronagesDenied=this.patronageService.findPatronagesByStatus(Status.DENIED);;
		
		List<Collection<Patronage>> patronages= new ArrayList<Collection<Patronage>>();
		
		patronages.add(patronagesProposed);
		patronages.add(patronagesAccepted);
		patronages.add(patronagesDenied);
		
		Collection<Item> components =this.componentsService.findItemsByType(Type.COMPONENT);
		Collection<Item> tools =this.componentsService.findItemsByType(Type.TOOL);
		AdministratorDashboard patronDashboard= new AdministratorDashboard(patronages, tools, components);
		
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
		result.addObject("dashboard", administratorDashboard);
		this.addPatronagesStats(result, administratorDashboard);
		this.addToolsStats(result, administratorDashboard);
		this.addComponentsStats(result, administratorDashboard);
		
	}
	
	private void addToolsStats(ModelAndView result, AdministratorDashboard administratorDashboard) {
		// Average
		result.addObject("toolsAverageEUR",administratorDashboard.getRetailPriceToolsAverage().get("EUR"));
		result.addObject("toolsAverageUSD",administratorDashboard.getRetailPriceToolsAverage().get("USD"));
		result.addObject("toolsAverageGBP",administratorDashboard.getRetailPriceToolsAverage().get("GBP"));

		// Deviation
		result.addObject("toolsDeviationEUR",administratorDashboard.getRetailPriceToolsDeviation().get("EUR"));
		result.addObject("toolsDeviationUSD",administratorDashboard.getRetailPriceToolsDeviation().get("USD"));
		result.addObject("toolsDeviationGBP",administratorDashboard.getRetailPriceToolsDeviation().get("GBP"));

		// Max
		result.addObject("toolsMaxEUR",administratorDashboard.getRetailPriceToolsMaximum().get("EUR"));
		result.addObject("toolsMaxUSD",administratorDashboard.getRetailPriceToolsMaximum().get("USD"));
		result.addObject("toolsMaxGBP",administratorDashboard.getRetailPriceToolsMaximum().get("GBP"));
		
		// Min
		result.addObject("toolsMinEUR",administratorDashboard.getRetailPriceToolsMinimum().get("EUR"));
		result.addObject("toolsMinUSD",administratorDashboard.getRetailPriceToolsMinimum().get("USD"));
		result.addObject("toolsMinGBP",administratorDashboard.getRetailPriceToolsMinimum().get("GBP"));
		
	}

	private void addComponentsStats(ModelAndView result, AdministratorDashboard administratorDashboard) {
		//Average
		result.addObject("componentsAverageAcceptedEUR",administratorDashboard.getRetailPriceComponentsAverage().get(Pair.of("Energy","EUR")));
		result.addObject("componentsAverageAcceptedUSD",administratorDashboard.getRetailPriceComponentsAverage().get(Pair.of("Energy","USD")));
		result.addObject("componentsAverageAcceptedGBP",administratorDashboard.getRetailPriceComponentsAverage().get(Pair.of("Energy","GBP")));
		result.addObject("componentsAverageProposedEUR",administratorDashboard.getRetailPriceComponentsAverage().get(Pair.of("Electrical","EUR")));
		result.addObject("componentsAverageProposedUSD",administratorDashboard.getRetailPriceComponentsAverage().get(Pair.of("Electrical","USD")));
		result.addObject("componentsAverageProposedGBP",administratorDashboard.getRetailPriceComponentsAverage().get(Pair.of("Electrical","GBP")));
		result.addObject("componentsAverageDeniedEUR",administratorDashboard.getRetailPriceComponentsAverage().get(Pair.of("T","EUR")));
		result.addObject("componentsAverageDeniedUSD",administratorDashboard.getRetailPriceComponentsAverage().get(Pair.of("T","USD")));
		result.addObject("componentsAverageDeniedGBP",administratorDashboard.getRetailPriceComponentsAverage().get(Pair.of("T","GBP")));
		//Deviation
		result.addObject("componentsDeviationAcceptedEUR",administratorDashboard.getRetailPriceComponentsDeviation().get(Pair.of("Energy","EUR")));
		result.addObject("componentsDeviationAcceptedUSD",administratorDashboard.getRetailPriceComponentsDeviation().get(Pair.of("Energy","USD")));
		result.addObject("componentsDeviationAcceptedGBP",administratorDashboard.getRetailPriceComponentsDeviation().get(Pair.of("Energy","GBP")));
		result.addObject("componentsDeviationProposedEUR",administratorDashboard.getRetailPriceComponentsDeviation().get(Pair.of("Electrical","EUR")));
		result.addObject("componentsDeviationProposedUSD",administratorDashboard.getRetailPriceComponentsDeviation().get(Pair.of("Electrical","USD")));
		result.addObject("componentsDeviationProposedGBP",administratorDashboard.getRetailPriceComponentsDeviation().get(Pair.of("Electrical","GBP")));
		result.addObject("componentsDeviationDeniedEUR",administratorDashboard.getRetailPriceComponentsDeviation().get(Pair.of("T","EUR")));
		result.addObject("componentsDeviationDeniedUSD",administratorDashboard.getRetailPriceComponentsDeviation().get(Pair.of("T","USD")));
		result.addObject("componentsDeviationDeniedGBP",administratorDashboard.getRetailPriceComponentsDeviation().get(Pair.of("T","GBP")));
		//Max
		result.addObject("componentsMaxAcceptedEUR",administratorDashboard.getRetailPriceComponentsMaximum().get(Pair.of("Energy","EUR")));
		result.addObject("componentsMaxAcceptedUSD",administratorDashboard.getRetailPriceComponentsMaximum().get(Pair.of("Energy","USD")));
		result.addObject("componentsMaxAcceptedGBP",administratorDashboard.getRetailPriceComponentsMaximum().get(Pair.of("Energy","GBP")));
		result.addObject("componentsMaxProposedEUR",administratorDashboard.getRetailPriceComponentsMaximum().get(Pair.of("Electrical","EUR")));
		result.addObject("componentsMaxProposedUSD",administratorDashboard.getRetailPriceComponentsMaximum().get(Pair.of("Electrical","USD")));
		result.addObject("componentsMaxProposedGBP",administratorDashboard.getRetailPriceComponentsMaximum().get(Pair.of("Electrical","GBP")));
		result.addObject("componentsMaxDeniedEUR",administratorDashboard.getRetailPriceComponentsMaximum().get(Pair.of("T","EUR")));
		result.addObject("componentsMaxDeniedUSD",administratorDashboard.getRetailPriceComponentsMaximum().get(Pair.of("T","USD")));
		result.addObject("componentsMaxDeniedGBP",administratorDashboard.getRetailPriceComponentsMaximum().get(Pair.of("T","GBP")));
		//Min
		result.addObject("componentsMinAcceptedEUR",administratorDashboard.getRetailPriceComponentsMinimum().get(Pair.of("Energy","EUR")));
		result.addObject("componentsMinAcceptedUSD",administratorDashboard.getRetailPriceComponentsMinimum().get(Pair.of("Energy","USD")));
		result.addObject("componentsMinAcceptedGBP",administratorDashboard.getRetailPriceComponentsMinimum().get(Pair.of("Energy","GBP")));
		result.addObject("componentsMinProposedEUR",administratorDashboard.getRetailPriceComponentsMinimum().get(Pair.of("Electrical","EUR")));
		result.addObject("componentsMinProposedUSD",administratorDashboard.getRetailPriceComponentsMinimum().get(Pair.of("Electrical","USD")));
		result.addObject("componentsMinProposedGBP",administratorDashboard.getRetailPriceComponentsMinimum().get(Pair.of("Electrical","GBP")));
		result.addObject("componentsMinDeniedEUR",administratorDashboard.getRetailPriceComponentsMinimum().get(Pair.of("T","EUR")));
		result.addObject("componentsMinDeniedUSD",administratorDashboard.getRetailPriceComponentsMinimum().get(Pair.of("T","USD")));
		result.addObject("componentsMinDeniedGBP",administratorDashboard.getRetailPriceComponentsMinimum().get(Pair.of("T","GBP")));
		
	}

	private void addPatronagesStats(ModelAndView result,AdministratorDashboard administratorDashboard) {
		
		//Average
		result.addObject("patronagesAverageAcceptedEUR",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.ACCEPTED,"EUR")));
		result.addObject("patronagesAverageAcceptedUSD",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.ACCEPTED,"USD")));
		result.addObject("patronagesAverageAcceptedGBP",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.ACCEPTED,"GBP")));
		result.addObject("patronagesAverageProposedEUR",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.PROPOSED,"EUR")));
		result.addObject("patronagesAverageProposedUSD",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.PROPOSED,"USD")));
		result.addObject("patronagesAverageProposedGBP",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.PROPOSED,"GBP")));
		result.addObject("patronagesAverageDeniedEUR",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.DENIED,"EUR")));
		result.addObject("patronagesAverageDeniedUSD",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.DENIED,"USD")));
		result.addObject("patronagesAverageDeniedGBP",administratorDashboard.getPatronagesAverage().get(Pair.of(Status.DENIED,"GBP")));
		//Deviation
		result.addObject("patronagesDeviationAcceptedEUR",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.ACCEPTED,"EUR")));
		result.addObject("patronagesDeviationAcceptedUSD",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.ACCEPTED,"USD")));
		result.addObject("patronagesDeviationAcceptedGBP",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.ACCEPTED,"GBP")));
		result.addObject("patronagesDeviationProposedEUR",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.PROPOSED,"EUR")));
		result.addObject("patronagesDeviationProposedUSD",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.PROPOSED,"USD")));
		result.addObject("patronagesDeviationProposedGBP",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.PROPOSED,"GBP")));
		result.addObject("patronagesDeviationDeniedEUR",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.DENIED,"EUR")));
		result.addObject("patronagesDeviationDeniedUSD",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.DENIED,"USD")));
		result.addObject("patronagesDeviationDeniedGBP",administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.DENIED,"GBP")));
		//Max
		result.addObject("patronagesMaxAcceptedEUR",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.ACCEPTED,"EUR")));
		result.addObject("patronagesMaxAcceptedUSD",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.ACCEPTED,"USD")));
		result.addObject("patronagesMaxAcceptedGBP",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.ACCEPTED,"GBP")));
		result.addObject("patronagesMaxProposedEUR",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.PROPOSED,"EUR")));
		result.addObject("patronagesMaxProposedUSD",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.PROPOSED,"USD")));
		result.addObject("patronagesMaxProposedGBP",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.PROPOSED,"GBP")));
		result.addObject("patronagesMaxDeniedEUR",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.DENIED,"EUR")));
		result.addObject("patronagesMaxDeniedUSD",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.DENIED,"USD")));
		result.addObject("patronagesMaxDeniedGBP",administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.DENIED,"GBP")));
		//Min
		result.addObject("patronagesMinAcceptedEUR",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.ACCEPTED,"EUR")));
		result.addObject("patronagesMinAcceptedUSD",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.ACCEPTED,"USD")));
		result.addObject("patronagesMinAcceptedGBP",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.ACCEPTED,"GBP")));
		result.addObject("patronagesMinProposedEUR",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.PROPOSED,"EUR")));
		result.addObject("patronagesMinProposedUSD",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.PROPOSED,"USD")));
		result.addObject("patronagesMinProposedGBP",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.PROPOSED,"GBP")));
		result.addObject("patronagesMinDeniedEUR",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.DENIED,"EUR")));
		result.addObject("patronagesMinDeniedUSD",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.DENIED,"USD")));
		result.addObject("patronagesMinDeniedGBP",administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.DENIED,"GBP")));

	}

	

}
