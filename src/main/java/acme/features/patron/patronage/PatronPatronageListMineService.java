package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;
import acme.utils.ChangeCurrencyLibrary;

@Service
public class PatronPatronageListMineService implements AbstractListService<Patron, Patronage> {

    // Internal state ---------------------------------------------------------

    @Autowired
    protected PatronPatronageRepository repository;
    
    @Autowired
   	protected ChangeCurrencyLibrary changeLibrary;

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
                "finishDate", "link","published");

    }

    @Override
    public Collection<Patronage> findMany(final Request<Patronage> request) {
        assert request != null;

        Collection<Patronage> result;

        result = this.repository.findPatronagesByPatronId(request.getPrincipal().getAccountId());
        
        final String defaultCurrency = this.repository.findDefaultCurrency();
        
        result.stream().filter(x-> !(x.getBudget().getCurrency().equals(defaultCurrency))).forEach(x->x.setBudget(this.changeLibrary.computeMoneyExchange(x.getBudget(), defaultCurrency).getTarget()));

        return result;
    }

	
}
