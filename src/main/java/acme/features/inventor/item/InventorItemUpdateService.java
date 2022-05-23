package acme.features.inventor.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import acme.utils.AcceptedCurrencyLibrary;
import main.AntiSpam;
import acme.utils.ChangeCurrencyLibrary;

@Service
public class InventorItemUpdateService implements AbstractUpdateService<Inventor, Item>{

	@Autowired
	protected InventorItemRepository inventorItemRepository;
	
	@Autowired
	protected ChangeCurrencyLibrary changeLibrary;
	
	@Override
	public boolean authorise(final Request<Item> request) {
		boolean result = true;
		
		int itemId;
		Item item;
		Inventor inventor;
		
		itemId = request.getModel().getInteger("id");
		item = this.inventorItemRepository.findItemByItemId(itemId);
		inventor = item.getInventor();
		
		result = !item.isPublished() && request.isPrincipal(inventor);
		
		return result;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity,errors,"name", "technology", "code", "retailPrice", "description", "link", "type");
		
		final Model model = request.getModel();
		
		if(model.hasAttribute("defaultCurrency")){
			final Money m = model.getAttribute("defaultCurrency", Money.class);
			entity.setRetailPrice(m);
		}
		
		
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "name", "technology", "code", "retailPrice", "description", "link", "type", "published");
		
		final String defaultCurrency = this.inventorItemRepository.findDefaultCurrency();
		
		final Item i = this.inventorItemRepository.findItemByItemId(entity.getId());
		
		if(!(i.getRetailPrice().getCurrency().equals(defaultCurrency))) {
			
			final Money m = this.changeLibrary.computeMoneyExchange(i.getRetailPrice(), defaultCurrency).getTarget();
			
			model.setAttribute("showDefaultCurrency", true);
			model.setAttribute("retailPrice",m);
			model.setAttribute("defaultCurrency", entity.getRetailPrice());
		}
		
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		Item result;
		int itemId;
		
		itemId = request.getModel().getInteger("id");
		result = this.inventorItemRepository.findItemByItemId(itemId);
		
		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final List<String> acceptedCurrencies = AcceptedCurrencyLibrary.getAcceptedCurrencies(this.inventorItemRepository.findAcceptedCurrencies());
		
		boolean spamWord;
		boolean spamWordName;
		boolean spamWordTecnology;
		
		final Configuration configuration = this.inventorItemRepository.configuration();
		final AntiSpam antiSpam = new AntiSpam(configuration.getStrongSpamWords(), configuration.getStrongSpamThreshold(), configuration.getWeakSpamWords(), configuration.getWeakSpamThreshold(), entity.getDescription());
		spamWord = antiSpam.getAvoidSpam();
		errors.state(request, !spamWord, "description", "inventor.item.form.error.spamWord");
		
		final AntiSpam antiSpamName = new AntiSpam(configuration.getStrongSpamWords(), configuration.getStrongSpamThreshold(), configuration.getWeakSpamWords(), configuration.getWeakSpamThreshold(), entity.getName());
		spamWordName = antiSpamName.getAvoidSpam();
		errors.state(request, !spamWordName, "name", "inventor.item.form.error.spamWord");
		
		final AntiSpam antiSpamTecnology = new AntiSpam(configuration.getStrongSpamWords(), configuration.getStrongSpamThreshold(), configuration.getWeakSpamWords(), configuration.getWeakSpamThreshold(), entity.getTechnology());
		spamWordTecnology = antiSpamTecnology.getAvoidSpam();
		errors.state(request, !spamWordTecnology, "technology", "inventor.item.form.error.spamWord");
		
		if(!(request.getModel().hasAttribute("defaultCurrency"))){
			
			if(!errors.hasErrors("retailPrice")) {
				boolean acceptedCurrency;
				
				acceptedCurrency = acceptedCurrencies.contains(entity.getRetailPrice().getCurrency());
				
				errors.state(request, acceptedCurrency, "retailPrice", "inventor.item.form.error.acceptedCurrency");
				
				boolean positiveValue;
				
				positiveValue = entity.getRetailPrice().getAmount()>0;
				
				errors.state(request, positiveValue, "retailPrice", "inventor.item.form.error.positiveValue");
			}
			
		}else {
			
			if(!errors.hasErrors("defaultCurrency")) {
				boolean acceptedCurrency;
				
				acceptedCurrency = acceptedCurrencies.contains(entity.getRetailPrice().getCurrency());
				
				errors.state(request, acceptedCurrency, "defaultCurrency", "inventor.item.form.error.acceptedCurrency");
				
				boolean positiveValue;
				
				positiveValue = entity.getRetailPrice().getAmount()>0;
				
				errors.state(request, positiveValue, "defaultCurrency", "inventor.item.form.error.positiveValue");
			}
			
		}
		
		
	}

	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(false);
		this.inventorItemRepository.save(entity);
		
	} 
	

}
