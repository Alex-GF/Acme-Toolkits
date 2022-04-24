package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.currencyExchange.CurrencyExchange;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository{

	@Query("SELECT q FROM Quantity q WHERE q.toolkit.published = true")
	Collection<Quantity> findAllQuantity();
	
	@Query("SELECT DISTINCT(q.toolkit) FROM Quantity q WHERE q.toolkit.published = true")
	Collection<Toolkit> findAllToolkit();
	
	@Query("SELECT c FROM CurrencyExchange c WHERE c.sourceCurrency = :sourceCurrency and c.targetCurrency = :targetCurrency")
	CurrencyExchange findCurrencyExchangeBySourceCurrency(String sourceCurrency, String targetCurrency);
	
	@Query("SELECT q.toolkit FROM Quantity q WHERE q.toolkit.id = :toolkitId AND q.toolkit.published = true")
	Toolkit findToolkitById(int toolkitId);
	
	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id = :toolkitId AND q.toolkit.published = true")
	Collection<Quantity> findAllQuantityByToolkitId(int toolkitId);
	
	@Query("SELECT DISTINCT(q.toolkit) FROM Quantity q WHERE q.item.id = :itemId AND q.toolkit.published = true")
	Collection<Toolkit> findAllToolkitByItemId(int itemId);
	
	@Query("SELECT c.defaultCurrency FROM Configuration c")
	String findDefaultCurrency();
	
}
