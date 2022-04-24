
package acme.features.any.userAccount;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class AnyUserAccountShowService implements AbstractShowService<Any, UserAccount> {

	@Autowired
	protected AnyUserAccountRepository repository;

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
		
		result.getRoles().forEach(r -> {;});

		return result;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "username", "identity.name", "identity.surname", "identity.email");
		model.setAttribute("readonly", true);
		final UserRole userRole = entity.getRoles().stream().filter(r->!"Administrator".equals(r.getAuthorityName())).collect(Collectors.toList()).get(0);
		if(userRole.getAuthorityName().equals("Inventor")) {
			final Inventor inventor = (Inventor) this.repository.findInventorByUserAccount(entity.getId()).toArray()[0];
			model.setAttribute("company", inventor.getCompany());
			model.setAttribute("statement", inventor.getStatement());
			model.setAttribute("moreInfo", inventor.getMoreInfo());
		}else {
			final Patron patron = (Patron) this.repository.findPatronByUserAccount(entity.getId()).toArray()[0];
			model.setAttribute("company", patron.getCompany());
			model.setAttribute("statement", patron.getStatement());
			model.setAttribute("moreInfo", patron.getMoreInfo());
		}
	}

}
