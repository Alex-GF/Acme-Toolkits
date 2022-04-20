package acme.features.administrator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorConfigurationShowService implements AbstractShowService<Administrator,Configuration>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorConfigurationRepository administratorConfigurationRepository;


	@Override
	public boolean authorise(final Request<Configuration> request) {
		assert request != null;
		boolean result;

		result = request.getPrincipal().hasRole(Administrator.class);

		return result;
	}

	@Override
	public Configuration findOne(final Request<Configuration> request) {
		assert request != null;

		Configuration result;

		result = this.administratorConfigurationRepository.getGeneralConfiguration();

		return result;
	}

	@Override
	public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "defaultCurrency", "acceptedCurrencies", "strongSpamWords", "strongSpamThreshold", "weakSpamWords", "weakSpamThreshold");
	}

}
