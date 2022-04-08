package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository {

	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findById(int id);
	
	@Query("select t from Toolkit t")
	Collection<Toolkit> findAllToolkits();

	@Query("select distinct ti.toolkit from ToolkitItem ti where ti.item.name LIKE %:item%")
	Collection<Toolkit> findAllToolkitsByItem(String item);
	
	@Query("select ti.item from ToolkitItem ti where ti.toolkit.id = :id")
	Collection<Item> findItemsByToolkit(int id);

}
