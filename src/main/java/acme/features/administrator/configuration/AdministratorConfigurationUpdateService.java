package acme.features.administrator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;
import acme.entities.configuration;


@Service

public class AdministratorConfigurationUpdateService implements AbstractUpdateService<Administrator, Configuration> {
    
    // Internal state ---------------------------------------------------------

    @Autowired
    protected AdministratorConfigurationRepository repository;

    // AbstractUpdateService<Authenticated, Configuration> interface ---------------

    @Override
    public boolean authorise(final Request<Configuration> request) {
        assert request != null;

        boolean result;

        result = request.getPrincipal().hasRole(Administrator.class);

        return result;
    }

    @Override
    public void bind(final Request<Configuration> request, final Configuration entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

        request.bind(entity, errors, "defaultCurrency", "acceptedCurrencies", "strongSpamWords", "strongSpamThreshold",
                "weakSpamWords", "weakSpamThreshold");
    }

    @Override
    public void unbind(final Request<Configuration> request, final Configuration entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "defaultCurrency", "acceptedCurrencies", "strongSpamWords", "strongSpamThreshold",
                "weakSpamWords", "weakSpamThreshold");
    }

    @Override
    public Configuration findOne(final Request<Configuration> request) {
        assert request != null;

        Configuration result;

        result = this.repository.getGeneralConfiguration();

        return result;
    }

    @Override
    public void validate(final Request<Configuration> request, final Configuration entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;
    }

    @Override
    public void update(final Request<Configuration> request, final Configuration entity) {
        assert request != null;
        assert entity != null;

        this.repository.save(entity);
    }

    @Override
    public void onSuccess(final Request<Configuration> request, final Response<Configuration> response) {
        assert request != null;
        assert response != null;

        if (request.isMethod(HttpMethod.POST)) {
            PrincipalHelper.handleUpdate();
        }
    }
}
