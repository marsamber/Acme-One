package acme.features.antiSpam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SpamDetectorRepository extends AbstractRepository{
	
	@Query("SELECT sc FROM SystemConfiguration sc")
	SystemConfiguration findTheSystemConfiguration();
}
