
package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.entities.ToolkitItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolkitListMineService implements AbstractListService<Inventor, Toolkit> {

	@Autowired
	protected InventorToolkitRepository repository;

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
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findManyByInventorId(principal.getActiveRoleId());

		for (final Toolkit toolkit : result) {
			final Collection<ToolkitItem> toolkitItems = this.repository.findItemsByToolkit(toolkit.getId());
			double price = 0;
			String currency = "";
			for (final ToolkitItem toolkitItem : toolkitItems) {
				currency = toolkitItem.getItem().getRetailPrice().getCurrency();
				price = price + toolkitItem.getItem().getRetailPrice().getAmount()*toolkitItem.getUnits();
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
