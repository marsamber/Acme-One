package acme.features.inventor.toolkitItem;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ToolkitItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitItemShowService implements AbstractShowService<Inventor, ToolkitItem> {


	@Autowired
	protected InventorToolkitItemRepository repository;

	// Interface 


	@Override
	public boolean authorise(final Request<ToolkitItem> request) {
		assert request != null;
		
		int id;
		int principalId;
		Collection<ToolkitItem> toolkitItems;
		boolean result = false;

		id = request.getModel().getInteger("id");
		toolkitItems = this.repository.findManyItemsByToolkitId(id);

		principalId = request.getPrincipal().getActiveRoleId();
		for(final ToolkitItem toolkitItem: toolkitItems) {
			result = toolkitItems != null && toolkitItem.getItem().getInventor().getId() == principalId;
			if(result) return true;
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
	public void unbind(final Request<ToolkitItem> request, final ToolkitItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.link","item.type","units");
		model.setAttribute("readonly", true);
	}
	
}
