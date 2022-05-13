package acme.features.inventor.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.quantity.Quantity;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorQuantityController extends AbstractController<Inventor, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityListService	listQuantityService;
	
	@Autowired
	protected InventorShowQuantityService showQuantityService;
	
	@Autowired
	protected InventorQuantityCreateService createQuantityService;
	
	@Autowired
	protected InventorQuantityUpdateService updateQuantityService;
	
	@Autowired
	protected InventorQuantityDeleteService deleteQuantityService;
	
	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listQuantityService);
		super.addCommand("show", this.showQuantityService);
		super.addCommand("create", this.createQuantityService);
		super.addCommand("update", this.updateQuantityService);
		super.addCommand("delete", this.deleteQuantityService);
	}
}
