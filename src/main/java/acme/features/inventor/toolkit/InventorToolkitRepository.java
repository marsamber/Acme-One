
package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {

	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findById(int id);

	@Query("select distinct ti.toolkit from ToolkitItem ti where ti.item.inventor.id=:inventorId")
	Collection<Toolkit> findManyByInventorId(int inventorId);

	@Query("select ti.item from ToolkitItem ti where ti.toolkit.id = :id")
	Collection<Item> findItemsByToolkit(int id);

}
