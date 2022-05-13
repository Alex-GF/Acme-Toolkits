package acme.features.inventor.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkit.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorToolkitController extends AbstractController<Inventor, Toolkit>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected InventorListToolkitsMineService	listToolkitsMineService;
		
		@Autowired
		protected InventorShowToolkitService showToolkitService;
		
		@Autowired
		protected InventorToolkitCreateService createToolkitService;
		
		@Autowired
		protected InventorToolkitUpdateService updateToolkitService;
		
		@Autowired
		protected InventorToolkitPublishService publishToolkitService;
		
		@Autowired
		protected InventorToolkitDeleteService deleteTollkitService;
		
		// Constructors -----------------------------------------------------------

		@PostConstruct
		protected void initialise() {
			super.addCommand("list-mine", "list", this.listToolkitsMineService);
			super.addCommand("show", this.showToolkitService);
			
			super.addCommand("create", this.createToolkitService);
			super.addCommand("update", this.updateToolkitService);
			super.addCommand("publish", "update", this.publishToolkitService);
			super.addCommand("delete", this.deleteTollkitService);
		}
}
