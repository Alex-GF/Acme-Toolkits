package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
import acme.utils.ChangeCurrencyLibrary;

@Service
public class InventorShowItemService implements AbstractShowService<Inventor,Item> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository inventorItemRepository;
	
	@Autowired
	protected ChangeCurrencyLibrary changeLibrary;

	// AbstractShowService<Authenticated, Item> interface ---------------------------


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		boolean result;

		result = request.getPrincipal().hasRole(Inventor.class);

		return result;
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		final int itemId;

		itemId = request.getModel().getInteger("id");
		result = this.inventorItemRepository.findItemByInventorAndItemId(request.getPrincipal().getAccountId(), itemId);
		
		final String defaultCurrency = this.inventorItemRepository.findDefaultCurrency();
		
		if(!(result.getRetailPrice().getCurrency().equals(defaultCurrency))){
			result.setRetailPrice(this.changeLibrary.computeMoneyExchange(result.getRetailPrice(), defaultCurrency).getTarget());
		}

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "technology", "code", "retailPrice", "description", "link", "type", "published");
	}
}
