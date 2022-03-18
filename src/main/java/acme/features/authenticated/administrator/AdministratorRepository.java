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

package acme.features.authenticated.administrator;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Patronage;
import acme.entities.Patronage.Status;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.framework.roles.Administrator;

@Repository
public interface AdministratorRepository extends AbstractRepository {

	@Query("select a from Administrator a where a.userAccount.id = :id")
	Administrator findOneProviderByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);
	
	@Query("select p from Patronage p")
	Collection<Patronage> findAllPatronage();
	
	@Query("select p from Patronage p and p.status = :status")
	Collection<Patronage> findPatronageByStatus(Status status);

}
