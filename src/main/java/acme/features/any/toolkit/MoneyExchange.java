package acme.features.any.toolkit;

import acme.entities.currencyExchange.CurrencyExchange;
import acme.framework.datatypes.Money;

public class MoneyExchange {
	

	public static Money changeCurrency(final Money source, final String targetCurrency, final AnyToolkitRepository anyToolkitRepository) {
			
			Money result = new Money();
			
			if(source.getCurrency().equals(targetCurrency)) {
				result = source;
			}else {
				final CurrencyExchange currencyExchange = anyToolkitRepository.findCurrencyExchangeBySourceCurrency(source.getCurrency(),targetCurrency);
				
				result.setCurrency(targetCurrency);
				result.setAmount(source.getAmount()*currencyExchange.getRate());
				
			}
			
			return result;
	}
	
}
