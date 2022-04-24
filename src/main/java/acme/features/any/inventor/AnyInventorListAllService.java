package acme.features.any.inventor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class AnyInventorListAllService implements AbstractListService<Any, Inventor> {


	@Autowired
	protected AnyInventorRepository repository;

	// Interface 


	@Override
	public boolean authorise(final Request<Inventor> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Inventor> findMany(final Request<Inventor> request) {
		assert request != null;

		Collection<Inventor> result;

		result = this.repository.findAllInventors();

		return result;
	}
	
	@Override
	public void unbind(final Request<Inventor> request, final Inventor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userAccount.identity.name", "userAccount.identity.surname", "userAccount.identity.email");
	}

}
