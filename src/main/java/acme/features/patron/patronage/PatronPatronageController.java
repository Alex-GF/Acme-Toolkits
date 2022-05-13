package acme.features.patron.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronage.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronPatronageController extends AbstractController<Patron, Patronage> {

    // Internal state ---------------------------------------------------------

    @Autowired
    protected PatronPatronageListMineService listMineService;

    @Autowired
    protected PatronPatronageShowService showService;
    
    @Autowired
    protected PatronPatronagePublishService publishService;
    
    @Autowired
    protected PatronPatronageCreateService createService;
    
    @Autowired
    protected PatronPatronageUpdateService updateService;
    
    @Autowired
    protected PatronPatronageDeleteService deleteService;

    // Constructors -----------------------------------------------------------

    @PostConstruct
    protected void initialise() {
        super.addCommand("list", this.listMineService);
        super.addCommand("show", this.showService);
        super.addCommand("create", this.createService);
        super.addCommand("update", this.updateService);
        super.addCommand("delete", this.deleteService);
        
        super.addCommand("publish", "update", this.publishService);
    }
}
