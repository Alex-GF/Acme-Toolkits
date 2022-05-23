package acme.features.patron.patronage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Patronage;
import acme.entities.patronage.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;
import acme.utils.AcceptedCurrencyLibrary;
import acme.utils.GenerateCodeLibrary;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage>{

	@Autowired
	protected PatronPatronageRepository patronPatronageRepository;
	
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return request.getPrincipal().hasRole(Patron.class);
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity,errors,"status", "code", "legalStuff", "budget", "creationMoment", "startDate", "finishDate","link");
		
		final Inventor inventor = this.patronPatronageRepository.getInventorByInventorId(request.getModel().getInteger("inventor"));
		
		entity.setInventor(inventor);
		
		
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final List<Inventor> list = this.patronPatronageRepository.getInventors();
		model.setAttribute("inventorsList", list);
		
		request.unbind(entity,model,"status", "code", "legalStuff", "budget", "creationMoment", "startDate", "finishDate","link", "published");
		
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;

		final Patron patron;
		final Patronage result;
		Date moment;

		final int userAccountId = request.getPrincipal().getAccountId();
		patron = this.patronPatronageRepository.findPatronByUserAccountId(userAccountId);

		final List<String> codes = this.patronPatronageRepository.findAllCodes();
		final String code = GenerateCodeLibrary.generateCode(codes,"^[A-Z]{3}-[0-9]{3}(-[A-Z])?$");

		
		
		moment = new Date(System.currentTimeMillis() - 1);
		
		result = new Patronage();
		result.setCode(code);
		result.setCreationMoment(moment);
		result.setPatron(patron);
		result.setPublished(false);
		result.setStatus(Status.PROPOSED);



		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final List<String> acceptedCurrencies = AcceptedCurrencyLibrary.getAcceptedCurrencies(this.patronPatronageRepository.findAcceptedCurrencies());

		
		if (!errors.hasErrors("startDate")) {
			
			final Calendar calendar = Calendar.getInstance();
			Date minimumPeriodStart;
			
			calendar.setTime(entity.getCreationMoment());
			calendar.add(Calendar.MONTH, 1);
			minimumPeriodStart = calendar.getTime();
			
			
			errors.state(request, entity.getStartDate().after(minimumPeriodStart), "startDate", "patron.patronage.form.error.acceptedPeriodTime.start");
			
		}
		
		if (!errors.hasErrors("finishDate")) {
			
			final Calendar calendar = Calendar.getInstance();
			Date minimumPeriodFinish;
			
			calendar.setTime(entity.getStartDate());
			calendar.add(Calendar.MONTH, 1);
			minimumPeriodFinish = calendar.getTime();
			
			
			errors.state(request, entity.getFinishDate().after(minimumPeriodFinish), "finishDate", "patron.patronage.form.error.acceptedPeriodTime.finish");
			
		}


		if(!errors.hasErrors("budget")) {
			boolean acceptedCurrency;
			
			acceptedCurrency = acceptedCurrencies.contains(entity.getBudget().getCurrency());
			
			errors.state(request, acceptedCurrency, "budget", "patron.patronage.form.error.acceptedCurrency");
			
			boolean positiveValue;
			
			positiveValue = entity.getBudget().getAmount()>0;
			
			errors.state(request, positiveValue, "budget", "patron.patronage.form.error.positiveValue");
			
		}
		
		if (errors.hasErrors("code")) {
			Patronage existing;
			
			existing = this.patronPatronageRepository.getPatronageByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "patron.patronage.form.error.duplicated");
		}
		
	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(false);
		
		this.patronPatronageRepository.save(entity);

	}
		
	
	
	

}
