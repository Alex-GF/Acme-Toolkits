package acme.features.inventor.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.utils.AcceptedCurrencyLibrary;
import acme.utils.GenerateCodeLibrary;;


@Service
public class InventorItemCreateService implements AbstractCreateService<Inventor, Item>{
	
	@Autowired
	protected InventorItemRepository inventorItemRepository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		boolean result;

		result = request.getPrincipal().hasRole(Inventor.class);

		return result;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity,errors,"name", "technology", "code", "retailPrice", "description", "link", "type");
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "technology", "code", "retailPrice", "description", "link", "type", "published");
	}

	@Override
	public Item instantiate(final Request<Item> request) {
		assert request != null;
		
		final Inventor inventor;
		final Item result;
		
		final int userAccountId = request.getPrincipal().getAccountId();
		inventor = this.inventorItemRepository.findInventorByUserAccountId(userAccountId);
		
		final List<String> codes = this.inventorItemRepository.findAllCodes();
		final String code = GenerateCodeLibrary.generateCode(codes,"^[A-Z]{3}-[0-9]{3}(-[A-Z])?$");
		
		result = new Item();
		result.setCode(code);
		result.setInventor(inventor);
		result.setPublished(false);
		
		
		
		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final List<String> acceptedCurrencies = AcceptedCurrencyLibrary.getAcceptedCurrencies(this.inventorItemRepository.findAcceptedCurrencies());
		
		
		if(!errors.hasErrors("retailPrice")) {
			boolean acceptedCurrency;
			
			acceptedCurrency = acceptedCurrencies.contains(entity.getRetailPrice().getCurrency());
			
			errors.state(request, acceptedCurrency, "retailPrice", "inventor.item.form.error.acceptedCurrency");
			
			boolean positiveValue;
			
			positiveValue = entity.getRetailPrice().getAmount()>0;
			
			errors.state(request, positiveValue, "retailPrice", "inventor.item.form.error.positiveValue");
			
		}
	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(false);
		
		this.inventorItemRepository.save(entity);
		
	}
	


}
