package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.inventor.item.InventorItemRepository;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;;


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
		
		request.bind(entity,errors, "amount");
		final int itemId = request.getModel().getInteger("itemId");
		final Item item = this.inventorItemRepository.findItemByItemId(itemId);
		
		entity.setItem(item);
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "toolkit.title","amount");
		model.setAttribute("toolkit.inventor.fullName", entity.getToolkit().getInventor().getIdentity().getFullName());
		model.setAttribute("itemList", this.inventorItemRepository.findItemsByInventorIdToList(request.getPrincipal().getAccountId()));
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		
		final Quantity result = new Quantity();
		
		Integer toolkitId = null;
		Toolkit toolkit = null;
		
		if(request.isMethod(HttpMethod.GET)){
			
			toolkitId = request.getModel().getInteger("toolkitId");
			toolkit = this.inventorToolkitRepository.findToolkitById(toolkitId);
			
		}else if(request.isMethod(HttpMethod.POST)){
			
			final String toolkitTitle = (String) request.getModel().getAttribute("toolkit.title");
			
			toolkit = this.inventorToolkitRepository.findToolkitByTitle(toolkitTitle);
			
		}
		
		result.setToolkit(toolkit);
		
		return result;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("itemId")) {
			boolean existingTool;
			
			try {
				final Quantity q = this.inventorQuantityRepository.findQuantityByItemIdAndToolkitId(entity.getItem().getId(), entity.getToolkit().getId());
				if(q.getItem().getType() == ItemType.TOOL) {
					existingTool = false;
				}else {
					existingTool = true;
				}
			}catch(final Exception e){
				existingTool = true;
			}
			
			errors.state(request, existingTool, "itemId", "inventor.quantity.form.error.itemId");
		}

		if(!errors.hasErrors("amount")) {
			boolean amountPositive;
			boolean correctToolAmount = true;
			
			amountPositive = entity.getAmount() > 0;
			
			if(entity.getItem().getType() == ItemType.TOOL) {
				correctToolAmount = entity.getAmount() == 1;
			}
			
			errors.state(request, amountPositive, "amount", "inventor.quantity.form.error.amount");
			errors.state(request, correctToolAmount, "amount", "inventor.quantity.form.error.amount.tool");
		}
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		try {
			final Quantity q = this.inventorQuantityRepository.findQuantityByItemIdAndToolkitId(entity.getItem().getId(), entity.getToolkit().getId());
		
			if(q.getItem().getType() == ItemType.COMPONENT) {
				q.setAmount(q.getAmount() + entity.getAmount());
				this.inventorQuantityRepository.save(q);
			}
		}catch(final Exception e) {
			this.inventorQuantityRepository.save(entity);
		}
		
	}
	


}
