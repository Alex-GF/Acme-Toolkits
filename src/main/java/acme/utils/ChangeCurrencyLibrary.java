package acme.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.components.ExchangeRate;
import acme.entities.currencyExchange.CurrencyExchange;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangeRepository;
import acme.forms.MoneyExchange;
import acme.framework.datatypes.Money;
import acme.framework.helpers.StringHelper;

@Service
public class ChangeCurrencyLibrary {
	
	@Autowired
	protected AuthenticatedMoneyExchangeRepository repository;

	public MoneyExchange computeMoneyExchange(final Money source, final String targetCurrency) {
		assert source != null;
		assert !StringHelper.isBlank(targetCurrency);

		MoneyExchange result;
		RestTemplate api;
		CurrencyExchange record;
		String sourceCurrency;
		Double sourceAmount, targetAmount, rate;
		Money target;

		try {
			api = new RestTemplate();

			sourceCurrency = source.getCurrency();
			sourceAmount = source.getAmount();
			
			final CurrencyExchange currencyExchange = this.repository.getCurrencyExchangeByCurrencies(sourceCurrency, targetCurrency);
			
			if(Instant.now().isAfter(currencyExchange.getDate().toInstant().plus(24, ChronoUnit.HOURS))) {
				final ExchangeRate apiObject = api.getForObject( //
					"https://api.exchangerate.host/latest?base={0}&symbols={1}", //
					ExchangeRate.class, //
					sourceCurrency, //
					targetCurrency //
				);
				assert apiObject != null;
				
				
				record = currencyExchange;
				currencyExchange.setDate(new Date());
				currencyExchange.setRate(apiObject.getRates().get(targetCurrency));

				this.repository.save(currencyExchange);
			} else {
				record = currencyExchange;
			}

			assert record != null;
			rate = record.getRate();
			targetAmount = rate * sourceAmount;

			target = new Money();
			target.setAmount(Double.parseDouble(String.format("%.2f", targetAmount)));
			target.setCurrency(targetCurrency);

			result = new MoneyExchange();
			result.setSource(source);
			result.setTargetCurrency(targetCurrency);
			result.setDate(record.getDate());
			result.setTarget(target);
		} catch (final Throwable oops) {
			result = null;
		}

		return result;
	}
}
