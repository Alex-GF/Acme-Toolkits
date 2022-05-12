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
	protected InventorListItemsMineService	listItemsMineService;
	
	@Autowired
	protected InventorShowItemService showItemService;
	
	@Autowired
	protected InventorItemCreateService inventorItemCreateService;
	
	@Autowired
	protected InventorItemUpdateService inventorItemUpdateService;
	
	@Autowired
	protected InventorItemPublishService inventorItemPublishService;
	
	@Autowired
	protected InventorItemDeleteService inventorItemDeleteService;
	
	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listItemsMineService);
		super.addCommand("show", this.showItemService);
		
		super.addCommand("create", this.inventorItemCreateService);
		super.addCommand("update", this.inventorItemUpdateService);
		super.addCommand("publish","update",this.inventorItemPublishService);
		super.addCommand("delete", this.inventorItemDeleteService);
	}
}
