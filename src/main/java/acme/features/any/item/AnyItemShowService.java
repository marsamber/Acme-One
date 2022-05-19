package acme.features.any.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyItemShowService implements AbstractShowService<Any, Item> {

	@Autowired
	protected AnyItemRepository repository;

	// Interface 

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		AuthenticatedMoneyExchangePerformService moneyExchange= new AuthenticatedMoneyExchangePerformService();
		
		request.unbind(entity, model,"name", "code", "technology", "description", "retailPrice", "link","type");
		
		Money money =entity.getRetailPrice();
		Money moneyEUR = moneyExchange.computeMoneyExchange(money, "EUR").getTarget();
		Money moneyUSD = moneyExchange.computeMoneyExchange(money, "USD").getTarget();
		Money moneyGBP = moneyExchange.computeMoneyExchange(money, "GBP").getTarget();
		
		model.setAttribute("retailPriceEUR", moneyEUR);
		model.setAttribute("retailPriceUSD", moneyUSD);
		model.setAttribute("retailPriceGBP", moneyGBP);
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
	}
	
}