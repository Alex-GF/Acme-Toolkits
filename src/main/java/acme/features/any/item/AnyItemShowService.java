package acme.features.any.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;
import acme.utils.ChangeCurrencyLibrary;

@Service
public class AnyItemShowService implements AbstractShowService<Any,Item>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyItemRepository anyComponentRepository;
	
	@Autowired
	protected ChangeCurrencyLibrary changeLibrary;

	// AbstractShowService<Authenticated, Item> interface ---------------------------


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		boolean result;
		int id;
		
		id = request.getModel().getInteger("id");
		
		final Item i = this.anyComponentRepository.getItemById(id);
		
		result = i.isPublished();
		
		return result;
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.anyComponentRepository.getItemById(id);
		
		final String defaultCurrency = this.anyComponentRepository.findDefaultCurrency();
		
		if(!(result.getRetailPrice().getCurrency().equals(defaultCurrency))) {
			result.setRetailPrice(this.changeLibrary.computeMoneyExchange(result.getRetailPrice(), defaultCurrency).getTarget());
		}

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "technology", "code", "retailPrice", "description", "link","type");
		model.setAttribute("readonly", true);
		model.setAttribute("inventor.fullName", entity.getInventor().getIdentity().getFullName());
		
		final String defaultCurrency = this.anyComponentRepository.findDefaultCurrency();
		
		final Item item = this.anyComponentRepository.findAllItem().stream().filter(x -> x.getId() == entity.getId()).findFirst().get();
		
		if(!(item.getRetailPrice().getCurrency().equals(defaultCurrency))) {
			model.setAttribute("showDefaultCurrency", true);
			model.setAttribute("defaultCurrency",item.getRetailPrice());
		}
		
	}

}
