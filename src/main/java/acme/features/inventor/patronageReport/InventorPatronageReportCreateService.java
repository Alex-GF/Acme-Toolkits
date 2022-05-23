package acme.features.inventor.patronageReport;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronageReport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

    // Internal state ---------------------------------------------------------

    @Autowired
    protected InventorPatronageReportRepository repository;

    // AbstractCreateService<Administrator, Announcement> interface --------------

    @Override
    public boolean authorise(final Request<PatronageReport> request) {
        assert request != null;

        boolean result;

        result = request.getPrincipal().hasRole(Inventor.class);

        return result;
    }

    @Override
    public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

        request.bind(entity, errors, "creationMoment", "memorandum", "link");

        final Patronage patronage = this.repository.findAllPatronages().stream()
                .filter(x -> x.getId() == request.getModel().getInteger("patronageId")).findFirst().get();

        entity.setPatronage(patronage);

        final List<PatronageReport> patronageReports = this.repository
                .findAllPatronageReportsById(request.getModel().getInteger("patronageId"));

        if (patronageReports.size() > 0) {
            final List<Integer> sequences = patronageReports.stream()
                    .map(x -> x.getAutomaticSequenceNumber())
                    .map(x -> x.split(":")[1].trim().replaceAll("0", ""))
                    .map(x -> Integer.valueOf(x))
                    .collect(Collectors.toList());

            final String maxSequence = String.valueOf(Collections.max(sequences) + 1);
            String zeros = "";

            for (int i = maxSequence.length(); i < 4; i++)
                zeros += "0";

            final String sequenceNumber = entity.getPatronage().getCode() + ": " + zeros + maxSequence;

            entity.setAutomaticSequenceNumber(sequenceNumber);

        } else {

            entity.setAutomaticSequenceNumber(patronage.getCode() + ": " + "0001");

        }

    }

    @Override
    public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "creationMoment", "memorandum", "link");
        model.setAttribute("confirmation", false);

        final Collection<Patronage> patronages = this.repository.findAllPatronagesByInventorId(request.getPrincipal().getAccountId());
        model.setAttribute("patronageList", patronages);

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

        this.repository.save(entity);
    }
}