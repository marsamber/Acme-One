package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Item.Type;
import acme.framework.repositories.AbstractRepository;
import acme.entities.Toolkit;

@Repository
public interface AnyItemRepository extends AbstractRepository {

	@Query("select i from Item i where i.id = :id")
	Item findById(int id);
	
	@Query("select i from Item i where i.type = :type")
	Collection<Item> findAllItemsByType(Type type);

	@Query("select t from Toolkit t where t.id = :toolkitId")
	Toolkit findOneToolkitById(int toolkitId);

	@Query("select ti.item from ToolkitItem ti where ti.toolkit.id=:toolkitId")
	Collection<Item> findManyItemsByToolkitId(int toolkitId);

}