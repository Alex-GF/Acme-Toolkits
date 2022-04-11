package acme.features.any.toolkit;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import acme.component.ExchangeRate;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolkitListAllService implements AbstractListService<Any, Toolkit>{
	
	@Autowired
	protected AnyToolkitRepository anyToolkitRepository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;
		
		Collection<Toolkit> result;
		
		result = this.anyToolkitRepository.findAllToolkit();
		
		final Map<String,Money> totalPrice = this.totalPrice();
		
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Map<String,Money> totalPrice = this.totalPrice();
		
		for(final String code : totalPrice.keySet()) model.setAttribute(code, totalPrice.get(code));
		
		request.unbind(entity, model, "title", "code");
		
	}
	
	// Ancillary methods ------------------------------------------------------
		
	private Map<String,Money> totalPrice(){
		
		final Map<String,Money> result = new HashMap<>();
		
		final Collection<Quantity> quantities = this.anyToolkitRepository.findAllQuantity();
		
		for(final Quantity q : quantities) {
			final Money itemMoney = q.getItem().getRetailPrice();
			
			final Money itemMoneyEur = this.changeCurrency(itemMoney, "EUR");
			
			if(result.containsKey(q.getToolkit().getCode())) {
				final Money afterUpdate = result.get(q.getToolkit().getCode());
				
				final Money beforeUpdate = new Money();
				beforeUpdate.setAmount(afterUpdate.getAmount()+(itemMoney.getAmount()*q.getAmount()));
				beforeUpdate.setCurrency("EUR");
				
				
				result.put(q.getToolkit().getCode(),beforeUpdate);
			}else {
				final Money firsValueMoney = new Money();
				firsValueMoney.setAmount(itemMoney.getAmount()*q.getAmount());
				firsValueMoney.setCurrency("EUR");
				
				result.put(q.getToolkit().getCode(),firsValueMoney);
			}
			
		}
		
		
		return result;
	}
	
	public Money changeCurrency(final Money source, final String targetCurrency) {
		Money targetMoney = new Money();
		
		RestTemplate api;
		ExchangeRate record;
		String sourceCurrency;
		Double sourceAmount, targetAmount, rate;
		final Money target;
		
		try {
			api = new RestTemplate();

			sourceCurrency = source.getCurrency();
			sourceAmount = source.getAmount();

			record = api.getForObject( //
				"https://api.exchangerate.host/latest?base={0}&symbols={1}", //
				ExchangeRate.class, //
				sourceCurrency, //
				targetCurrency //
			);

			assert record != null;
			rate = record.getRates().get(targetCurrency);
			targetAmount = rate * sourceAmount;

			targetMoney.setAmount(targetAmount);
			targetMoney.setCurrency(sourceCurrency);
			
		} catch (final Throwable oops) {
			targetMoney = null;
		}
		
		return targetMoney;
		
	}
	
	
}
