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
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitRepository inventorToolkitRepository;
	
	@Autowired
	protected InventorQuantityRepository inventorQuantityRepository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		boolean result = true;
		
		final int toolkitId;
		final Toolkit toolkit;
		final Long quantities;
		Inventor inventor;
		
		toolkitId = request.getModel().getInteger("id");
		toolkit = this.inventorToolkitRepository.findToolkitById(toolkitId);
		quantities = this.inventorQuantityRepository.findAllQuantityByToolkitId(toolkitId).stream().count();
		inventor = toolkit.getInventor();
		
		result = !toolkit.isPublished() && quantities != 0 && request.isPrincipal(inventor);
		
		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "title", "assemblyNotes", "code", "description", "published", "link", "totalPrice");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "assemblyNotes", "code", "description", "published", "link", "totalPrice");
		model.setAttribute("inventor.fullName", entity.getInventor().getIdentity().getFullName());
		
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		final int toolkitId;
		
		toolkitId = request.getModel().getInteger("id");
		result = this.inventorToolkitRepository.findToolkitById(toolkitId);
		
		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		//TODO Filtro spam
		
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		final Collection<Quantity> toolkitItems = this.inventorQuantityRepository.findAllQuantityByToolkitId(entity.getId());
		
		for(final Quantity q: toolkitItems) {
			q.getItem().setPublished(true);
		}
		
		entity.setPublished(true);
		
		this.inventorToolkitRepository.save(entity);
	}

	
	
}
