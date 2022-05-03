package acme.features.inventor.item;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorItemRepository extends AbstractRepository{
	
	@Query("SELECT i FROM Item i WHERE i.inventor.userAccount.id = :inventorId")
	Collection<Item> findItemsByInventorId(int inventorId);
	
	@Query("SELECT i FROM Item i WHERE i.inventor.userAccount.id = :inventorId AND i.id = :itemId")
	Item findItemByInventorAndItemId(int inventorId, int itemId);
	
	@Query("SELECT c.defaultCurrency FROM Configuration c")
	String findDefaultCurrency();
	
	@Query("SELECT i FROM Inventor i WHERE i.userAccount.id = :userAccountId")
	Inventor findInventorByUserAccountId(int userAccountId);
	
	@Query("SELECT i.code FROM Item i")
	List<String> findAllCodes();
	
	@Query("SELECT i FROM Item i WHERE i.id = :itemId")
	Item findItemByItemId(int itemId);
	
	@Query("SELECT c.acceptedCurrencies FROM Configuration c")
	String findAcceptedCurrencies();
	
	
}