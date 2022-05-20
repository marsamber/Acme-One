package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.SystemConfiguration;
import acme.entities.Toolkit;
import acme.entities.ToolkitItem;
import acme.features.antiSpam.SpamDetector;
import acme.features.antiSpam.SpamDetectorRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitUpdateService implements AbstractUpdateService<Inventor, Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected SpamDetectorRepository repositorySpam; 

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
		
		if(toolkitItems == null || toolkitItems.isEmpty()) return true;

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
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;

		this.repository.save(entity);
		
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "code", "description", "assemblyNotes", "link");
		
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Toolkit existing;

			existing = this.repository.findToolkitByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.toolkit.form.error.code.existingItem");
		}
		
		final SystemConfiguration systemConfiguration= this.repositorySpam.findTheSystemConfiguration();
        final SpamDetector spamDetector= new SpamDetector(systemConfiguration);

        errors.state(request, !spamDetector.detectSpam(entity.getAssemblyNotes()), "assemblyNotes", "inventor.toolkit.form.error.spam");
        
        errors.state(request, !spamDetector.detectSpam(entity.getTitle()), "title", "inventor.toolkit.form.error.spam");

        errors.state(request, !spamDetector.detectSpam(entity.getDescription()), "description", "inventor.toolkit.form.error.spam");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link");
		
	}

}
