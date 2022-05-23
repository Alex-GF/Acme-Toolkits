package acme.features.any.toolkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;
import acme.utils.ChangeCurrencyLibrary;

@Service
public class AnyToolkitListAllService implements AbstractListService<Any, Toolkit>{
	
	@Autowired
	protected AnyToolkitRepository anyToolkitRepository;
	
	@Autowired
	protected ChangeCurrencyLibrary changeLibrary;

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
		
		result.stream().forEach(t -> t.setTotalPrice(totalPrice.get(t.getCode())));
		
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String payload;
		
		request.unbind(entity, model, "title", "code", "totalPrice");
		
		final List<Quantity> quantitiesList = new ArrayList<Quantity>(this.anyToolkitRepository.findAllQuantityByToolkitId(entity.getId()));
	
		final List<Item> itemsList = quantitiesList.stream().map(Quantity::getItem).collect(Collectors.toList());
		
		final List<String> codesList = itemsList.stream().map(Item::getCode).collect(Collectors.toList());
		
		final List<String> namesList = itemsList.stream().map(Item::getName).collect(Collectors.toList());
		
		final List<String> technologiesList = itemsList.stream().map(Item::getTechnology).collect(Collectors.toList()); 
 		
		payload = String.format(
            "%s; %s; %s;",
            codesList.toString().replace("[", "").replace("]", ""),
            namesList.toString().replace("[", "").replace("]", ""),
            technologiesList.toString().replace("[", "").replace("]", ""));
		model.setAttribute("payload", payload);
			
	}
	
	// Ancillary methods ------------------------------------------------------
		
	private Map<String,Money> totalPrice(){
		
		final Map<String,Money> result = new HashMap<>();
		
		final Collection<Quantity> quantities = this.anyToolkitRepository.findAllQuantity();
		
		for(final Quantity q : quantities) {
			final Money itemMoney = q.getItem().getRetailPrice();
			
			final String defaultCurrency = this.anyToolkitRepository.findDefaultCurrency();
			
			Money itemDefaultMoney;
			
			if(itemMoney.getCurrency().equals(defaultCurrency)) {
				itemDefaultMoney = itemMoney;
			}else {
				itemDefaultMoney = this.changeLibrary.computeMoneyExchange(itemMoney, defaultCurrency).getTarget();
			}
			
			if(result.containsKey(q.getToolkit().getCode())) {
				final Money beforeUpdate = result.get(q.getToolkit().getCode());
				
				final Money afterUpdate = new Money();
				afterUpdate.setAmount(beforeUpdate.getAmount()+(itemDefaultMoney.getAmount()*q.getAmount()));
				afterUpdate.setCurrency("EUR");
				
				result.put(q.getToolkit().getCode(),afterUpdate);
			}else {
				final Money firstMoneyValue = new Money();
				firstMoneyValue.setCurrency("EUR");
				final double newAumount = itemDefaultMoney.getAmount()*q.getAmount();
				firstMoneyValue.setAmount(newAumount);
				result.put(q.getToolkit().getCode(),firstMoneyValue);
			}
			
		}
		
		
		return result;
	}
	
	
	
	
}
