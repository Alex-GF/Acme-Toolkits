package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.currencyExchange.RateExchange;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository{

	@Query("SELECT q FROM Quantity q")
	Collection<Quantity> findAllQuantity();
	
	@Query("SELECT t FROM Toolkit t")
	Collection<Toolkit> findAllToolkit();
	
	@Query("SELECT r FROM RateExchange r WHERE r.currencyExchange.base = :sourceCurrency and r.currency = :targetCurrency")
	RateExchange findRateExchangeBySourceCurrency(String sourceCurrency, String targetCurrency);
	
	@Query("SELECT t FROM Toolkit t WHERE t.id = :toolkitId")
	Toolkit findToolkitById(int toolkitId);
	
	@Query("SELECT q FROM Quantity q WHERE q.toolkit.id = :toolkitId")
	Collection<Quantity> findAllQuantityByToolkitId(int toolkitId);
	
	@Query("SELECT q.toolkit FROM Quantity q WHERE q.item.id = :itemId")
	Collection<Toolkit> findAllToolkitByItemId(int itemId);
	
}
