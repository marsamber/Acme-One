package acme.features.inventor.item;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.SystemConfiguration;
import acme.features.antiSpam.SpamDetector;
import acme.features.antiSpam.SpamDetectorRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorItemUpdateService implements AbstractUpdateService<Inventor, Item> {
	
	@Autowired
	protected InventorItemRepository repository;
	
	@Autowired
	protected SpamDetectorRepository repositorySpam;


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int masterId;
		Item item;
		Inventor inventor;

		masterId = request.getModel().getInteger("id");
		item = this.repository.findById(masterId);
		inventor = item.getInventor();
		result = !item.isPublished() && request.isPrincipal(inventor);
		return result;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "type", "link");
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "type", "link");
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);

		return result;

	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Item existing;

			existing = this.repository.findComponentByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.item.form.error.code.existingItem");
		}
		
		if (!errors.hasErrors("price")) {
			final List<String> acceptedCurrencies = Arrays.asList(this.repository.getAcceptedCurrencies().split(","));
			
			errors.state(request, entity.getRetailPrice().getAmount() > 0, "price", "inventor.item.form.error.negative");
			
			errors.state(request, acceptedCurrencies.contains(entity.getRetailPrice().getCurrency()), "price", "inventor.item.form.error.invalidCurrency");
		}
		
		Boolean isSpam;
		SystemConfiguration systemConfiguration= this.repositorySpam.findTheSystemConfiguration();
		SpamDetector spamDetector= new SpamDetector(systemConfiguration);
		isSpam = (spamDetector.detectSpam(entity.getName()) ||spamDetector.detectSpam(entity.getDescription()));
		
		errors.state(request, !isSpam, "*", "inventor.item.form.error.spam");

	}

	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}