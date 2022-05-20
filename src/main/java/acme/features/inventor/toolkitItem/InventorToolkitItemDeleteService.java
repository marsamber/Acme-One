
package acme.features.inventor.toolkitItem;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ToolkitItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorToolkitItemDeleteService implements AbstractDeleteService<Inventor, ToolkitItem> {

	@Autowired
	protected InventorToolkitItemRepository repository;


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
	public void delete(final Request<ToolkitItem> request, final ToolkitItem entity) {
		assert request != null;

		this.repository.delete(entity);

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

	}

	@Override
	public void unbind(final Request<ToolkitItem> request, final ToolkitItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.link", "item.type", "units");

	}

}
