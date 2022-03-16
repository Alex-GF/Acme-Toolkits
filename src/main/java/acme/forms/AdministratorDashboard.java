package acme.forms;

import java.io.Serializable;

public class AdministratorDashboard implements Serializable{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						numberOfComponents;
	Double						averageRetailPriceOfComponentsByTechnologyAndCurrency;
	Double						deviationRetailPriceOfComponentsByTechnologyAndCurrency;
	Double						minRetailPriceOfComponentsByTechnologyAndCurrency;
	Double						maxRetailPriceOfComponentsByTechnologyAndCurrency;
	Integer						numberOfTools;
	Double						averageRetailPriceOfToolsByCurrency;
	Double						deviationRetailPriceOfToolsByCurrency;
	Double						minRetailPriceOfToolsByCurrency;
	Double						maxRetailPriceOfToolsByCurrency;
	Integer						numberOfPatronagesByStatus;
	Double						averageBudgetOfPatronagesByStatus;
	Double						deviationBudgetOfPatronagesByStatus;
	Double						minBudgetOfPatronagesByStatus;
	Double						maxBudgetOfPatronagesByStatus;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
