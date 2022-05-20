package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.entities.ToolkitItem;
import acme.features.inventor.toolkitItem.InventorToolkitItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorToolkitDeleteService implements AbstractDeleteService<Inventor, Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected InventorToolkitItemRepository toolkitItemRepository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		int id;
		int principalId;
		Collection<ToolkitItem> toolkitItems;
		boolean result = false;

		id = request.getModel().getInteger("id");
		toolkitItems = this.repository.findItemsByToolkit(id);

		principalId = request.getPrincipal().getActiveRoleId();
		for(final ToolkitItem toolkitItem: toolkitItems) {
			result = toolkitItems != null && toolkitItem.getItem().getInventor().getId() == principalId;
			if(result) return true;
		}
		
		if( toolkitItems == null || toolkitItems.isEmpty()) return true;

		return result;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);

		final Collection<ToolkitItem> toolkitItems = this.repository.findItemsByToolkit(result.getId());
		double price = 0;
		String currency = "";
		for (final ToolkitItem toolkitItem : toolkitItems) {
			currency = toolkitItem.getItem().getRetailPrice().getCurrency();
			price = price + toolkitItem.getItem().getRetailPrice().getAmount()*toolkitItem.getUnits();
		}
		final Money totalPrice = new Money();
		totalPrice.setAmount(price);
		totalPrice.setCurrency(currency);
		result.setTotalPrice(totalPrice);

		return result;
	}

	@Override
	public void delete(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		
		final Collection<ToolkitItem> toolkitItems = this.repository.findItemsByToolkit(entity.getId());
		toolkitItems.stream().forEach(x->this.toolkitItemRepository.delete(x));
		
		this.repository.delete(entity);
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "code", "description", "assemblyNotes", "link", "totalPrice");
	}
	
	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link", "totalPrice");
		
	}

}
