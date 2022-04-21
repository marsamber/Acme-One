/*
 * AuthenticatedProviderRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.patron.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Patronage;
import acme.entities.Patronage.Status;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Patron;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {

	@Query("select p from Patronage p")
	Collection<Patronage> findAllPatronage();
	
	@Query("select p from Patronage p where p.status = :status")
	Collection<Patronage> findPatronageByStatus(Status status);
	
	@Query("select p from Patronage p where p.patron = :patron")
	Collection<Patronage> findPatronageByPatron(Patron patron);
	
	@Query("select p from Patronage p where p.patron = :patron and p.status = :status")
	Collection<Patronage> findPatronagesByPatronAndStatus(Patron patron, Status status);
	
	@Query("select p from Patron p where p.userAccount.id = :id")
	Patron findOnePatronByUserAccountId(int id);
	

}
