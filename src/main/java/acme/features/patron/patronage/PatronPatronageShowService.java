package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;
import acme.utils.ChangeCurrencyLibrary;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage> {
    // Internal state ---------------------------------------------------------

    @Autowired
    protected PatronPatronageRepository repository;
    
    @Autowired
	protected ChangeCurrencyLibrary changeLibrary;

    @Override
    public boolean authorise(final Request<Patronage> request) {
        assert request != null;

        final boolean result;
		final int patronageId;
		final Patronage patronage;
		final Patron patron;
		
		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.getPatronageById(patronageId);
		patron = patronage.getPatron();
		result = request.isPrincipal(patron);
		
		return result;
    }

    @Override
    public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;
        
        final String fullName = entity.getInventor().getUserAccount().getIdentity().getFullName();
        model.setAttribute("fullName", fullName);
        
        final Money m = entity.getBudget();
        
        final String defaultCurrency = this.repository.findDefaultCurrency();
        
        if(!(entity.getBudget().getCurrency().equals(defaultCurrency))) {
        	entity.setBudget(this.changeLibrary.computeMoneyExchange(entity.getBudget(), defaultCurrency).getTarget());
        }
        
        request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationMoment", "startDate",
                "finishDate", "link","published", "inventor");
		
		if(!(m.getCurrency().equals(defaultCurrency))){
			model.setAttribute("showDefaultCurrency", true);
			model.setAttribute("defaultCurrency",m);
		}
        
    }

    @Override
    public Patronage findOne(final Request<Patronage> request) {
        assert request != null;

        Patronage result;
        int id;

        id = request.getModel().getInteger("id");
        result = this.repository.getPatronageById(id);
        
        return result;
    }

}
