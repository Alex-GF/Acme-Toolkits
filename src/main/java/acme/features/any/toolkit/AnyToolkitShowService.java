package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit>{

	@Autowired
	protected AnyToolkitRepository anyToolkitRepository;
	
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
		result = this.anyToolkitRepository.findToolkitById(toolkitId);
		
		final Money totalPrice = this.totalPriceOfToolktit(toolkitId);
		result.setTotalPrice(totalPrice);
		
		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "assemblyNotes", "code", "totalPrice", "description", "link");
		model.setAttribute("readonly", true);
		
	}
	
	// Ancillary methods ------------------------------------------------------
	
	private Money totalPriceOfToolktit(final int toolkitId) {
		final Money result = new Money();
		result.setAmount(0.0);
		result.setCurrency("EUR");
		
		final Collection<Quantity> quantities = this.anyToolkitRepository.findAllQuantityByToolkitId(toolkitId);
		
		for(final Quantity q : quantities) {
			final Money itemMoney = q.getItem().getRetailPrice();
			final Integer amountItem = q.getAmount();
			
			final Money itemMoneyExchanged = MoneyExchange.changeCurrency(itemMoney, "EUR", this.anyToolkitRepository);
			
			final Double newAmount = result.getAmount() + itemMoneyExchanged.getAmount()*amountItem;
			result.setAmount(newAmount);
			
		}
		
		return result;
	}

}
