package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorComponentRepository extends AbstractRepository{
	
	@Query("SELECT q.item FROM Quantity q WHERE q.item.type = acme.entities.item.ItemType.COMPONENT AND q.inventor.userAccount.id = :inventorId")
	Collection<Item> findComponentsByInventorId(int inventorId);
}