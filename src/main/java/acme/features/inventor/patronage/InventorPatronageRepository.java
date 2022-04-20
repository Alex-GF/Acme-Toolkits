package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageRepository extends AbstractRepository{

	@Query("SELECT p FROM Patronage p WHERE p.inventor.userAccount.id = :inventorId")
	Collection<Patronage> findPatronagesByInventorId(int inventorId);
	
	@Query("SELECT p FROM Patronage p WHERE p.id = :patronageId")
	Patronage getPatronageById(int patronageId);
	

}
