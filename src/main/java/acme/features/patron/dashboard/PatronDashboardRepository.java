package acme.features.patron.dashboard;

import acme.framework.repositories.AbstractRepository;

public interface PatronDashboardRepository extends AbstractRepository{
	
	//TODO to be reviewed during third sprint
	
	//-----------------------------------------------------------------------------
	
	/*@Query("SELECT count(p) FROM Patronage p WHERE p.status = 'PROPOSED")
	Integer numberOfPatronagesByStatus();

	@Query("SELECT avg(p.budget) FROM Patronage p GROUP BY p.budget.currency, p.status")
	Double averageNumberOfBudgetsByCurrencyAndStatus();
	
	@Query("SELECT stddev(p.budget) FROM Patronage p GROUP BY p.budget.currency, p.status")
	Double deviationOfBudgetsByCurrencyAndStatus();
	
	@Query("SELECT min(p.budget) FROM Patronage p GROUP BY p.budget.currency, p.status")
	Double minBudgetByCurrencyAndStatus();
	
	@Query("SELECT max(p.budget) FROM Patronage p GROUP BY p.budget.currency, p.status")
	Double maxBudgetByCurrencyAndStatus();
	
	*/
}
