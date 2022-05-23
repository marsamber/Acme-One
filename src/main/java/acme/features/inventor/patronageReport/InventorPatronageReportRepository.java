package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {

	@Query("select pr from PatronageReport pr where pr.patronage.inventor.id = :id")
	Collection<PatronageReport> findManyPatronageReportByInventorId(int id);
	
	@Query("select pr from PatronageReport pr where pr.id = :id")
	PatronageReport findOnePatronageReportById(int id);
	
	@Query("select pr from PatronageReport pr where pr.patronage.id = :id")
	Collection<PatronageReport> findManyPatronageReportByPatronageId(int id);
}