//package acme.features.any.component;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import acme.entities.item.Item;
//import acme.framework.components.models.Model;
//import acme.framework.controllers.Request;
//import acme.framework.roles.Any;
//import acme.framework.services.AbstractShowService;
//
//@Service
//public class AnyComponentShowService implements AbstractShowService<Any,Item>{
//
//	// Internal state ---------------------------------------------------------
//
//	@Autowired
//	protected AnyComponentRepository anyComponentRepository;
//
//	// AbstractShowService<Authenticated, Item> interface ---------------------------
//
//
//	@Override
//	public boolean authorise(final Request<Item> request) {
//		assert request != null;
//		
//		return true;
//	}
//
//	@Override
//	public Item findOne(final Request<Item> request) {
//		assert request != null;
//
//		Item result;
//		int id;
//
//		id = request.getModel().getInteger("id");
//		result = this.anyComponentRepository.getItemById(id);
//
//		return result;
//	}
//
//	@Override
//	public void unbind(final Request<Item> request, final Item entity, final Model model) {
//		assert request != null;
//		assert entity != null;
//		assert model != null;
//
//		request.unbind(entity, model, "name", "technology", "code", "retailPrice", "description", "link");
//		model.setAttribute("readonly", true);
//	}
//
//}
