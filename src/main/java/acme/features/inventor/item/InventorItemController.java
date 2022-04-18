package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventortListComponentsMineService	listComponentsMineService;
	
	@Autowired
	protected InventorListToolsMineService	listToolsMineService;
	
	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list-components-mine", "list", this.listComponentsMineService);
		super.addCommand("list-tools-mine", "list", this.listToolsMineService);
	}
}
