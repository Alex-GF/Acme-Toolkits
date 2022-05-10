package acme.features.inventor.quantity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.inventor.item.InventorItemRepository;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.utils.AcceptedCurrencyLibrary;
import acme.utils.GenerateCodeLibrary;;


@Service
public class InventorQuantityCreateService implements AbstractCreateService<Inventor, Quantity>{
	
	@Autowired
	protected InventorQuantityRepository inventorQuantityRepository;
	
	@Autowired
	protected InventorItemRepository inventorItemRepository;
	
	@Autowired
	protected InventorToolkitRepository inventorToolkitRepository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		boolean result;

		result = request.getPrincipal().hasRole(Inventor.class);

		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity,errors,"item.code","item.name","item.technology","item.retailPrice","item.type","item.description","item.link","item.inventor.name","item.inventor.surname", "amount");
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "item.code","item.name","item.technology","item.retailPrice","item.type","item.description","item.link","item.inventor.name","item.inventor.surname", "amount");
		model.setAttribute("inventor.fullName", entity.getToolkit().getInventor().getIdentity().getFullName());
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		
		final Inventor inventor;
		final Quantity result = new Quantity();
		final Item resultItem = new Item();
		
		final int toolkitId = request.getModel().getInteger("toolkitId");
		final Toolkit toolkit = this.inventorToolkitRepository.findToolkitById(toolkitId);
		
		final int userAccountId = request.getPrincipal().getAccountId();
		inventor = this.inventorItemRepository.findInventorByUserAccountId(userAccountId);
		
		final List<String> itemCodes = this.inventorItemRepository.findAllCodes();
		final String itemCode = GenerateCodeLibrary.generateCode(itemCodes,"^[A-Z]{3}-[0-9]{3}(-[A-Z])?$");
	
		resultItem.setCode(itemCode);
		resultItem.setInventor(inventor);
		resultItem.setPublished(false);
		
		result.setItem(resultItem);
		result.setToolkit(toolkit);
		
		return result;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final List<String> acceptedCurrencies = AcceptedCurrencyLibrary.getAcceptedCurrencies(this.inventorItemRepository.findAcceptedCurrencies());
		
		
		if(!errors.hasErrors("retailPrice")) {
			boolean acceptedCurrency;
			
			acceptedCurrency = acceptedCurrencies.contains(entity.getItem().getRetailPrice().getCurrency());
			
			errors.state(request, acceptedCurrency, "retailPrice", "inventor.item.form.error.acceptedCurrency");
			
		}
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		this.inventorItemRepository.save(entity.getItem());
		this.inventorQuantityRepository.save(entity);
		
	}
	


}
