package acme.features.patron.patronageReport;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronageReport.PatronageReport;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronPatronageReportController extends AbstractController<Patron, PatronageReport> {

    // Internal state ---------------------------------------------------------

    @Autowired
    protected PatronPatronageReportListMineService listMineService;

    @Autowired
    protected PatronPatronageReportShowService showService;

    // Constructors -----------------------------------------------------------

    @PostConstruct
    protected void initialise() {
        super.addCommand("list", this.listMineService);
        super.addCommand("show", this.showService);
    }
}
