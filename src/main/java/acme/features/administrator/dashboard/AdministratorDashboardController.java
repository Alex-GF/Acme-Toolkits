package acme.features.administrator.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.forms.AdministratorDashboard;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdministratorDashboardController extends AbstractController<Administrator, AdministratorDashboard>{
	
	// Internal state ---------------------------------------------------------

			@Autowired
			protected AdministratorDashboardShowService showService;

			// Constructors -----------------------------------------------------------


			@PostConstruct
			protected void initialise() {
				super.addCommand("show", this.showService);
			}

}
