package acme.features.inventor.toolkit;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository{

	@Query("SELECT t FROM Toolkit t " + 
		"WHERE t.inventor.userAccount.id = :inventorId ")
	Collection<Toolkit> findToolkitsByInventorId(int inventorId);
	
	@Query("SELECT q.toolkit FROM Quantity q " + 
			"WHERE q.toolkit.inventor.userAccount.id = :inventorId " + 
			"AND q.item.id = :itemId GROUP BY q.toolkit")
	Collection<Toolkit> findToolkitsByInventorIdAndItemId(int inventorId, int itemId);
	
	@Query("SELECT t FROM Toolkit t WHERE t.id = :toolkitId")
	Toolkit findToolkitById(int toolkitId);
	
	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id = :toolkitId")
	Collection<Quantity> findAllQuantityByToolkitId(int toolkitId);
	
	@Query("SELECT c.defaultCurrency FROM Configuration c")
	String findDefaultCurrency();
	
	@Query("SELECT t.code FROM Toolkit t")
	List<String> findAllToolkitCodes();
	
	@Query("SELECT c FROM Configuration c")
	public Configuration configuration();

	@Query("SELECT t FROM Toolkit t WHERE t.title = :title")
	Toolkit findToolkitByTitle(String title);
}
