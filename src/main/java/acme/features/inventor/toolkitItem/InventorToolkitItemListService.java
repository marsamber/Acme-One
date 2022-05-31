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
		
		boolean result = false;
		int masterId; 
		Toolkit toolkit;
		Collection<ToolkitItem> toolkitItems;
		int principalId;
		
		masterId = request.getModel().getInteger("toolkitId");
		toolkit = this.repository.findOneToolkitById(masterId);
		toolkitItems = this.repository.findManyItemsByToolkitId(masterId);

		principalId = request.getPrincipal().getActiveRoleId();
		if( toolkitItems == null || toolkitItems.isEmpty()) return true;
		
		for(final ToolkitItem toolkitItem: toolkitItems) {
			result = toolkitItem.getItem().getInventor().getId() == principalId;
			if(result && Boolean.TRUE.equals(toolkit.getDraftMode())) return true;
		}
		
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
	public void unbind(final Request<ToolkitItem> request, final Collection<ToolkitItem> entities, final Model model) {
		assert request != null;
		assert model != null;
		int toolkitId;

		toolkitId = request.getModel().getInteger("toolkitId");
		
		model.setAttribute("toolkitId", toolkitId);
		final Boolean draftMode = entities.iterator().next().getToolkit().getDraftMode();
		model.setAttribute("draftMode", draftMode);
	}
	
	@Override
	public void unbind(final Request<ToolkitItem> request, final ToolkitItem entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "item.name", "item.code", "item.type","units");

	}

}
