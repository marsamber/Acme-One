package acme.features.anonymous.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousItemListService implements AbstractListService<Anonymous, Item> {


	@Autowired
	protected AnonymousItemRepository repository;

	// Interface 


	@Override
	public boolean authorise(final Request<Item> request) {
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
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;

		Collection<Item> result;
		int toolkitId;
		
		toolkitId = request.getModel().getInteger("toolkitId");
		result = this.repository.findManyItemsByToolkitId(toolkitId);

		return result;
	}
	
	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "type");
	}

}
