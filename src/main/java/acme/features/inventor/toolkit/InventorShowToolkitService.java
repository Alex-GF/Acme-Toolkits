package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
import acme.utils.ChangeCurrencyLibrary;

@Service
public class InventorShowToolkitService implements AbstractShowService<Inventor, Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository inventorToolkitRepository;
	
	@Autowired
	protected ChangeCurrencyLibrary changeLibrary;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		final Toolkit result;
		
		final int toolkitId = request.getModel().getInteger("id");
		result = this.inventorToolkitRepository.findToolkitById(toolkitId);
		
		final Money totalPrice = this.totalPriceOfToolktit(toolkitId);
		result.setTotalPrice(totalPrice);
		
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;	

		assert model != null;

		request.unbind(entity, model, "title", "assemblyNotes", "code", "description", "published", "link", "totalPrice");
		model.setAttribute("readonly", entity.isPublished());
		model.setAttribute("inventor.fullName", entity.getInventor().getIdentity().getFullName());
		
	}
	
	// Ancillary methods ------------------------------------------------------
	
	private Money totalPriceOfToolktit(final int toolkitId) {
		
		final String defaultCurrency = this.inventorToolkitRepository.findDefaultCurrency();
		
		final Money result = new Money();
		result.setAmount(0.0);
		result.setCurrency(defaultCurrency);
		
		final Collection<Quantity> quantities = this.inventorToolkitRepository.findAllQuantityByToolkitId(toolkitId);
		
		for(final Quantity q : quantities) {
			final Money itemMoney = q.getItem().getRetailPrice();
			final Integer amountItem = q.getAmount();
			
			Money itemMoneyExchanged;
			
			if(itemMoney.getCurrency().equals(defaultCurrency)) {
				itemMoneyExchanged = itemMoney;
			}else {
				itemMoneyExchanged = this.changeLibrary.computeMoneyExchange(itemMoney,defaultCurrency).getTarget();
			}
			
			final Double newAmount = result.getAmount() + itemMoneyExchanged.getAmount()*amountItem;
			result.setAmount(newAmount);
			
		}
		
		return result;
	}
	
}
