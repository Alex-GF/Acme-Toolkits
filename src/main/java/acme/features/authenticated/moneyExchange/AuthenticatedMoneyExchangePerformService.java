package acme.features.authenticated.moneyExchange;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractPerformService;
import acme.utils.ChangeCurrencyLibrary;

@Service
public class AuthenticatedMoneyExchangePerformService implements AbstractPerformService<Authenticated,MoneyExchange>{

	@Autowired
	protected AuthenticatedMoneyExchangeRepository authenticatedCurrencyExchangeRepository;
	
	@Autowired
	protected ChangeCurrencyLibrary changeLibrary;
	
	@Override
	public boolean authorise(final Request<MoneyExchange> request) {
		assert request != null;
		
		return request.getPrincipal().isAuthenticated();
	}

	@Override  
	public void bind(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "source", "targetCurrency", "date", "target");

	}

	@Override
	public void unbind(final Request<MoneyExchange> request, final MoneyExchange entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final String defaultCurrency = this.authenticatedCurrencyExchangeRepository.getDefaultCurrency();
		final String acceptedCurrencies = this.authenticatedCurrencyExchangeRepository.getAcceptedCurrencies();
		
		request.unbind(entity, model, "source", "targetCurrency", "date", "target");
		
		model.setAttribute("defaultCurrency", defaultCurrency);
		model.setAttribute("acceptedCurrencies", acceptedCurrencies);
	}

	@Override
	public MoneyExchange instantiate(final Request<MoneyExchange> request) {
		assert request != null;

		return new MoneyExchange();
	}

	@Override
	public void validate(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void perform(final Request<MoneyExchange> request, final MoneyExchange entity, final Errors errors) {
		assert request != null;
		assert entity != null;

		Money source, target;
		String targetCurrency;
		Date date;
		MoneyExchange exchange;

		source = request.getModel().getAttribute("source", Money.class);
		targetCurrency = request.getModel().getAttribute("targetCurrency", String.class);
		exchange = this.changeLibrary.computeMoneyExchange(source, targetCurrency);
		errors.state(request, exchange != null, "*", "authenticated.money-exchange.form.label.api-error");
		if (exchange == null) {
			entity.setTarget(null);
			entity.setDate(null);
		} else {
			target = exchange.getTarget();
			entity.setTarget(target);
			date = exchange.getDate();
			entity.setDate(date);
		}
	}
	

	// Ancillary methods ------------------------------------------------------

		

}
