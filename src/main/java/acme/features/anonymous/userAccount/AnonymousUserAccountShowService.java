package acme.features.anonymous.userAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousUserAccountShowService implements AbstractShowService<Anonymous, UserAccount> {


	@Autowired
	protected AnonymousUserAccountRepository repository;

	// Interface 


	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;

		return true;
	}

	@Override
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;

		UserAccount result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findById(id);

		return result;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"identity", "username");
		model.setAttribute("readonly", true);
	}
	
}
