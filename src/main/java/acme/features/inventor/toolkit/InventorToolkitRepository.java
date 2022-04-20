package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository{

	@Query("SELECT q.toolkit FROM Quantity q " + 
			"WHERE q.toolkit.inventor.userAccount.id = :inventorId " + 
			"AND q.toolkit.published = true GROUP BY q.toolkit")
	Collection<Toolkit> findToolkitsByInventorId(int inventorId);
	
	@Query("SELECT q.toolkit FROM Quantity q " + 
			"WHERE q.toolkit.inventor.userAccount.id = :inventorId " + 
			"AND q.item.id = :itemId GROUP BY q.toolkit")
	Collection<Toolkit> findToolkitsByInventorIdAndItemId(int inventorId, int itemId);
}
