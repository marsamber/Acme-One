
package acme.features.inventor.toolkitItem;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.ToolkitItem;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitItemUpdateService implements AbstractUpdateService<Inventor, ToolkitItem> {

	@Autowired
	protected InventorToolkitItemRepository repository;
	
	@Autowired
	protected InventorItemRepository itemRepository;


	@Override
	public boolean authorise(final Request<ToolkitItem> request) {
		assert request != null;

		final int id;
		int principalId;
		Collection<ToolkitItem> toolkitItems;
		boolean result = false;

		final int idToolkitItem = request.getModel().getInteger("id");
		final ToolkitItem tI = this.repository.findById(idToolkitItem);
		id = tI.getToolkit().getId();
		toolkitItems = this.repository.findManyItemsByToolkitId(id);

		principalId = request.getPrincipal().getActiveRoleId();
		for (final ToolkitItem toolkitItem : toolkitItems) {
			result = toolkitItems != null && toolkitItem.getItem().getInventor().getId() == principalId;
			if (result)
				return true;
		}

		return result;
	}

	@Override
	public ToolkitItem findOne(final Request<ToolkitItem> request) {
		assert request != null;

		ToolkitItem result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);

		return result;
	}

	@Override
	public void update(final Request<ToolkitItem> request, final ToolkitItem entity) {
		assert request != null;
		
		final Item item = entity.getItem();
		this.itemRepository.save(item);
		
		this.repository.save(entity);

	}

	@Override
	public void bind(final Request<ToolkitItem> request, final ToolkitItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.link", "item.type", "units");

	}

	@Override
	public void validate(final Request<ToolkitItem> request, final ToolkitItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		errors.state(request, entity.getItem().getCode().matches("^[A-Z]{3}-[0-9]{3}(-[A-Z])?$"), "item.code", "inventor.toolkit-item.form.error.code.invalid");
		
		if (!errors.hasErrors("item.code")) {
			Item existing;

			existing = this.repository.findItemByCode(entity.getItem().getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "item.code", "inventor.toolkit-item.form.error.code.existingItem");
		}
		
		if (!errors.hasErrors("item.retailPrice")) {
			final List<String> acceptedCurrencies = Arrays.asList(this.repository.getAcceptedCurrencies().split(","));
			
			errors.state(request, entity.getItem().getRetailPrice().getAmount() > 0, "item.retailPrice", "inventor.toolkit-item.form.error.negative");
			
			errors.state(request, acceptedCurrencies.contains(entity.getItem().getRetailPrice().getCurrency()), "item.retailPrice", "inventor.toolkit-item.form.error.invalidCurrency");
		}
	}

	@Override
	public void unbind(final Request<ToolkitItem> request, final ToolkitItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.link", "item.type", "units");

	}

}
