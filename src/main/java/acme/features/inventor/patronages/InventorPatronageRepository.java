package acme.features.inventor.patronages;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

public interface InventorPatronageRepository extends AbstractRepository {

	@Query("select p from Patronages p where p.id = :id")
	Inventor findById(int id);
	
	@Query("select p from Patronage p where p.inventor.id = :id")
	Collection<Inventor> findAllInventorPatronagesByInventorId(int id);
	
}
