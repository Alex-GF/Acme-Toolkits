package acme.features.any.toolkit;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		if(request.getModel().hasAttribute("itemId")) {
			final int itemId = request.getModel().getInteger("itemId");
			result = this.anyToolkitRepository.findAllToolkitByItemId(itemId);
		}else {
			result = this.anyToolkitRepository.findAllToolkit();
		}
		
		final Map<String,Money> totalPrice = this.totalPrice();
		
		result.stream().forEach(t -> t.setTotalPrice(totalPrice.get(t.getCode())));
		
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;	
		
		request.unbind(entity, model, "title", "code", "totalPrice");
		
	}
	
	// Ancillary methods ------------------------------------------------------
		
	private Map<String,Money> totalPrice(){
		
		final Map<String,Money> result = new HashMap<>();
		
		final Collection<Quantity> quantities = this.anyToolkitRepository.findAllQuantity();
		
		for(final Quantity q : quantities) {
			final Money itemMoney = q.getItem().getRetailPrice();
			
			final Money itemMoneyEur = MoneyExchange.changeCurrency(itemMoney, "EUR", this.anyToolkitRepository);
			
			if(result.containsKey(q.getToolkit().getCode())) {
				final Money beforeUpdate = result.get(q.getToolkit().getCode());
				
				final Money afterUpdate = new Money();
				afterUpdate.setAmount(beforeUpdate.getAmount()+(itemMoneyEur.getAmount()*q.getAmount()));
				afterUpdate.setCurrency("EUR");
				
				result.put(q.getToolkit().getCode(),afterUpdate);
			}else {
				final Money firstMoneyValue = new Money();
				firstMoneyValue.setCurrency("EUR");
				final double newAumount = itemMoneyEur.getAmount()*q.getAmount();
				firstMoneyValue.setAmount(newAumount);
				result.put(q.getToolkit().getCode(),firstMoneyValue);
			}
			
		}
		
		
		return result;
	}
	
	
	
	
}
