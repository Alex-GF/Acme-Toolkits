package acme.features.administrator.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

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
        if (!errors.hasErrors("defaultCurrency")) {
            boolean acceptedCurrency;

            acceptedCurrency = entity.getAcceptedCurrencies().contains(entity.getDefaultCurrency());

            errors.state(request, acceptedCurrency, "defaultCurrency",
                    "administrator.configuration.form.error.acceptedCurrency");
        }

        if (!errors.hasErrors("acceptedCurrencies")) {
            boolean uniqueAcceptedCurrencies = true;
            boolean correctCurrenciesPattern = true;
            final List<String> currencies = new ArrayList<>();

            for (final String currency : entity.getAcceptedCurrencies().split(",")) {
                currencies.add(currency.trim());
                if(!(currency.trim().length() == 3 && currency.trim().toUpperCase().equals(currency.trim()))) {
                	correctCurrenciesPattern = false;
                }
            }
            for (final String currency : entity.getAcceptedCurrencies().split(",")) {
                if (Collections.frequency(currencies, currency.trim()) != 1) {
                    uniqueAcceptedCurrencies = false;
                }
            }

            errors.state(request, uniqueAcceptedCurrencies, "acceptedCurrencies",
                    "administrator.configuration.form.error.uniqueAcceptedCurrencies");
            
            errors.state(request, correctCurrenciesPattern, "acceptedCurrencies",
                "administrator.configuration.form.error.correctCurrenciesPattern");
        }

        if (!errors.hasErrors("weakSpamWords")) {
            boolean differentWeakAndStrongSpamWords = true;

            final List<String> weakWords = new ArrayList<>();
            final List<String> strongWords = new ArrayList<>();

            for (final String weakWord : entity.getWeakSpamWords().split(",")) {
                weakWords.add(weakWord.toLowerCase().trim());
            }
            for (final String strongWord : entity.getStrongSpamWords().split(",")) {
                strongWords.add(strongWord.toLowerCase().trim());
            }
            for (final String weakWord : weakWords) {
                if (strongWords.contains(weakWord))
                    differentWeakAndStrongSpamWords = false;
            }

            errors.state(request, differentWeakAndStrongSpamWords, "weakSpamWords",
                    "administrator.configuration.form.error.differentWeakAndStrongSpamWords");

            boolean uniqueWeakSpamWords = true;

            for (final String weakWord : entity.getWeakSpamWords().split(",")) {
                if (Collections.frequency(weakWords, weakWord.toLowerCase().trim()) != 1) {
                    uniqueWeakSpamWords = false;
                }
            }

            errors.state(request, uniqueWeakSpamWords, "weakSpamWords",
                    "administrator.configuration.form.error.uniqueWeakSpamWords");
        }

        if (!errors.hasErrors("strongSpamWords")) {
            boolean uniqueStrongSpamWords = true;
            final List<String> strongWords = new ArrayList<>();

            for (final String strongWord : entity.getStrongSpamWords().split(",")) {
                strongWords.add(strongWord.toLowerCase().trim());
            }

            for (final String strongWord : entity.getStrongSpamWords().split(",")) {
                if (Collections.frequency(strongWords, strongWord.toLowerCase().trim()) != 1) {
                    uniqueStrongSpamWords = false;
                }
            }

            errors.state(request, uniqueStrongSpamWords, "strongSpamWords",
                    "administrator.configuration.form.error.uniqueStrongSpamWords");
        }

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
