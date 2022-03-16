package acme.forms;

import java.io.Serializable;

public class PatronDashboard implements Serializable{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						numberOfPatronagesByStatus;
	Double						averageNumberOfBudgetsByCurrencyAndStatus;
	Double						deviationOfBudgetsByCurrencyAndStatus;
	Double						minBudgetByCurrencyAndStatus;
	Double						maxBudgetByCurrencyAndStatus;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
