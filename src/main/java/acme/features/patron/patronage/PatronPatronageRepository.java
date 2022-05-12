package acme.features.patron.patronage;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReport.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

    @Query("SELECT p FROM Patronage p WHERE p.patron.userAccount.id = :patronId")
    Collection<Patronage> findPatronagesByPatronId(int patronId);

    @Query("SELECT p FROM Patronage p WHERE p.id = :patronageId")
    Patronage getPatronageById(int patronageId);

    @Query("SELECT p.code FROM Patronage p")
	List<String> findAllCodes();

    @Query("SELECT p FROM Patron p WHERE p.userAccount.id = :userAccountId")
	Patron findPatronByUserAccountId(int userAccountId);

    @Query("SELECT c.acceptedCurrencies FROM Configuration c")
	String findAcceptedCurrencies();
    
    
    @Query("SELECT p FROM Patronage p WHERE p.code = :code")
	Patronage getPatronageByCode(String code);

    @Query("SELECT i FROM Inventor i")
	List<Inventor> getInventors();
    
    @Query("SELECT i FROM Inventor i WHERE i.id = :inventorId")
   	Inventor getInventorByInventorId(int inventorId);
    
    
    @Query("SELECT p From PatronageReport p WHERE p.patronage.id = :id")
	Collection<PatronageReport> findPatronageReportByPatronageId(int id);

}
