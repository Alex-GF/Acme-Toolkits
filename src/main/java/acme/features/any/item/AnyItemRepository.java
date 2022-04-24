package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository{
	
	@Query("select i from Item i where i.published = true")
	Collection<Item> findAllItem();
	
	@Query("select i from Item i where i.id = :itemId")
	Item getItemById(int itemId);
	
	@Query("select q.item from Quantity q where q.toolkit.id = :toolkitId")
	Collection<Item> findAllItemByToolkitId(int toolkitId);
	
	@Query("select c.defaultCurrency from Configuration c")
	String findDefaultCurrency();
}
