package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.quantity.Quantity;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorQuantityRepository extends AbstractRepository{
	
	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id = :toolkitId")
	Collection<Quantity> findAllQuantityByToolkitId(int toolkitId);
	
	@Query("SELECT q FROM Quantity q WHERE q.id = :id")
	Quantity findQuantityById(int id);
	
	@Query("SELECT q FROM Quantity q WHERE q.item.id = :itemId AND q.toolkit.id = :toolkitId")
	Quantity findQuantityByItemIdAndToolkitId(int itemId, int toolkitId);
}