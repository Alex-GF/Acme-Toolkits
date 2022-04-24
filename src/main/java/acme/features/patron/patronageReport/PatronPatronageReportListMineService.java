package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronageReport.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportListMineService implements AbstractListService<Patron, PatronageReport> {

    // Internal state ---------------------------------------------------------

    @Autowired
    protected PatronPatronageReportRepository repository;

    // AbstractListService<Patron, Quantity> interface ---------------------------


    

    

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

        boolean result;

        result = request.getPrincipal().hasRole(Patron.class);

        return result;
	}

	@Override
	public Collection<PatronageReport> findMany(final Request<PatronageReport> request) {
		assert request != null;

        Collection<PatronageReport> result;

        result = this.repository.findPatronageReportsByPatronId(request.getPrincipal().getAccountId());

        return result;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "creationMoment", "memorandum", "patronage.code", "automaticSequenceNumber");
		
	}

	

	
}
