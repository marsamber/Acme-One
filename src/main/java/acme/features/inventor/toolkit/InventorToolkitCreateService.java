
package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor, Toolkit> {

	@Autowired
	protected InventorToolkitRepository repository;


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		Money money;

		money = new Money();
		money.setAmount(0.);
		money.setCurrency("EUR");

		result = new Toolkit();
		result.setAssemblyNotes("");
		result.setCode("");
		result.setDescription("");
		result.setDraftMode(true);
		result.setLink("");
		result.setTitle("");
		result.setTotalPrice(money);

		return result;
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

	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link");

	}

	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);

	}

}
