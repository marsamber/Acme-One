package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Item.Type;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorComponentListMineService implements AbstractListService<Inventor, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;

	// AbstractListService<Inventor, Item> interface ---------------------------


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;

		Collection<Item> result;
		Principal principal;

		principal = request.getPrincipal();
		
		
		final int inventorId = principal.getActiveRoleId();
		final Type type = Type.COMPONENT;
		
		
		result = this.repository.findItemsByInventorIdAndType(inventorId,type);

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "type", "published", "link");
		
		final AuthenticatedMoneyExchangePerformService moneyExchange= new AuthenticatedMoneyExchangePerformService();
		
		final Money money =entity.getRetailPrice();
		final Money moneyEUR = moneyExchange.computeMoneyExchange(money, "EUR").getTarget();
		final Money moneyUSD = moneyExchange.computeMoneyExchange(money, "USD").getTarget();
		final Money moneyGBP = moneyExchange.computeMoneyExchange(money, "GBP").getTarget();
		
		model.setAttribute("retailPriceEUR", moneyEUR);
		model.setAttribute("retailPriceUSD", moneyUSD);
		model.setAttribute("retailPriceGBP", moneyGBP);
	}

}