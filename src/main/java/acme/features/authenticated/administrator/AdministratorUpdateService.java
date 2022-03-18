/*
 * AuthenticatedProviderUpdateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Administrator;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorUpdateService implements AbstractUpdateService<Authenticated, Administrator> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorRepository repository;

	// AbstractUpdateService<Authenticated, Provider> interface ---------------


	@Override
	public boolean authorise(final Request<Administrator> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Administrator> request, final Administrator entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "company", "sector");
	}

	@Override
	public void unbind(final Request<Administrator> request, final Administrator entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "company", "sector");
	}

	@Override
	public Administrator findOne(final Request<Administrator> request) {
		assert request != null;

		Administrator result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneProviderByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void validate(final Request<Administrator> request, final Administrator entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Administrator> request, final Administrator entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Administrator> request, final Response<Administrator> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
