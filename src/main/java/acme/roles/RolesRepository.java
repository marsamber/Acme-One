package acme.roles;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface RolesRepository extends AbstractRepository {
	
	@Query("select p from Patron p where p.id = :id")
	Patron findPatronById(int id);
	
	@Query("select i from Inventor i")
	Collection<Inventor> findAllInventors();
	
	@Query("select i from Inventor i where i.userAccount.username = :username")
	Inventor findInventorByUsername(String username);
	

}
