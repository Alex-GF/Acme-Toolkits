package acme.features.authenticated.moneyExchange;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.currencyExchange.CurrencyExchange;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMoneyExchangeRepository extends AbstractRepository{

	@Query("SELECT c.defaultCurrency FROM Configuration c")
	public String getDefaultCurrency();
	
	@Query("SELECT c.acceptedCurrencies FROM Configuration c")
	public String getAcceptedCurrencies();
	
	@Query("SELECT ce FROM CurrencyExchange ce WHERE ce.sourceCurrency = :sourceCurrency AND ce.targetCurrency = :targetCurrency")
	public CurrencyExchange getCurrencyExchangeByCurrencies(String sourceCurrency, String targetCurrency);
	
}
