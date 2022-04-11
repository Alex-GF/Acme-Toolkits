package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository{

	@Query("SELECT q FROM Quantity q")
	Collection<Quantity> findAllQuantity();
	
	@Query("SELECT t FROM Toolkit t")
	Collection<Toolkit> findAllToolkit();
	
}
