package acme.features.patron.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Status;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;
import acme.utils.DashboardLibrary;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	@Autowired
	protected PatronDashboardRepository repository;
	
	@Autowired
	protected DashboardLibrary library;

	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;

		return request.getPrincipal().hasRole(Patron.class);
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;

		final PatronDashboard result = new PatronDashboard();
		
		final Map<Status, Integer> numberOfPatronagesByStatus;
		final Map<Pair<Status, String>, Double> averageNumberOfBudgetsByCurrencyAndStatus;
		final Map<Pair<Status, String>, Double> deviationOfBudgetsByCurrencyAndStatus;
		final Map<Pair<Status, String>, Double> minBudgetByCurrencyAndStatus;
		final Map<Pair<Status, String>, Double> maxBudgetByCurrencyAndStatus;

		numberOfPatronagesByStatus = new HashMap<Status, Integer>();
		for(int i=0; i<Status.values().length; i++) {
			numberOfPatronagesByStatus.put(Status.values()[i], this.repository.numberOfPatronagesByStatus(Status.values()[i]));
		}
		
		averageNumberOfBudgetsByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();	
		for(int i=0; i<Status.values().length; i++) {
			final int j = i;
			this.repository.averageNumberOfBudgetsByCurrencyAndStatus(Status.values()[i]).stream()
			.forEach(x->
				averageNumberOfBudgetsByCurrencyAndStatus.put(
					Pair.of(Status.values()[j], x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
				);
		}
		
		deviationOfBudgetsByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();	
		for(int i=0; i<Status.values().length; i++) {
			final int j = i;
			this.repository.deviationOfBudgetsByCurrencyAndStatus(Status.values()[i]).stream()
			.forEach(x->
				deviationOfBudgetsByCurrencyAndStatus.put(
					Pair.of(Status.values()[j], x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
				);
		}
		
		minBudgetByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();	
		for(int i=0; i<Status.values().length; i++) {
			final int j = i;
			this.repository.minBudgetByCurrencyAndStatus(Status.values()[i]).stream()
			.forEach(x->
				minBudgetByCurrencyAndStatus.put(
					Pair.of(Status.values()[j], x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
				);
		}
		
		maxBudgetByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();	
		for(int i=0; i<Status.values().length; i++) {
			final int j = i;
			this.repository.maxBudgetByCurrencyAndStatus(Status.values()[i]).stream()
			.forEach(x->
				maxBudgetByCurrencyAndStatus.put(
					Pair.of(Status.values()[j], x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
				);
		}

		result.setNumberOfPatronagesByStatus(numberOfPatronagesByStatus);
		result.setAverageNumberOfBudgetsByCurrencyAndStatus(averageNumberOfBudgetsByCurrencyAndStatus);
		result.setDeviationOfBudgetsByCurrencyAndStatus(deviationOfBudgetsByCurrencyAndStatus);
		result.setMinBudgetByCurrencyAndStatus(minBudgetByCurrencyAndStatus);
		result.setMaxBudgetByCurrencyAndStatus(maxBudgetByCurrencyAndStatus);
		
		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"numberOfPatronagesByStatus", "averageNumberOfBudgetsByCurrencyAndStatus", // 
			"deviationOfBudgetsByCurrencyAndStatus", "minBudgetByCurrencyAndStatus", //
			"maxBudgetByCurrencyAndStatus");
		
		this.library.createDashboard(entity, model);
		
	}

}
