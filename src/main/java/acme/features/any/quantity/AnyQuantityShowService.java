package acme.features.any.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;
import acme.utils.ChangeCurrencyLibrary;

@Service
public class AnyQuantityShowService implements AbstractShowService<Any, Quantity>{

	@Autowired
	protected AnyQuantityRepository anyQuantityRepository;
	
	@Autowired
	protected ChangeCurrencyLibrary changeLibrary;
	
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		
		return true;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		int id;
		Quantity result;
		
		id = request.getModel().getInteger("id");
		
		result = this.anyQuantityRepository.findQuantityById(id);
		
		final String defaultCurrency = this.anyQuantityRepository.findDefaultCurrency();
		
		result.getItem().setRetailPrice(this.changeLibrary.computeMoneyExchange(result.getItem().getRetailPrice(), defaultCurrency).getTarget());
		
		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "item.code","item.name","item.technology","item.retailPrice","item.type","item.description","item.link","item.inventor.name","item.inventor.surname", "amount");		
		model.setAttribute("inventor.fullName", entity.getToolkit().getInventor().getIdentity().getFullName());
	}

	
	
}
