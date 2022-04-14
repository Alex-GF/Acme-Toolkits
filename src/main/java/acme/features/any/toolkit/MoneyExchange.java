package acme.features.any.toolkit;

import acme.entities.currencyExchange.RateExchange;
import acme.framework.datatypes.Money;

public class MoneyExchange {
	

	public static Money changeCurrency(final Money source, final String targetCurrency, final AnyToolkitRepository anyToolkitRepository) {
			
			Money result = new Money();
			
			if(source.getCurrency().equals(targetCurrency)) {
				result = source;
			}else {
				final RateExchange rateExchange = anyToolkitRepository.findRateExchangeBySourceCurrency(source.getCurrency(),targetCurrency);
				
				result.setCurrency(targetCurrency);
				result.setAmount(source.getAmount()*rateExchange.getRate());
				
			}
			
			return result;
	}
	
}
