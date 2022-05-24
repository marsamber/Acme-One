
package acme.features.inventor.patronage;

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

@Service
public class InventorPatronageShowService implements AbstractShowService<Inventor, Patronage> {

	@Autowired
	protected InventorPatronageRepository repo;


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		final Patronage p = this.repo.findById(id);
		//return true;
		return p != null && p.getInventor().getId() == request.getPrincipal().getActiveRoleId();
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
		assert request != null;
		assert request != null;

		final Patron patron = entity.getPatron();

		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "link", "createdAt", "startedAt", "finishedAt", "patron", "inventor");
		
		model.setAttribute("company", patron.getCompany());
		model.setAttribute("statement", patron.getStatement());
		model.setAttribute("username", patron.getUserAccount().getUsername());
		
		
		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();

		final Money money = entity.getBudget();
		final Money moneyEUR = moneyExchange.computeMoneyExchange(money, "EUR").getTarget();
		final Money moneyUSD = moneyExchange.computeMoneyExchange(money, "USD").getTarget();
		final Money moneyGBP = moneyExchange.computeMoneyExchange(money, "GBP").getTarget();

		model.setAttribute("budgetEUR", moneyEUR);
		model.setAttribute("budgetUSD", moneyUSD);
		model.setAttribute("budgetGBP", moneyGBP);

		model.setAttribute("patronLink", patron.getMoreInfo());

	}

}
