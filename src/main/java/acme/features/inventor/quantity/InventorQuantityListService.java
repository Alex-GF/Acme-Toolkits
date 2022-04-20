package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorQuantityListService implements AbstractListService<Inventor, Quantity>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;

	// AbstractListService<Inventor, Quantity> interface ---------------------------


	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;

		result = request.getPrincipal().hasRole(Inventor.class);

		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "item.code", "item.name", "item.technology", "item.retailPrice", "item.type", "amount");
		
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;

		Collection<Quantity> result;
		final int toolkitId = request.getModel().getInteger("toolkitId");
		
		result = this.repository.findAllQuantityByToolkitId(toolkitId);

		return result;
	}
}
