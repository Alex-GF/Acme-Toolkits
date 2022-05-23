package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.features.inventor.quantity.InventorQuantityRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorToolkitDeleteService implements AbstractDeleteService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitRepository inventorToolkitRepository;
	
	@Autowired
	protected InventorQuantityRepository inventorQuantityRepository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		boolean result = true;
		
		int toolkitId;
		Toolkit toolkit;
		Inventor inventor;
		
		toolkitId = request.getModel().getInteger("id");
		toolkit = this.inventorToolkitRepository.findToolkitById(toolkitId);
		inventor = toolkit.getInventor();
		
		result = !toolkit.isPublished() && request.isPrincipal(inventor);
		
		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity,errors,"title", "assemblyNotes", "code", "description", "published", "link", "totalPrice");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "assemblyNotes", "code", "description", "published", "link", "totalPrice");
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int toolkitId;
		
		toolkitId = request.getModel().getInteger("id");
		result = this.inventorToolkitRepository.findToolkitById(toolkitId);
		
		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		final Collection<Quantity> quantities = this.inventorQuantityRepository.findAllQuantityByToolkitId(entity.getId());
		
		for(final Quantity q: quantities) {
			this.inventorQuantityRepository.delete(q);
		}
		
		this.inventorToolkitRepository.delete(entity);
	}

}
