
package acme.features.any.toolkitItem;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Toolkit;
import acme.entities.ToolkitItem;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitItemRepository extends AbstractRepository {

	@Query("select t from Toolkit t where t.id = :toolkitId")
	Toolkit findOneToolkitById(int toolkitId);

	@Query("select ti from ToolkitItem ti where id = :id")
	ToolkitItem findById(int id);

	@Query("select ti from ToolkitItem ti where ti.toolkit.id=:toolkitId")
	Collection<ToolkitItem> findManyItemsByToolkitId(int toolkitId);

}
