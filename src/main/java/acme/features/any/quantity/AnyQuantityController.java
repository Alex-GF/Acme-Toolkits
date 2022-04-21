package acme.features.any.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.quantity.Quantity;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyQuantityController extends AbstractController<Any, Quantity>{

	@Autowired
	protected AnyQuantityListService anyQuantityListService;
	
	@Autowired
	protected AnyQuantityShowService anyQuantityShowService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.anyQuantityListService);
		super.addCommand("show", this.anyQuantityShowService);
	}
	
}
