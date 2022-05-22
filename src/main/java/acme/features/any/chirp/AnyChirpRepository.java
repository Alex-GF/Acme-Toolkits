package acme.features.any.chirp;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chirp.Chirp;
import acme.entities.configuration.Configuration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyChirpRepository extends AbstractRepository{
	
	
	@Query("select c from Chirp c where c.creationMoment > :deadline")
	Collection<Chirp> findRecentChirps(Date deadline);

	@Query("select c from Chirp c where c.id = :id")
	Chirp getChripById(int id);

	@Query("SELECT c FROM Configuration c")
	public Configuration configuration();
	
}
