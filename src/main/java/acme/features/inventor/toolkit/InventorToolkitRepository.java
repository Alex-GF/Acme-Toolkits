package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorToolkitRepository extends AbstractRepository{

	@Query("SELECT t FROM Toolkit t WHERE t.inventor.userAccount.id = :inventorId")
	Collection<Toolkit> findToolkitsByInventorId(int inventorId);
}
