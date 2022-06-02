package acme.features.inventor.patronageReport;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.Status;
import acme.entities.patronageReport.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {

	@Query("SELECT pr FROM PatronageReport pr WHERE pr.patronage.inventor.userAccount.id = :inventorId")
	Collection<PatronageReport> findPatronageReportsByInventorId(int inventorId);

	@Query("SELECT p FROM PatronageReport p WHERE p.id = :patronageReportId")
	PatronageReport getPatronageReportById(int patronageReportId);
	
	@Query("SELECT p FROM Patronage p WHERE p.inventor.userAccount.id = :inventorId")
	Collection<Patronage> findAllPatronagesByInventorId(int inventorId);

	@Query("SELECT  p.patronage FROM PatronageReport p ORDER BY p.creationMoment")
	List<Patronage> getPatronageByLastCreationMoment();

	@Query("SELECT p FROM Patronage p")
	List<Patronage> findAllPatronages();

	@Query("SELECT pr FROM PatronageReport pr WHERE pr.patronage.id = :patronageId")
	List<PatronageReport> findAllPatronageReportsById(int patronageId);
	
	@Query("SELECT p FROM Patronage p WHERE p.inventor.userAccount.id = :inventorId AND p.status = :status AND p.published = true")
	List<Patronage> findAllPatronagesByInventorIdAndStatus(int inventorId, Status status);

}