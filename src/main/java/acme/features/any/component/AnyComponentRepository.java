package acme.features.any.component;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyComponentRepository extends AbstractRepository{
	
	@Query("SELECT i FROM Item i WHERE i.type = acme.entities.item.ItemType.COMPONENT")
	Collection<Item> findAllComponent();
	
	@Query("SELECT i FROM Item i WHERE i.id = :itemId")
	Item getItemById(int itemId);
	
}
