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

package acme.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.Patronage.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractListService;
import acme.repositories.PatronageRepository;
import acme.roles.Patron;

@Service
public class PatronageShowService implements AbstractListService<Authenticated, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronageRepository repository;

	// AbstractUpdateService<Authenticated, Provider> interface ---------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	
	
	public Collection<Patronage> findPatronagesByPatron(final Patron patron) {
		return this.repository.findPatronageByPatron(patron);
	}
	public Collection<Patronage> findPatronagesByPatronAndStatus(final Patron patron,final Status status) {
		return this.repository.findPatronageByPatronAndStatus(patron,status);
	}
	public Collection<Patronage> findPatronagesByStatus(final Status status) {
		return this.repository.findPatronageByStatus(status);
	}



	@Override
	public Collection<Patronage> findMany(Request<Patronage> request) {

		return this.repository.findAllPatronage();
	}



	@Override
	public void unbind(Request<Patronage> request, Patronage entity, Model model) {
		// TODO Auto-generated method stub
		
	}



}
