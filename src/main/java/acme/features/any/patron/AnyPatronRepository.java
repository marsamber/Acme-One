package acme.features.any.patron;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Patron;

@Repository
public interface AnyPatronRepository extends AbstractRepository {

	@Query("select p from Patron p where p.id = :id")
	Patron findById(int id);
	
	@Query("select p from Patron p where p.userAccount.enabled = 1")
	Collection<Patron> findAllPatrons();


}
