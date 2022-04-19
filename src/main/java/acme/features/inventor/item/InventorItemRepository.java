package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository{
	
	@Query("SELECT i FROM Item i WHERE i.type = acme.entities.item.ItemType.COMPONENT AND i.inventor.userAccount.id = :inventorId")
	Collection<Item> findComponentsByInventorId(int inventorId);
	
	@Query("SELECT i FROM Item i WHERE i.type = acme.entities.item.ItemType.TOOL AND i.inventor.userAccount.id = :inventorId")
	Collection<Item> findToolsByInventorId(int inventorId);
}