package acme.forms;

import java.io.Serializable;

public class PatronDashboard implements Serializable{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						numberOfPatronagesByStatus;
	Double						averageNumberOfBudgetsProposedByCurrency;
	Double						averageNumberOfBudgetsAccetedByCurrency;
	Double						averageNumberOfBudgetsDeniedByCurrency;
	Double						deviationOfBudgetsProposedByCurrency;
	Double						deviationOfBudgetsAcceptedByCurrency;
	Double						deviationOfBudgetsDeniedByCurrency;
	Double						minBudgetProposedByCurrency;
	Double						minBudgetAcceptedByCurrency;
	Double						minBudgetDeniedByCurrency;
	Double						maxBudgetProposedByCurrency;
	Double						maxBudgetAcceptedByCurrency;
	Double						maxBudgetDeniedByCurrency;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
