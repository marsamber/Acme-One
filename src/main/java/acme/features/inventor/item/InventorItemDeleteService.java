package acme.features.inventor.item;


import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorItemDeleteService implements AbstractDeleteService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository repository;


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int itemId;
		Item item;
		Inventor inventor;

		itemId = request.getModel().getInteger("id");
		item = this.repository.findById(itemId);
		inventor = item.getInventor();
		result = !item.isPublished() && request.isPrincipal(inventor);

		return result;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "type", "link","published");
	}
	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "type", "link","published");
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
		
		if (!errors.hasErrors()){
			final Collection<Toolkit> toolkits = this.repository.findAllToolkits();
			final Iterator<Toolkit> it = toolkits.iterator();
			while(it.hasNext() && !errors.hasErrors()){
				final Toolkit toolkit = it.next();
				final Collection<Item> itemToolkit = this.repository.findManyItemsByToolkitId(toolkit.getId());
				final Iterator<Item> itItemToolkit = itemToolkit.iterator();
				while(itItemToolkit.hasNext() && !errors.hasErrors()) { 
					errors.state(request, itItemToolkit.next().getId() != entity.getId(), "*", "inventor.item.form.error.code.itemToolkit");
				}
			}
		}
	}

	@Override
	public void delete(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
