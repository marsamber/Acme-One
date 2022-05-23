
package acme.features.inventor.toolkitItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Item.Type;
import acme.entities.Toolkit;
import acme.entities.ToolkitItem;
import acme.features.any.userAccount.AnyUserAccountRepository;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitItemCreateService implements AbstractCreateService<Inventor, ToolkitItem> {

	@Autowired
	protected InventorToolkitItemRepository	repository;

	@Autowired
	protected InventorItemRepository		itemRepository;

	@Autowired
	protected AnyUserAccountRepository		inventorRepository;


	@Override
	public boolean authorise(final Request<ToolkitItem> request) {
		assert request != null;

		boolean result = false;
		int masterId;
		Toolkit toolkit;
		Collection<ToolkitItem> toolkitItems;
		int principalId;

		masterId = request.getModel().getInteger("toolkitId");
		toolkit = this.repository.findOneToolkitById(masterId);
		toolkitItems = this.repository.findManyItemsByToolkitId(masterId);

		principalId = request.getPrincipal().getActiveRoleId();
		for (final ToolkitItem toolkitItem : toolkitItems) {
			result = toolkitItems != null && toolkitItem.getItem().getInventor().getId() == principalId;
			if (result && toolkit != null && Boolean.TRUE.equals(toolkit.getDraftMode()))
				return true;
		}

		if (toolkitItems == null || toolkitItems.isEmpty())
			return true;

		return result;
	}

	@Override
	public ToolkitItem instantiate(final Request<ToolkitItem> request) {
		assert request != null;

		ToolkitItem result;
		int masterId;
		Toolkit toolkit;
		Item item;

		masterId = request.getModel().getInteger("toolkitId");
		toolkit = this.repository.findOneToolkitById(masterId);

		item = new Item();

		result = new ToolkitItem();
		result.setToolkit(toolkit);
		result.setItem(item);
		result.setUnits(1);

		return result;
	}

	@Override
	public void bind(final Request<ToolkitItem> request, final ToolkitItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String code;
		Item item;
		
		code = request.getModel().getString("item.code");
		item = this.repository.findItemByCode(code);

		entity.setItem(item);
		
		request.bind(entity, errors, "item.code", "units");
	}

	@Override
	public void validate(final Request<ToolkitItem> request, final ToolkitItem entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Item existing;

		existing = this.repository.findItemByCode(entity.getItem().getCode());
		errors.state(request, existing != null, "item.code", "inventor.toolkit-item.form.error.code.notExistingItem");

		if (existing != null && existing.getType() == Type.TOOL) {
			errors.state(request, entity.getUnits() == 1, "units", "inventor.toolkit-item.form.error.units.more-units");

		}

	}

	@Override
	public void unbind(final Request<ToolkitItem> request, final ToolkitItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;


		final List<String> codes = new ArrayList<>();
		this.itemRepository.findAllItems().stream().forEach(x->codes.add(x.getCode()));
		
		request.unbind(entity, model, "item.code", "units");
		model.setAttribute("toolkitId", request.getModel().getAttribute("toolkitId"));
		model.setAttribute("draftMode", entity.getToolkit().getDraftMode());
		model.setAttribute("codes", codes);

	}

	@Override
	public void create(final Request<ToolkitItem> request, final ToolkitItem entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
