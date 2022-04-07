package acme.features.any.inventor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface AnyInventorRepository extends AbstractRepository {

	@Query("select i from Inventor i where i.id = :id")
	Inventor findById(int id);
	
	@Query("select i from Inventor i where i.userAccount.enabled = 1")
	Collection<Inventor> findAllInventors();


}
