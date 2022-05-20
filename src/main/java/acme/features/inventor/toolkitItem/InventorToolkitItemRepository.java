
package acme.features.inventor.toolkitItem;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.entities.ToolkitItem;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitItemRepository extends AbstractRepository {

	@Query("select t from Toolkit t where t.id = :toolkitId")
	Toolkit findOneToolkitById(int toolkitId);

	@Query("select ti from ToolkitItem ti where id = :id")
	ToolkitItem findById(int id);
	
	@Query("SELECT ti.item FROM ToolkitItem ti WHERE ti.item.code = :code")
	Item findItemByCode(String code);
	
	@Query("SELECT acceptedCurrencies FROM SystemConfiguration")
	String getAcceptedCurrencies();

	@Query("select ti from ToolkitItem ti where ti.toolkit.id=:toolkitId")
	Collection<ToolkitItem> findManyItemsByToolkitId(int toolkitId);

}
