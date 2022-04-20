
package acme.features.any.inventor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class AnyInventorShowService implements AbstractShowService<Any, Inventor> {

	@Autowired
	protected AnyInventorRepository repository;

	// Interface 


	@Override
	public boolean authorise(final Request<Inventor> request) {
		assert request != null;

		return true;
	}

	@Override
	public Inventor findOne(final Request<Inventor> request) {
		assert request != null;

		Inventor result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Inventor> request, final Inventor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "company", "statement", "moreInfo", "userAccount.username", "userAccount.enabled", "userAccount.identity.name", "userAccount.identity.surname", "userAccount.identity.email");
		model.setAttribute("readonly", true);
	}

}
