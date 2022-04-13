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
		Double[][] componentsAverage= new Double[administratorDashboard.getTechnologiesOfComponents().length][3];
		Double[][] componentsDeviation= new Double[administratorDashboard.getTechnologiesOfComponents().length][3];
		Double[][] componentsMax= new Double[administratorDashboard.getTechnologiesOfComponents().length][3];
		Double[][] componentsMin= new Double[administratorDashboard.getTechnologiesOfComponents().length][3];
		String[] technologies=administratorDashboard.getTechnologiesOfComponents();
		String[] currencies= new String[] {"EUR","USD","GBP"};
		for(int i=0; i<technologies.length; i++) {
			for(int j=0; j<currencies.length;j++) {
				componentsAverage[i][j]=administratorDashboard.getRetailPriceComponentsAverage().get(Pair.of(technologies[i],currencies[j]));
				componentsDeviation[i][j]=administratorDashboard.getRetailPriceComponentsDeviation().get(Pair.of(technologies[i],currencies[j]));
				componentsMax[i][j]=administratorDashboard.getRetailPriceComponentsMaximum().get(Pair.of(technologies[i],currencies[j]));
				componentsMin[i][j]=administratorDashboard.getRetailPriceComponentsMinimum().get(Pair.of(technologies[i],currencies[j]));
			}
		}
		result.addObject("technologies", technologies);
		result.addObject("componentsAverage",componentsAverage);
		result.addObject("componentsDeviation",componentsDeviation);
		result.addObject("componentsMax",componentsMax);
		result.addObject("componentsMin",componentsMin);
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
