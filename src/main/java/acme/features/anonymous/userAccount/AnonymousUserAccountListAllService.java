package acme.features.anonymous.userAccount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousUserAccountListAllService implements AbstractListService<Anonymous, UserAccount>{

	@Autowired
	protected AnonymousUserAccountRepository repository;

	// Interface 


	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<UserAccount> findMany(final Request<UserAccount> request) {
		assert request != null;

		final List<UserAccount> result = new ArrayList<UserAccount>();

		result.addAll(this.repository.findAllUserAccountsInventors());
		result.addAll(this.repository.findAllUserAccountsPatrons());

		return result;
	}
	
	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "username", "identity");
	}
}
