package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;
import acme.utils.ChangeCurrencyLibrary;

@Service
public class InventorListItemsMineService implements AbstractListService<Inventor, Item>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;
	
	@Autowired
	protected ChangeCurrencyLibrary changeLibrary;

	// AbstractListService<Inventor, Quantity> interface ---------------------------


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;

		result = request.getPrincipal().hasRole(Inventor.class);

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "retailPrice", "type","published");
		
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;

		Collection<Item> result;

		result = this.repository.findItemsByInventorId(request.getPrincipal().getAccountId());
		
		final String defaultCurrency = this.repository.findDefaultCurrency();
		
		result.stream()
			.filter(p->!(p.getRetailPrice().getCurrency().equals(defaultCurrency)))
			.forEach(p->p.setRetailPrice(this.changeLibrary.computeMoneyExchange(p.getRetailPrice(), defaultCurrency).getTarget()));

		return result;
	}
}
