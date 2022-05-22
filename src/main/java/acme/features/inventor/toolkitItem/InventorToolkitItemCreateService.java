package acme.features.inventor.toolkitItem;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.entities.ToolkitItem;
import acme.features.any.userAccount.AnyUserAccountRepository;
import acme.features.inventor.item.InventorItemRepository;
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
	
	@Autowired
	protected InventorItemRepository itemRepository;
	
	@Autowired
	protected AnyUserAccountRepository inventorRepository;
	
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
		for(final ToolkitItem toolkitItem: toolkitItems) {
			result = toolkitItems != null && toolkitItem.getItem().getInventor().getId() == principalId;
			if(result && toolkit != null && Boolean.TRUE.equals(toolkit.getDraftMode())) return true;
		}
		
		if( toolkitItems == null || toolkitItems.isEmpty()) return true;
		
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
		
		masterId = request.getModel().getInteger("toolkitId");
		toolkit = this.repository.findOneToolkitById(masterId);
		
		money = new Money();
		money.setAmount(0.);
		money.setCurrency("EUR");
		
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
		
		request.bind(entity, errors, "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.link","item.type","units");
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
		
		request.unbind(entity, model, "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.link","item.type","units");
		model.setAttribute("toolkitId", request.getModel().getAttribute("toolkitId"));
		model.setAttribute("draftMode", entity.getToolkit().getDraftMode());
		
	}


	

	@Override
	public void create(final Request<ToolkitItem> request, final ToolkitItem entity) {
		assert request != null;
		assert entity != null;
		
		final Item item = entity.getItem();
		item.setInventor(this.inventorRepository.findInventorByUserAccount(request.getPrincipal().getAccountId()).iterator().next());
		this.itemRepository.save(item);
		
		this.repository.save(entity);
		
	}

}
