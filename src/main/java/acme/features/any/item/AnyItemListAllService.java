package acme.features.any.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;
import acme.utils.ChangeCurrencyLibrary;

@Service
public class AnyItemListAllService implements AbstractListService<Any, Item>{
	
	@Autowired
	protected AnyItemRepository anyItemRepository; 
	
	@Autowired
	protected ChangeCurrencyLibrary changeLibrary;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
			
		return true;
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;
		
		Collection<Item> result;
		
		result = this.anyItemRepository.findAllItem();
		
		final String defaultCurrency = this.anyItemRepository.findDefaultCurrency();
		
		result.stream().filter(p-> !(p.getRetailPrice().getCurrency().equals(defaultCurrency))).forEach(p -> p.setRetailPrice(this.changeLibrary.computeMoneyExchange(p.getRetailPrice(), defaultCurrency).getTarget()));
		
		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "technology", "code","retailPrice","type");
	}

	
	
}
