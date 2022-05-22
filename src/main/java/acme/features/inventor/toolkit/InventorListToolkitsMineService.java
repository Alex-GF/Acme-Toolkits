package acme.features.inventor.toolkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
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
		
		String payload;
		
		request.unbind(entity, model, "code", "title","published");
		
		final List<Quantity> quantitiesList = new ArrayList<Quantity>(this.repository.findAllQuantityByToolkitId(entity.getId()));
		
		final List<Item> itemsList = quantitiesList.stream().map(Quantity::getItem).collect(Collectors.toList());
		
		final List<String> codesList = itemsList.stream().map(Item::getCode).collect(Collectors.toList());
		
		final List<String> namesList = itemsList.stream().map(Item::getName).collect(Collectors.toList());
		
		final List<String> technologiesList = itemsList.stream().map(Item::getTechnology).collect(Collectors.toList()); 
 		
		payload = String.format(
            "%s; %s; %s;",
            codesList.toString().replace("[", "").replace("]", ""),
            namesList.toString().replace("[", "").replace("]", ""),
            technologiesList.toString().replace("[", "").replace("]", ""));
		model.setAttribute("payload", payload);
		
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;

		Collection<Toolkit> result;
		
		result = this.repository.findToolkitsByInventorId(request.getPrincipal().getAccountId());	

		return result;
	}
}
