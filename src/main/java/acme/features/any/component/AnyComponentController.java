package acme.features.any.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyComponentController extends AbstractController<Any,Item> {

	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyComponentListAllService anyComponentListAllService; 
		
		@Autowired
		protected AnyComponentShowService anyComponentShowService;


		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list-all-component","list", this.anyComponentListAllService);
			super.addCommand("show",this.anyComponentShowService);
		}
}
