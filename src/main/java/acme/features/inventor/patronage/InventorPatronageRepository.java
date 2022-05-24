package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageRepository extends AbstractRepository {

	@Query("select p from Patronage p where p.id = :id")
	Patronage findById(int id);
	
	@Query("select p from Patronage p where p.inventor.id = :id")
	Collection<Patronage> findAllInventorPatronagesByInventorId(int id);
	
}
