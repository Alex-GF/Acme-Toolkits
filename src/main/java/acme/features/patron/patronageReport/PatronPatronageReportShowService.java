package acme.features.patron.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronageReport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportShowService implements AbstractShowService<Patron, PatronageReport> {

    // Internal state ---------------------------------------------------------

    @Autowired
    protected PatronPatronageReportRepository repository;

    // AbstractListService<Patron, Quantity> interface ---------------------------

    @Override
    public boolean authorise(final Request<PatronageReport> request) {
        assert request != null;

        final boolean result;
		final int patronageReportId;
		final PatronageReport patronageReport;
		final Patron patron;
		
		patronageReportId = request.getModel().getInteger("id");
		patronageReport = this.repository.getPatronageReportById(patronageReportId);
		patron = patronageReport.getPatronage().getPatron();
		result = request.isPrincipal(patron);
		
		return result;
    }

    @Override
    public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "creationMoment", "memorandum", "link", "automaticSequenceNumber", "patronage");
        model.setAttribute("readonly", true);
    }

    @Override
    public PatronageReport findOne(final Request<PatronageReport> request) {
        assert request != null;

        PatronageReport result;
        int id;

        id = request.getModel().getInteger("id");
        result = this.repository.getPatronageReportById(id);

        return result;
    }

}
