package acme.features.administrator.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.configuration.Configuration;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdministratorConfigurationController extends AbstractController<Administrator,Configuration> {

	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorConfigurationShowService administratorConfigurationShowService;

		@Autowired
		protected AdministratorConfigurationUpdateService administratorConfigurationUpdateService;

		

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("show",this.administratorConfigurationShowService);

			super.addCommand("update", this.administratorConfigurationUpdateService);

		}
}
