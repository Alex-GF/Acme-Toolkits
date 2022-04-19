package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;


@Service
public class InventorListToolkitsMineService implements AbstractListService<Inventor, Toolkit>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	// AbstractListService<Inventor, Quantity> interface ---------------------------


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;

		result = request.getPrincipal().hasRole(Inventor.class);

		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "link");
		
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;

		Collection<Toolkit> result;

		result = this.repository.findToolkitsByInventorId(request.getPrincipal().getAccountId());

		return result;
	}
}
