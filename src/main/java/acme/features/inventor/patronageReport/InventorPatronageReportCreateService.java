package acme.features.inventor.patronageReport;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronageReport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Inventor;
import acme.framework.services.AbstractCreateService;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

    // Internal state ---------------------------------------------------------

    @Autowired
    protected InventorPatronageReportRepository repository;

    // AbstractCreateService<Administrator, Announcement> interface --------------

    @Override
    public boolean authorise(final Request<PatronageReport> request) {
        assert request != null;

        return true;
    }

    @Override
    public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

        request.bind(entity, errors, "creationMoment", "memorandum", "patronage.code", "automaticSequenceNumber");
    }

    @Override
    public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "creationMoment", "memorandum", "patronage.code", "automaticSequenceNumber");
        model.setAttribute("confirmation", false);
    }

    @Override
    public PatronageReport instantiate(final Request<PatronageReport> request) {
        assert request != null;

        PatronageReport result;
        Date moment;

        moment = new Date(System.currentTimeMillis() - 1);

        result = new PatronageReport();
        result.setMemorandum("");
        result.setCreationMoment(moment);
        result.setLink("");
        result.setAutomaticSequenceNumber(automaticSequenceNumber:"");

        return result;
    }

    @Override
    public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

        boolean confirmation;

        confirmation = request.getModel().getBoolean("confirmation");
        errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
    }

    @Override
    public void create(final Request<PatronageReport> request, final PatronageReport entity) {
        assert request != null;
        assert entity != null;

        Date moment;

        moment = new Date(System.currentTimeMillis() - 1);
        entity.setCreationMoment(moment);
        this.repository.save(entity);
    }

}
