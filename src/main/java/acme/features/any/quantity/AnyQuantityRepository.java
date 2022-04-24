package acme.features.any.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.quantity.Quantity;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyQuantityRepository extends AbstractRepository{

	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id = :toolkitId")
	Collection<Quantity> findAllQuantityByToolkitId(int toolkitId);
	
	@Query("SELECT q FROM Quantity q WHERE q.id = :id")
	Quantity findQuantityById(int id);
	
	@Query("SELECT c.defaultCurrency FROM Configuration c")
	String findDefaultCurrency();
}
