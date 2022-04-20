package acme.features.any.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyQuantityListService implements AbstractListService<Any, Quantity>{

	@Autowired
	protected AnyQuantityRepository anyQuantityRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		
		return true;
	}

	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;
		
		final Collection<Quantity> result;
		int id;
		
		id = request.getModel().getInteger("toolkitId");
		result = this.anyQuantityRepository.findAllQuantityByToolkitId(id);
		
		
		
		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "item.code","item.name","item.technology","item.retailPrice","item.type", "amount");
	}
	
}
