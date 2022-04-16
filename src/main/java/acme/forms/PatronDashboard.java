package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.patronage.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Map<Status, Integer>			  numberOfPatronagesByStatus;
	Map<Pair<Status, String>, Double> averageNumberOfBudgetsByCurrencyAndStatus;
	Map<Pair<Status, String>, Double> deviationOfBudgetsByCurrencyAndStatus;
	Map<Pair<Status, String>, Double> minBudgetByCurrencyAndStatus;
	Map<Pair<Status, String>, Double> maxBudgetByCurrencyAndStatus;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
}
