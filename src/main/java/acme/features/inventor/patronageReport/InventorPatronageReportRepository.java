package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReport.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository{
	
	@Query("SELECT pr FROM PatronageReport pr WHERE pr.patronage.inventor.userAccount.id = :inventorId")
	Collection<PatronageReport> findPatronageReportsByInventorId(int inventorId);

	@Query("SELECT p FROM PatronageReport p WHERE p.id = :patronageReportId")
	PatronageReport getPatronageReportById(int patronageReportId);

	@Query("SELECT TOP 1 p.patronage FROM PatronageReport p ORDER BY p.creationMoment")
	Patronage getPatronageByLastCreationMoment();
	
}