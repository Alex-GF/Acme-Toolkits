package acme.features.inventor.toolkit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.features.authenticated.inventor.AuthenticatedInventorRepository;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.utils.GenerateCodeLibrary;;


@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor, Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository inventorToolkitRepository;
	@Autowired
	protected AuthenticatedInventorRepository authenticatedInventorRepository;
	@Autowired
	protected InventorItemRepository inventorItemRepository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		return request.getPrincipal().hasRole(Inventor.class);
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity,errors, "title", "code", "description", "assemblyNotes", "link");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link");
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;
		
		final Inventor inventor;
		final Toolkit result;
		
		final int userAccountId = request.getPrincipal().getAccountId();
		inventor = this.authenticatedInventorRepository.findOneInventorByUserAccountId(userAccountId);
		
		final List<String> codes = this.inventorToolkitRepository.findAllToolkitCodes();
		final String code = GenerateCodeLibrary.generateCode(codes,"^[A-Z]{3}-[0-9]{3}(-[A-Z])?$");
		
		result = new Toolkit();
		result.setCode(code);
		result.setInventor(inventor);
		result.setPublished(false);
		
		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		//TODO Implement here spam filter
			
	}

	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(false);
		
		this.inventorToolkitRepository.save(entity);
		
	}
	


}
