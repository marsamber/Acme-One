package acme.features.inventor.toolkitItem;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Item.Type;
import acme.entities.Toolkit;
import acme.entities.ToolkitItem;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitItemCreateService implements AbstractCreateService<Inventor, ToolkitItem>{

	@Autowired
	protected InventorToolkitItemRepository repository;
	
	@Override
	public boolean authorise(final Request<ToolkitItem> request) {
		assert request != null;
		
		boolean result = false;
		int masterId; 
		Toolkit toolkit;
		Collection<ToolkitItem> toolkitItems;
		int principalId;
		
		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		toolkitItems = this.repository.findManyItemsByToolkitId(masterId);

		principalId = request.getPrincipal().getActiveRoleId();
		for(final ToolkitItem toolkitItem: toolkitItems) {
			result = toolkitItems != null && toolkitItem.getItem().getInventor().getId() == principalId;
			if(result && toolkit != null && toolkit.getDraftMode()) return true;
		}
		
		return result;
	}
	

	@Override
	public ToolkitItem instantiate(final Request<ToolkitItem> request) {
		assert request != null;
		
		ToolkitItem result;
		int masterId;
		Toolkit toolkit;
		Item item;
		Money money;
		
		masterId = request.getModel().getInteger("masterId");
		toolkit = this.repository.findOneToolkitById(masterId);
		
		money = new Money();
		money.setAmount(0.);
		money.setCurrency("EUR");
		
		item = new Item();
		item.setCode("");
		item.setDescription("");
		item.setLink("");
		item.setName("");
		item.setRetailPrice(money);
		item.setTechnology("");
		item.setType(Type.COMPONENT);
		
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
		
		request.bind(entity, errors, "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.link","item.type","units");
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
		
		request.unbind(entity, model, "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.link","item.type","units");
		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));
		model.setAttribute("draftMode", entity.getToolkit().getDraftMode());
		
	}


	

	@Override
	public void create(final Request<ToolkitItem> request, final ToolkitItem entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}
