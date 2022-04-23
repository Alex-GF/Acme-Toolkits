package acme.features.administrator.dashboard;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Status;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface AdministratorDashboardRepository extends AbstractRepository{
	
	@Query("SELECT c.acceptedCurrencies FROM Configuration c")
	String getAcceptedCurrencies();
	
	@Query("SELECT COUNT(i) FROM Item i WHERE i.type = acme.entities.item.ItemType.COMPONENT")
	Integer numberOfComponents();
	
	@Query("SELECT i.technology, AVG(i.retailPrice.amount) FROM Item i WHERE i.retailPrice.currency = :currency AND i.type = acme.entities.item.ItemType.COMPONENT GROUP BY i.technology")
	Collection<Tuple> averageRetailPriceOfComponentsByTechnologyAndCurrency(String currency);
	
	@Query("SELECT i.technology, STDDEV(i.retailPrice.amount) FROM Item i WHERE i.retailPrice.currency = :currency AND i.type = acme.entities.item.ItemType.COMPONENT GROUP BY i.technology")
	Collection<Tuple> deviationRetailPriceOfComponentsByTechnologyAndCurrency(String currency);
	
	@Query("SELECT i.technology, MIN(i.retailPrice.amount) FROM Item i WHERE i.retailPrice.currency = :currency AND i.type = acme.entities.item.ItemType.COMPONENT GROUP BY i.technology")
	Collection<Tuple> minRetailPriceOfComponentsByTechnologyAndCurrency(String currency);
	
	@Query("SELECT i.technology, MAX(i.retailPrice.amount) FROM Item i WHERE i.retailPrice.currency = :currency AND i.type = acme.entities.item.ItemType.COMPONENT GROUP BY i.technology")
	Collection<Tuple> maxRetailPriceOfComponentsByTechnologyAndCurrency(String currency);
	
	@Query("SELECT COUNT(i) FROM Item i WHERE i.type = acme.entities.item.ItemType.TOOL")
	Integer numberOfTools();
	
	@Query("SELECT AVG(i.retailPrice.amount) FROM Item i WHERE (i.retailPrice.currency = :currency AND i.type = acme.entities.item.ItemType.TOOL)")
	Double averageRetailPriceOfToolsByCurrency(String currency);
	
	@Query("SELECT STDDEV(i.retailPrice.amount) FROM Item i WHERE i.retailPrice.currency = :currency AND i.type = acme.entities.item.ItemType.TOOL")
	Double deviationRetailPriceOfToolsByCurrency(String currency);
	
	@Query("SELECT MIN(i.retailPrice.amount) FROM Item i WHERE i.retailPrice.currency = :currency AND i.type = acme.entities.item.ItemType.TOOL ")
	Double minRetailPriceOfToolsByCurrency(String currency);
	
	@Query("SELECT MAX(i.retailPrice.amount) FROM Item i WHERE i.retailPrice.currency = :currency AND i.type = acme.entities.item.ItemType.TOOL")
	Double maxRetailPriceOfToolsByCurrency(String currency);
	
	@Query("SELECT COUNT(p) FROM Patronage p WHERE p.status = :status")
	Integer numberOfPatronagesByStatus(Status status);
	
	@Query("SELECT AVG(p.budget.amount) FROM Patronage p WHERE p.status = :status")
	Double averageBudgetOfPatronagesByStatus(Status status);
	
	@Query("SELECT STDDEV(p.budget.amount) FROM Patronage p WHERE p.status = :status")
	Double deviationBudgetOfPatronagesByStatus(Status status);
	
	@Query("SELECT MIN(p.budget.amount) FROM Patronage p WHERE p.status = :status")
	Double minBudgetOfPatronagesByStatus(Status status);
	
	@Query("SELECT MAX(p.budget.amount) FROM Patronage p WHERE p.status = :status")
	Double maxBudgetOfPatronagesByStatus(Status status);
	
}

