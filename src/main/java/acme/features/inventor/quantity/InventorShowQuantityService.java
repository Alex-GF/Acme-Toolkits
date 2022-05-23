package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorShowQuantityService implements AbstractShowService<Inventor,Quantity> {
	
	@Autowired
	protected InventorQuantityRepository inventorQuantityRepository;
	
	@Autowired
	protected InventorToolkitRepository inventorToolkitRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result = true;
		
		int quantityId;
		Quantity quantity;
		Inventor inventor;
		
		quantityId = request.getModel().getInteger("id");
		quantity = this.inventorQuantityRepository.findQuantityById(quantityId);
		inventor = quantity.getToolkit().getInventor();
		
		result = request.isPrincipal(inventor);
		
		return result;
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		int id;
		Quantity result;
		
		id = request.getModel().getInteger("id");
		
		result = this.inventorQuantityRepository.findQuantityById(id);
		
		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "item.name", "toolkit.title", "toolkit.published", "amount");		
		model.setAttribute("toolkit.inventor.fullName", entity.getToolkit().getInventor().getIdentity().getFullName());
		model.setAttribute("isPublished", entity.getToolkit().isPublished());
	}
}
