package acme.features.administrator.dashboard;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronage.Status;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard>{
	
	@Autowired
	protected AdministratorDashboardRepository repository;

	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;
		boolean result;

		result = request.getPrincipal().hasRole(Administrator.class);

		return result;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		
		final AdministratorDashboard result = new AdministratorDashboard();
		
		Integer							  	numberOfComponents;
		final Map<Pair<String, String>, Double> averageRetailPriceOfComponentsByTechnologyAndCurrency;
		final Map<Pair<String, String>, Double> deviationRetailPriceOfComponentsByTechnologyAndCurrency;
		final Map<Pair<String, String>, Double> minRetailPriceOfComponentsByTechnologyAndCurrency;
		final Map<Pair<String, String>, Double> maxRetailPriceOfComponentsByTechnologyAndCurrency;
		final Integer							  numberOfTools;
		final Map<String, Double>				  averageRetailPriceOfToolsByCurrency;
		final Map<String, Double>				  deviationRetailPriceOfToolsByCurrency;
		final Map<String, Double>				  minRetailPriceOfToolsByCurrency;
		final Map<String, Double>			      maxRetailPriceOfToolsByCurrency;
		final Map<Status, Integer>			 	  numberOfPatronagesByStatus;
		final Map<Status, Double>				  averageBudgetOfPatronagesByStatus;
		final Map<Status, Double>				  deviationBudgetOfPatronagesByStatus;
		final Map<Status, Double>				  minBudgetOfPatronagesByStatus;
		final Map<Status, Double>				  maxBudgetOfPatronagesByStatus;
		
		numberOfComponents = this.repository.numberOfComponents();
		
		numberOfPatronagesByStatus = new HashMap<Status, Integer>();
		final Integer numberOfProposedPatronages = this.repository.numberOfPatronagesByStatus(Status.PROPOSED);
		final Integer numberOfAcceptedPatronages = this.repository.numberOfPatronagesByStatus(Status.ACCEPTED);
		final Integer numberOfDeniedPatronages = this.repository.numberOfPatronagesByStatus(Status.DENIED);
		numberOfPatronagesByStatus.put(Status.PROPOSED, numberOfProposedPatronages);
		numberOfPatronagesByStatus.put(Status.ACCEPTED, numberOfAcceptedPatronages);
		numberOfPatronagesByStatus.put(Status.DENIED, numberOfDeniedPatronages);
		
		averageBudgetOfPatronagesByStatus = new HashMap<Status, Double>();
		final Double averageBudgetOfProposedPatronages = this.repository.averageBudgetOfPatronagesByStatus(Status.PROPOSED);
		final Double averageBudgetOfAcceptedPatronages = this.repository.averageBudgetOfPatronagesByStatus(Status.ACCEPTED);
		final Double averageBudgetOfDeniedPatronages = this.repository.averageBudgetOfPatronagesByStatus(Status.DENIED);
		averageBudgetOfPatronagesByStatus.put(Status.PROPOSED, averageBudgetOfProposedPatronages);
		averageBudgetOfPatronagesByStatus.put(Status.ACCEPTED, averageBudgetOfAcceptedPatronages);
		averageBudgetOfPatronagesByStatus.put(Status.DENIED, averageBudgetOfDeniedPatronages);
		
		deviationBudgetOfPatronagesByStatus = new HashMap<Status, Double>();
		final Double deviationBudgetOfProposedPatronages = this.repository.deviationBudgetOfPatronagesByStatus(Status.PROPOSED);
		final Double deviationBudgetOfAcceptedPatronages = this.repository.deviationBudgetOfPatronagesByStatus(Status.ACCEPTED);
		final Double deviationBudgetOfDeniedPatronages = this.repository.deviationBudgetOfPatronagesByStatus(Status.DENIED);
		deviationBudgetOfPatronagesByStatus.put(Status.PROPOSED, deviationBudgetOfProposedPatronages);
		deviationBudgetOfPatronagesByStatus.put(Status.ACCEPTED, deviationBudgetOfAcceptedPatronages);
		deviationBudgetOfPatronagesByStatus.put(Status.DENIED, deviationBudgetOfDeniedPatronages);
		
		minBudgetOfPatronagesByStatus = new HashMap<Status, Double>();
		final Double minBudgetOfProposedPatronages = this.repository.minBudgetOfPatronagesByStatus(Status.PROPOSED);
		final Double minBudgetOfAcceptedPatronages = this.repository.minBudgetOfPatronagesByStatus(Status.ACCEPTED);
		final Double minBudgetOfDeniedPatronages = this.repository.minBudgetOfPatronagesByStatus(Status.DENIED);
		minBudgetOfPatronagesByStatus.put(Status.PROPOSED, minBudgetOfProposedPatronages);
		minBudgetOfPatronagesByStatus.put(Status.ACCEPTED, minBudgetOfAcceptedPatronages);
		minBudgetOfPatronagesByStatus.put(Status.DENIED, minBudgetOfDeniedPatronages);
		
		maxBudgetOfPatronagesByStatus = new HashMap<Status, Double>();
		final Double maxBudgetOfProposedPatronages = this.repository.maxBudgetOfPatronagesByStatus(Status.PROPOSED);
		final Double maxBudgetOfAcceptedPatronages = this.repository.maxBudgetOfPatronagesByStatus(Status.ACCEPTED);
		final Double maxBudgetOfDeniedPatronages = this.repository.maxBudgetOfPatronagesByStatus(Status.DENIED);
		maxBudgetOfPatronagesByStatus.put(Status.PROPOSED, maxBudgetOfProposedPatronages);
		maxBudgetOfPatronagesByStatus.put(Status.ACCEPTED, maxBudgetOfAcceptedPatronages);
		maxBudgetOfPatronagesByStatus.put(Status.DENIED, maxBudgetOfDeniedPatronages);
		
		numberOfTools = this.repository.numberOfTools();
		
		
		averageRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		final Double averageEURRetailPriceOfTools =  this.repository.averageRetailPriceOfToolsByCurrency("EUR");
		final Double averageGBPRetailPriceOfTools = this.repository.averageRetailPriceOfToolsByCurrency("GBP");
		final Double averagUSDRetailPriceOfTools = this.repository.averageRetailPriceOfToolsByCurrency("USD");
		averageRetailPriceOfToolsByCurrency.put("EUR", averageEURRetailPriceOfTools);
		averageRetailPriceOfToolsByCurrency.put("GBP", averageGBPRetailPriceOfTools);
		averageRetailPriceOfToolsByCurrency.put("USD", averagUSDRetailPriceOfTools);
		
		deviationRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		final Double deviationEURRetailPriceOfTools = this.repository.deviationRetailPriceOfToolsByCurrency("EUR");
		final Double deviationGBPRetailPriceOfTools = this.repository.deviationRetailPriceOfToolsByCurrency("GBP");
		final Double deviationUSDRetailPriceOfTools = this.repository.deviationRetailPriceOfToolsByCurrency("USD");
		deviationRetailPriceOfToolsByCurrency.put("EUR", deviationEURRetailPriceOfTools);
		deviationRetailPriceOfToolsByCurrency.put("GBP", deviationGBPRetailPriceOfTools);
		deviationRetailPriceOfToolsByCurrency.put("USD", deviationUSDRetailPriceOfTools);
		
		minRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		final Double minEURRetailPriceOfTools = this.repository.minRetailPriceOfToolsByCurrency("EUR");
		final Double minGBPRetailPriceOfTools = this.repository.minRetailPriceOfToolsByCurrency("GBP");
		final Double minUSDRetailPriceOfTools = this.repository.minRetailPriceOfToolsByCurrency("USD");
		minRetailPriceOfToolsByCurrency.put("EUR", minEURRetailPriceOfTools);
		minRetailPriceOfToolsByCurrency.put("GBP", minGBPRetailPriceOfTools);
		minRetailPriceOfToolsByCurrency.put("USD", minUSDRetailPriceOfTools);
		
		maxRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		final Double maxEURRetailPriceOfTools = this.repository.maxRetailPriceOfToolsByCurrency("EUR");
		final Double maxGBPRetailPriceOfTools = this.repository.maxRetailPriceOfToolsByCurrency("GBP");
		final Double maxUSDRetailPriceOfTools = this.repository.maxRetailPriceOfToolsByCurrency("USD");
		maxRetailPriceOfToolsByCurrency.put("EUR", maxEURRetailPriceOfTools);
		maxRetailPriceOfToolsByCurrency.put("GBP", maxGBPRetailPriceOfTools);
		maxRetailPriceOfToolsByCurrency.put("USD", maxUSDRetailPriceOfTools);
		
		averageRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();	
		this.repository.averageRetailPriceOfComponentsByTechnologyAndCurrency("EUR").stream()
		.forEach(x->
		averageRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("EUR", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.averageRetailPriceOfComponentsByTechnologyAndCurrency("GBP").stream()
		.forEach(x->
		averageRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("GBP", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.averageRetailPriceOfComponentsByTechnologyAndCurrency("USD").stream()
		.forEach(x->
		averageRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("USD", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		
		deviationRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();	
		this.repository.deviationRetailPriceOfComponentsByTechnologyAndCurrency("EUR").stream()
		.forEach(x->
		deviationRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("EUR", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.deviationRetailPriceOfComponentsByTechnologyAndCurrency("GBP").stream()
		.forEach(x->
		deviationRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("GBP", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.deviationRetailPriceOfComponentsByTechnologyAndCurrency("USD").stream()
		.forEach(x->
		deviationRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("USD", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		
		minRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();	
		this.repository.minRetailPriceOfComponentsByTechnologyAndCurrency("EUR").stream()
		.forEach(x->
		minRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("EUR", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.minRetailPriceOfComponentsByTechnologyAndCurrency("GBP").stream()
		.forEach(x->
		minRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("GBP", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.minRetailPriceOfComponentsByTechnologyAndCurrency("USD").stream()
		.forEach(x->
		minRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("USD", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		
		maxRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();	
		this.repository.maxRetailPriceOfComponentsByTechnologyAndCurrency("EUR").stream()
		.forEach(x->
		maxRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("EUR", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.maxRetailPriceOfComponentsByTechnologyAndCurrency("GBP").stream()
		.forEach(x->
		maxRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("GBP", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		this.repository.maxRetailPriceOfComponentsByTechnologyAndCurrency("USD").stream()
		.forEach(x->
		maxRetailPriceOfComponentsByTechnologyAndCurrency.put(
				Pair.of("USD", x.get(0).toString()),
				Double.parseDouble(x.get(1).toString()))
			);
		
		
		
		result.setNumberOfComponents(numberOfComponents);
		result.setAverageRetailPriceOfComponentsByTechnologyAndCurrency(averageRetailPriceOfComponentsByTechnologyAndCurrency);
		result.setDeviationRetailPriceOfComponentsByTechnologyAndCurrency(deviationRetailPriceOfComponentsByTechnologyAndCurrency);
		result.setMinRetailPriceOfComponentsByTechnologyAndCurrency(minRetailPriceOfComponentsByTechnologyAndCurrency);
		result.setMaxRetailPriceOfComponentsByTechnologyAndCurrency(maxRetailPriceOfComponentsByTechnologyAndCurrency);
		result.setNumberOfTools(numberOfTools);
		result.setAverageRetailPriceOfToolsByCurrency(averageRetailPriceOfToolsByCurrency);
		result.setDeviationRetailPriceOfToolsByCurrency(deviationRetailPriceOfToolsByCurrency);
		result.setMinRetailPriceOfToolsByCurrency(minRetailPriceOfToolsByCurrency);
		result.setMaxRetailPriceOfToolsByCurrency(maxRetailPriceOfToolsByCurrency);
		result.setNumberOfPatronagesByStatus(numberOfPatronagesByStatus);
		result.setAverageBudgetOfPatronagesByStatus(averageBudgetOfPatronagesByStatus);
		result.setDeviationBudgetOfPatronagesByStatus(deviationBudgetOfPatronagesByStatus);
		result.setMinBudgetOfPatronagesByStatus(minBudgetOfPatronagesByStatus);
		result.setMaxBudgetOfPatronagesByStatus(maxBudgetOfPatronagesByStatus);
		
		
		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"numberOfComponents",
									 "averageRetailPriceOfComponentsByTechnologyAndCurrency",
									"deviationRetailPriceOfComponentsByTechnologyAndCurrency",
									"minRetailPriceOfComponentsByTechnologyAndCurrency",
									"maxRetailPriceOfComponentsByTechnologyAndCurrency",
									"numberOfTools",
									"averageRetailPriceOfToolsByCurrency",
									"deviationRetailPriceOfToolsByCurrency",
									"minRetailPriceOfToolsByCurrency",
									"maxRetailPriceOfToolsByCurrency",
									"numberOfPatronagesByStatus",
									"averageBudgetOfPatronagesByStatus",
									"deviationBudgetOfPatronagesByStatus",
									"minBudgetOfPatronagesByStatus",
									"maxBudgetOfPatronagesByStatus");
		
	}

}
