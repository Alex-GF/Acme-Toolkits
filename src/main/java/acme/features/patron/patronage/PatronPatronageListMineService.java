package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.Patronages.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronPatronageListMineService implements AbstractListService<Patron, Patronage> {

    // Internal state ---------------------------------------------------------

    @Autowired
    protected PatronPatronageRepository repository;

    // AbstractListService<Patron, Quantity> interface ---------------------------

    @Override
    public boolean authorise(final Request<Patronage> request) {
        assert request != null;

        boolean result;

        result = request.getPrincipal().hasRole(Patron.class);

        return result;
    }

    @Override
    public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationMoment", "startDate",
                "finishDate", "link");

    }

    @Override
    public Collection<Patronage> findMany(final Request<Patronage> request) {
        assert request != null;

        Collection<Patronage> result;

        result = this.repository.findPatronagesByPatronId(request.getPrincipal().getAccountId());

        return result;
    }
}
