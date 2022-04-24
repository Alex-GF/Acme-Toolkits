package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository{
	
	@Query("SELECT i FROM Item i")
	Collection<Item> findAllItem();
	
	@Query("SELECT i FROM Item i WHERE i.id = :itemId")
	Item getItemById(int itemId);
	
	@Query("SELECT q.item FROM Quantity q WHERE q.toolkit.id = :toolkitId")
	Collection<Item> findAllItemByToolkitId(int toolkitId);
	
	@Query("SELECT c.defaultCurrency FROM Configuration c")
	String findDefaultCurrency();
}
