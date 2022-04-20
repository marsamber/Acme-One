
package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolkitListAllService implements AbstractListService<Any, Toolkit> {

	@Autowired
	protected AnyToolkitRepository repository;

	// Interface 


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;

		Collection<Toolkit> result;

		result = this.repository.findAllToolkits();

		for (final Toolkit toolkit : result) {
			final Collection<Item> items = this.repository.findItemsByToolkit(toolkit.getId());
			double price = 0;
			String currency = "";
			for (final Item item : items) {
				currency = item.getRetailPrice().getCurrency();
				price = price + item.getRetailPrice().getAmount();
			}
			final Money totalPrice = new Money();
			totalPrice.setAmount(price);
			totalPrice.setCurrency(currency);
			toolkit.setTotalPrice(totalPrice);
		}

		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link", "totalPrice");
	}

}
