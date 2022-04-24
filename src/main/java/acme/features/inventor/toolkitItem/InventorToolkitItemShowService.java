package acme.features.inventor.toolkitItem;

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

		return true;
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
