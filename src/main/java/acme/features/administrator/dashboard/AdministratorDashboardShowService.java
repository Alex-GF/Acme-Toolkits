package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import acme.utils.DashboardLibrary;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard>{
	
	@Autowired
	protected AdministratorDashboardRepository repository;
	
	@Autowired
	protected DashboardLibrary library;

	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return request.getPrincipal().hasRole(Administrator.class);
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		
		final AdministratorDashboard result = new AdministratorDashboard();
		
		final Integer							  	numberOfComponents;
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
		
		final String[] acceptedCurrencies = this.repository.getAcceptedCurrencies().split(",");
		final List<String> currencies = new ArrayList<>();
		
		for(int i=0; i<acceptedCurrencies.length; i++) {
			currencies.add(acceptedCurrencies[i].trim());
		}
		
		numberOfComponents = this.repository.numberOfComponents();
		
		numberOfTools = this.repository.numberOfTools();
		
		numberOfPatronagesByStatus = new HashMap<Status, Integer>();
		for(int i=0; i<Status.values().length; i++) {
			numberOfPatronagesByStatus.put(Status.values()[i], this.repository.numberOfPatronagesByStatus(Status.values()[i]));
		}
		
		averageBudgetOfPatronagesByStatus = new HashMap<Status, Double>();
		for(int i=0; i<Status.values().length; i++) {
			averageBudgetOfPatronagesByStatus.put(Status.values()[i], this.repository.averageBudgetOfPatronagesByStatus(Status.values()[i]));
		}
		
		deviationBudgetOfPatronagesByStatus = new HashMap<Status, Double>();
		for(int i=0; i<Status.values().length; i++) {
			deviationBudgetOfPatronagesByStatus.put(Status.values()[i], this.repository.deviationBudgetOfPatronagesByStatus(Status.values()[i]));
		}
		
		minBudgetOfPatronagesByStatus = new HashMap<Status, Double>();
		for(int i=0; i<Status.values().length; i++) {
			minBudgetOfPatronagesByStatus.put(Status.values()[i], this.repository.minBudgetOfPatronagesByStatus(Status.values()[i]));
		}
		
		maxBudgetOfPatronagesByStatus = new HashMap<Status, Double>();
		for(int i=0; i<Status.values().length; i++) {
			maxBudgetOfPatronagesByStatus.put(Status.values()[i], this.repository.maxBudgetOfPatronagesByStatus(Status.values()[i]));
		}
		
		averageRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		for(int i=0; i<currencies.size(); i++) {
			averageRetailPriceOfToolsByCurrency.put(currencies.get(i), this.repository.averageRetailPriceOfToolsByCurrency(currencies.get(i)));
		}
		
		deviationRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		for(int i=0; i<currencies.size(); i++) {
			deviationRetailPriceOfToolsByCurrency.put(currencies.get(i), this.repository.deviationRetailPriceOfToolsByCurrency(currencies.get(i)));
		}
		
		minRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		for(int i=0; i<currencies.size(); i++) {
			minRetailPriceOfToolsByCurrency.put(currencies.get(i), this.repository.minRetailPriceOfToolsByCurrency(currencies.get(i)));
		}
		
		maxRetailPriceOfToolsByCurrency = new HashMap<String, Double>();
		for(int i=0; i<currencies.size(); i++) {
			maxRetailPriceOfToolsByCurrency.put(currencies.get(i), this.repository.maxRetailPriceOfToolsByCurrency(currencies.get(i)));
		}
		
		averageRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();	
		for(int i=0; i<currencies.size(); i++) {
			final int j = i;
			this.repository.averageRetailPriceOfComponentsByTechnologyAndCurrency(currencies.get(i)).stream()
			.forEach(x->
			averageRetailPriceOfComponentsByTechnologyAndCurrency.put(
					Pair.of(currencies.get(j), x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
				);
		}
		
		deviationRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();	
		for(int i=0; i<currencies.size(); i++) {
			final int j = i;
			this.repository.deviationRetailPriceOfComponentsByTechnologyAndCurrency(currencies.get(i)).stream()
			.forEach(x->
			deviationRetailPriceOfComponentsByTechnologyAndCurrency.put(
					Pair.of(currencies.get(j), x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
				);
		}
		
		minRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();	
		for(int i=0; i<currencies.size(); i++) {
			final int j = i;
			this.repository.minRetailPriceOfComponentsByTechnologyAndCurrency(currencies.get(i)).stream()
			.forEach(x->
			minRetailPriceOfComponentsByTechnologyAndCurrency.put(
					Pair.of(currencies.get(j), x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
				);
		}
		
		maxRetailPriceOfComponentsByTechnologyAndCurrency = new HashMap<Pair<String, String>, Double>();	
		for(int i=0; i<currencies.size(); i++) {
			final int j = i;
			this.repository.maxRetailPriceOfComponentsByTechnologyAndCurrency(currencies.get(i)).stream()
			.forEach(x->
			maxRetailPriceOfComponentsByTechnologyAndCurrency.put(
					Pair.of(currencies.get(j), x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
				);
		}
		
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
		
		request.unbind(entity, model, //
			"numberOfComponents","averageRetailPriceOfComponentsByTechnologyAndCurrency", //
			"deviationRetailPriceOfComponentsByTechnologyAndCurrency", //
			"minRetailPriceOfComponentsByTechnologyAndCurrency", //
			"maxRetailPriceOfComponentsByTechnologyAndCurrency", //
			"numberOfTools", "averageRetailPriceOfToolsByCurrency", //
			"deviationRetailPriceOfToolsByCurrency", "minRetailPriceOfToolsByCurrency", //
			"maxRetailPriceOfToolsByCurrency", "numberOfPatronagesByStatus", //
			"averageBudgetOfPatronagesByStatus", "deviationBudgetOfPatronagesByStatus", //
			"minBudgetOfPatronagesByStatus", "maxBudgetOfPatronagesByStatus");
		
		this.library.createDashboard(entity, model);
		
	}

}
