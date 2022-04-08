package acme.features.any.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository{
	
	@Query("SELECT i FROM Item i WHERE i.type = acme.entities.item.ItemType.COMPONENT")
	Collection<Item> findAllComponent();
	
	@Query("SELECT i FROM Item i WHERE i.type = acme.entities.item.ItemType.TOOL")
	Collection<Item> findAllTool();
	
	@Query("SELECT i FROM Item i WHERE i.id = :itemId")
	Item getItemById(int itemId);
	
}
