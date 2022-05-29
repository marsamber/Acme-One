/*
 * AdministratorAdministratorDashboardShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Item.Type;
import acme.entities.Patronage;
import acme.entities.Patronage.Status;
import acme.features.any.item.AnyItemListAllComponentsService;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;
import acme.services.PatronageShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository	repository;
	@Autowired
	protected PatronageShowService				patronageService;
	@Autowired
	protected AnyItemListAllComponentsService	componentsService;

	// AbstractShowService<Administrator, AdministratorDashboard> interface ----------------


	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;

		return this.createAdministratorDashboard();
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("dashboard", entity);
		this.addPatronagesStats(model, entity);
		this.addToolsStats(model, entity);
		this.addComponentsStats(model, entity);

	}

	// Constructors -----------------------------------------------------------

	private AdministratorDashboard createAdministratorDashboard() {

		final Collection<Patronage> patronagesProposed = this.patronageService.findPatronagesByStatus(Status.PROPOSED);
		final Collection<Patronage> patronagesAccepted = this.patronageService.findPatronagesByStatus(Status.ACCEPTED);
		final Collection<Patronage> patronagesDenied = this.patronageService.findPatronagesByStatus(Status.DENIED);
		;

		final List<Collection<Patronage>> patronages = new ArrayList<Collection<Patronage>>();

		patronages.add(patronagesProposed);
		patronages.add(patronagesAccepted);
		patronages.add(patronagesDenied);

		final Collection<Item> components = this.componentsService.findItemsByType(Type.COMPONENT);
		final Collection<Item> tools = this.componentsService.findItemsByType(Type.TOOL);

		return new AdministratorDashboard(patronages, tools, components);
	}

	private void addToolsStats(final Model result, final AdministratorDashboard administratorDashboard) {
		// Average
		result.setAttribute("toolsAverageEUR", administratorDashboard.getRetailPriceToolsAverage().get("EUR"));
		result.setAttribute("toolsAverageUSD", administratorDashboard.getRetailPriceToolsAverage().get("USD"));
		result.setAttribute("toolsAverageGBP", administratorDashboard.getRetailPriceToolsAverage().get("GBP"));

		// Deviation
		result.setAttribute("toolsDeviationEUR", administratorDashboard.getRetailPriceToolsDeviation().get("EUR"));
		result.setAttribute("toolsDeviationUSD", administratorDashboard.getRetailPriceToolsDeviation().get("USD"));
		result.setAttribute("toolsDeviationGBP", administratorDashboard.getRetailPriceToolsDeviation().get("GBP"));

		// Max
		result.setAttribute("toolsMaxEUR", administratorDashboard.getRetailPriceToolsMaximum().get("EUR"));
		result.setAttribute("toolsMaxUSD", administratorDashboard.getRetailPriceToolsMaximum().get("USD"));
		result.setAttribute("toolsMaxGBP", administratorDashboard.getRetailPriceToolsMaximum().get("GBP"));

		// Min
		result.setAttribute("toolsMinEUR", administratorDashboard.getRetailPriceToolsMinimum().get("EUR"));
		result.setAttribute("toolsMinUSD", administratorDashboard.getRetailPriceToolsMinimum().get("USD"));
		result.setAttribute("toolsMinGBP", administratorDashboard.getRetailPriceToolsMinimum().get("GBP"));

	}

	private void addComponentsStats(final Model result, final AdministratorDashboard administratorDashboard) {
		//Average
		final Double[][] componentsAverage = new Double[administratorDashboard.getTechnologiesOfComponents().length][3];
		final Double[][] componentsDeviation = new Double[administratorDashboard.getTechnologiesOfComponents().length][3];
		final Double[][] componentsMax = new Double[administratorDashboard.getTechnologiesOfComponents().length][3];
		final Double[][] componentsMin = new Double[administratorDashboard.getTechnologiesOfComponents().length][3];
		final String[] technologies = administratorDashboard.getTechnologiesOfComponents();
		final String[] currencies = new String[] {
			"EUR", "USD", "GBP"
		};
		for (int i = 0; i < technologies.length; i++) {
			for (int j = 0; j < currencies.length; j++) {
				componentsAverage[i][j] = administratorDashboard.getRetailPriceComponentsAverage().get(Pair.of(technologies[i], currencies[j]));
				componentsDeviation[i][j] = administratorDashboard.getRetailPriceComponentsDeviation().get(Pair.of(technologies[i], currencies[j]));
				componentsMax[i][j] = administratorDashboard.getRetailPriceComponentsMaximum().get(Pair.of(technologies[i], currencies[j]));
				componentsMin[i][j] = administratorDashboard.getRetailPriceComponentsMinimum().get(Pair.of(technologies[i], currencies[j]));
			}
		}
		result.setAttribute("technologies", technologies);
		result.setAttribute("componentsAverage", componentsAverage);
		result.setAttribute("componentsDeviation", componentsDeviation);
		result.setAttribute("componentsMax", componentsMax);
		result.setAttribute("componentsMin", componentsMin);
	}

	private void addPatronagesStats(final Model result, final AdministratorDashboard administratorDashboard) {

		//Average
		result.setAttribute("patronagesAverageAcceptedEUR", administratorDashboard.getPatronagesAverage().get(Pair.of(Status.ACCEPTED, "EUR")));
		result.setAttribute("patronagesAverageAcceptedUSD", administratorDashboard.getPatronagesAverage().get(Pair.of(Status.ACCEPTED, "USD")));
		result.setAttribute("patronagesAverageAcceptedGBP", administratorDashboard.getPatronagesAverage().get(Pair.of(Status.ACCEPTED, "GBP")));
		result.setAttribute("patronagesAverageProposedEUR", administratorDashboard.getPatronagesAverage().get(Pair.of(Status.PROPOSED, "EUR")));
		result.setAttribute("patronagesAverageProposedUSD", administratorDashboard.getPatronagesAverage().get(Pair.of(Status.PROPOSED, "USD")));
		result.setAttribute("patronagesAverageProposedGBP", administratorDashboard.getPatronagesAverage().get(Pair.of(Status.PROPOSED, "GBP")));
		result.setAttribute("patronagesAverageDeniedEUR", administratorDashboard.getPatronagesAverage().get(Pair.of(Status.DENIED, "EUR")));
		result.setAttribute("patronagesAverageDeniedUSD", administratorDashboard.getPatronagesAverage().get(Pair.of(Status.DENIED, "USD")));
		result.setAttribute("patronagesAverageDeniedGBP", administratorDashboard.getPatronagesAverage().get(Pair.of(Status.DENIED, "GBP")));
		//Deviation
		result.setAttribute("patronagesDeviationAcceptedEUR", administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.ACCEPTED, "EUR")));
		result.setAttribute("patronagesDeviationAcceptedUSD", administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.ACCEPTED, "USD")));
		result.setAttribute("patronagesDeviationAcceptedGBP", administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.ACCEPTED, "GBP")));
		result.setAttribute("patronagesDeviationProposedEUR", administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.PROPOSED, "EUR")));
		result.setAttribute("patronagesDeviationProposedUSD", administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.PROPOSED, "USD")));
		result.setAttribute("patronagesDeviationProposedGBP", administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.PROPOSED, "GBP")));
		result.setAttribute("patronagesDeviationDeniedEUR", administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.DENIED, "EUR")));
		result.setAttribute("patronagesDeviationDeniedUSD", administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.DENIED, "USD")));
		result.setAttribute("patronagesDeviationDeniedGBP", administratorDashboard.getPatronagesDeviation().get(Pair.of(Status.DENIED, "GBP")));
		//Max
		result.setAttribute("patronagesMaxAcceptedEUR", administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.ACCEPTED, "EUR")));
		result.setAttribute("patronagesMaxAcceptedUSD", administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.ACCEPTED, "USD")));
		result.setAttribute("patronagesMaxAcceptedGBP", administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.ACCEPTED, "GBP")));
		result.setAttribute("patronagesMaxProposedEUR", administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.PROPOSED, "EUR")));
		result.setAttribute("patronagesMaxProposedUSD", administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.PROPOSED, "USD")));
		result.setAttribute("patronagesMaxProposedGBP", administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.PROPOSED, "GBP")));
		result.setAttribute("patronagesMaxDeniedEUR", administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.DENIED, "EUR")));
		result.setAttribute("patronagesMaxDeniedUSD", administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.DENIED, "USD")));
		result.setAttribute("patronagesMaxDeniedGBP", administratorDashboard.getPatronagesMaximum().get(Pair.of(Status.DENIED, "GBP")));
		//Min
		result.setAttribute("patronagesMinAcceptedEUR", administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.ACCEPTED, "EUR")));
		result.setAttribute("patronagesMinAcceptedUSD", administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.ACCEPTED, "USD")));
		result.setAttribute("patronagesMinAcceptedGBP", administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.ACCEPTED, "GBP")));
		result.setAttribute("patronagesMinProposedEUR", administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.PROPOSED, "EUR")));
		result.setAttribute("patronagesMinProposedUSD", administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.PROPOSED, "USD")));
		result.setAttribute("patronagesMinProposedGBP", administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.PROPOSED, "GBP")));
		result.setAttribute("patronagesMinDeniedEUR", administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.DENIED, "EUR")));
		result.setAttribute("patronagesMinDeniedUSD", administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.DENIED, "USD")));
		result.setAttribute("patronagesMinDeniedGBP", administratorDashboard.getPatronagesMinimum().get(Pair.of(Status.DENIED, "GBP")));

	}
}
