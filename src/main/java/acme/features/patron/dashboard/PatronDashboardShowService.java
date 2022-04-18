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

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard> {

	@Autowired
	protected PatronDashboardRepository repository;


	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;
		boolean result;

		result = request.getPrincipal().hasRole(Patron.class);

		return result;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {
		assert request != null;

		final PatronDashboard result;
		Map<Status, Integer> numberOfPatronagesByStatus;
		Map<Pair<Status, String>, Double> averageNumberOfBudgetsByCurrencyAndStatus;
		Map<Pair<Status, String>, Double> deviationOfBudgetsByCurrencyAndStatus;
		Map<Pair<Status, String>, Double> minBudgetByCurrencyAndStatus;
		Map<Pair<Status, String>, Double> maxBudgetByCurrencyAndStatus;

		numberOfPatronagesByStatus = new HashMap<Status, Integer>();
		final Integer numberOfProposedPatronages = this.repository.numberOfPatronagesByStatus(Status.PROPOSED);
		final Integer numberOfAcceptedPatronages = this.repository.numberOfPatronagesByStatus(Status.ACCEPTED);
		final Integer numberOfDeniedPatronages = this.repository.numberOfPatronagesByStatus(Status.DENIED);
		numberOfPatronagesByStatus.put(Status.PROPOSED, numberOfProposedPatronages);
		numberOfPatronagesByStatus.put(Status.ACCEPTED, numberOfAcceptedPatronages);
		numberOfPatronagesByStatus.put(Status.DENIED, numberOfDeniedPatronages);
		
		averageNumberOfBudgetsByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();	
		this.repository.averageNumberOfBudgetsByCurrencyAndStatus(Status.PROPOSED).stream()
		.forEach(x->
			averageNumberOfBudgetsByCurrencyAndStatus.put(
				Pair.of(Status.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.averageNumberOfBudgetsByCurrencyAndStatus(Status.ACCEPTED).stream()
		.forEach(x->
			averageNumberOfBudgetsByCurrencyAndStatus.put(
				Pair.of(Status.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.averageNumberOfBudgetsByCurrencyAndStatus(Status.DENIED).stream()
		.forEach(x->
			averageNumberOfBudgetsByCurrencyAndStatus.put(
				Pair.of(Status.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		
		deviationOfBudgetsByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();	
		this.repository.deviationOfBudgetsByCurrencyAndStatus(Status.PROPOSED).stream()
		.forEach(x->
		deviationOfBudgetsByCurrencyAndStatus.put(
				Pair.of(Status.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.deviationOfBudgetsByCurrencyAndStatus(Status.ACCEPTED).stream()
		.forEach(x->
		deviationOfBudgetsByCurrencyAndStatus.put(
				Pair.of(Status.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.deviationOfBudgetsByCurrencyAndStatus(Status.DENIED).stream()
		.forEach(x->
		deviationOfBudgetsByCurrencyAndStatus.put(
				Pair.of(Status.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		
		minBudgetByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();	
		this.repository.minBudgetByCurrencyAndStatus(Status.PROPOSED).stream()
		.forEach(x->
		minBudgetByCurrencyAndStatus.put(
				Pair.of(Status.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.minBudgetByCurrencyAndStatus(Status.ACCEPTED).stream()
		.forEach(x->
		minBudgetByCurrencyAndStatus.put(
				Pair.of(Status.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.minBudgetByCurrencyAndStatus(Status.DENIED).stream()
		.forEach(x->
		minBudgetByCurrencyAndStatus.put(
				Pair.of(Status.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		
		maxBudgetByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();	
		this.repository.maxBudgetByCurrencyAndStatus(Status.PROPOSED).stream()
		.forEach(x->
		maxBudgetByCurrencyAndStatus.put(
				Pair.of(Status.PROPOSED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.maxBudgetByCurrencyAndStatus(Status.ACCEPTED).stream()
		.forEach(x->
		maxBudgetByCurrencyAndStatus.put(
				Pair.of(Status.ACCEPTED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.maxBudgetByCurrencyAndStatus(Status.DENIED).stream()
		.forEach(x->
		maxBudgetByCurrencyAndStatus.put(
				Pair.of(Status.DENIED, x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);

		result = new PatronDashboard();
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
	}

}
