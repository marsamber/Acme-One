/*
 * AuthenticatedProviderCreateService.java
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

import acme.framework.roles.Administrator;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractService;

@Service
public class AdministratorShowService implements AbstractService<Authenticated, Administrator> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorRepository repository;


	/*
	public Collection<Patronage> findPatronageByStatus(Status status){
		return this.repository.findPatronageByStatus(status);
	}

	public int NumberOfPatronageByStatus(Status status){
		return this.repository.findPatronageByStatus(status).size();
	}
	*/

}
