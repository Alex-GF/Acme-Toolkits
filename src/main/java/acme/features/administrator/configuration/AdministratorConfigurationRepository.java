package acme.features.administrator.configuration;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configuration.Configuration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorConfigurationRepository extends AbstractRepository{
	
	@Query("SELECT c FROM Configuration c")
	Configuration getGeneralConfiguration();
	
}
