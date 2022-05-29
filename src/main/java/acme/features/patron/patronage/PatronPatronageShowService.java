package acme.features.patron.patronage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
import acme.roles.Patron;
import acme.roles.RolesRepository;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage> {

	@Autowired
	protected PatronPatronageRepository repo;
	
	@Autowired
	protected RolesRepository roleRepository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Patronage p = this.repo.findById(id);

		return p != null && p.getPatron().getId() == request.getPrincipal().getActiveRoleId();
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		
		final int patronageId = request.getModel().getInteger("id");
		return this.repo.findById(patronageId);
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final List<String> usernames = new ArrayList<>();
		this.roleRepository.findAllInventors().stream().forEach(x -> usernames.add(x.getUserAccount().getUsername()));
		model.setAttribute("allInventors", usernames);
		final Inventor inventor = entity.getInventor();
		
		request.unbind(entity, model, "status", "code",
			"legalStuff", "budget", "link", "createdAt",
			"startedAt", "finishedAt", "patron", "inventor", "published");
		model.setAttribute("company", inventor.getCompany());
		model.setAttribute("statement", inventor.getStatement());
		model.setAttribute("inventor.username", inventor.getUserAccount().getUsername());

		final AuthenticatedMoneyExchangePerformService moneyExchange= new AuthenticatedMoneyExchangePerformService();
		
		final Money money =entity.getBudget();
		final Money moneyEUR = moneyExchange.computeMoneyExchange(money, "EUR").getTarget();
		final Money moneyUSD = moneyExchange.computeMoneyExchange(money, "USD").getTarget();
		final Money moneyGBP = moneyExchange.computeMoneyExchange(money, "GBP").getTarget();
		
		model.setAttribute("budgetEUR", moneyEUR);
		model.setAttribute("budgetUSD", moneyUSD);
		model.setAttribute("budgetGBP", moneyGBP);
		model.setAttribute("inventorLink", inventor.getMoreInfo());
		
	}
	
	
	
}
