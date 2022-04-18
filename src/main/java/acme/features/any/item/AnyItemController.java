package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyItemController extends AbstractController<Any,Item> {

	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyItemListAllService anyItemListAllService;
		
		@Autowired
		protected AnyItemShowService anyItemShowService;

		

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list", this.anyItemListAllService);
			super.addCommand("show",this.anyItemShowService);

		}
}
