package acme.features.inventor.toolkitItem;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.entities.ToolkitItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolkitItemListService implements AbstractListService<Inventor, ToolkitItem> {


	@Autowired
	protected InventorToolkitItemRepository repository;

	// Interface 


	@Override
	public boolean authorise(final Request<ToolkitItem> request) {
		assert request != null;
		
		boolean result;
		int toolkitId;
		Toolkit toolkit;
		
		toolkitId = request.getModel().getInteger("toolkitId");
		toolkit = this.repository.findOneToolkitById(toolkitId);
		result = !toolkit.getDraftMode();
		
		return result;
	}

	@Override
	public Collection<ToolkitItem> findMany(final Request<ToolkitItem> request) {
		assert request != null;

		Collection<ToolkitItem> result;
		int toolkitId;
		
		toolkitId = request.getModel().getInteger("toolkitId");
		result = this.repository.findManyItemsByToolkitId(toolkitId);

		return result;
	}
	
	@Override
	public void unbind(final Request<ToolkitItem> request, final ToolkitItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "item.name", "item.code", "item.type","units");
	}

}
