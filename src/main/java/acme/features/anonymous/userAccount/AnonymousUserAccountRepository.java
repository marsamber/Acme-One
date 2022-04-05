package acme.features.anonymous.userAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousUserAccountRepository extends AbstractRepository {

	@Query("select i from UserAccount i where i.id = :id")
	UserAccount findById(int id);
	
	@Query("select ua from UserAccount ua join Patron p")
	Collection<UserAccount> findAllUserAccountsPatrons();
	
	@Query("select ua from UserAccount ua join Inventor p")
	Collection<UserAccount> findAllUserAccountsInventors();

}
