package acme.features.patron.dashboard;

import org.springframework.data.jpa.repository.Query;

import acme.framework.repositories.AbstractRepository;

public interface PatronDashboardRepository extends AbstractRepository{
	
	@Query("SELECT count(p) FROM Patronage p WHERE p.status = 'PROPOSED")
	Integer numberOfProposedPatronages();
	
	@Query("SELECT count(p) FROM Patronage p WHERE p.status = 'PROPOSED")
	Integer numberOfAcceptedPatronages();
	
	@Query("SELECT count(p) FROM Patronage p WHERE p.status = 'PROPOSED")
	Integer numberOfDeniedPatronages();

	@Query("SELECT avg(p.budget) FROM Patronage p WHERE p.status = 'PROPOSED' GROUP BY p.budget.currency")
	Double averageNumberOfBudgetsProposedByCurrency();
	
	@Query("SELECT avg(p.budget) FROM Patronage p WHERE p.status = 'ACCEPTED' GROUP BY p.budget.currency")
	Double averageNumberOfBudgetsAccetedByCurrency();
	
	@Query("SELECT avg(p.budget) FROM Patronage p WHERE p.status = 'DENIED' GROUP BY p.budget.currency")
	Double averageNumberOfBudgetsDeniedByCurrency();
	
	@Query("SELECT stddev(p.budget) FROM Patronage p WHERE p.status = 'PORPOSED' GROUP BY p.budget.currency")
	Double deviationOfBudgetsProposedByCurrency();
	
	@Query("SELECT stddev(p.budget) FROM Patronage p WHERE p.status = 'ACCEPTED' GROUP BY p.budget.currency")
	Double deviationOfBudgetsAcceptedByCurrency();
	
	@Query("SELECT stddev(p.budget) FROM Patronage p WHERE p.status = 'DENIED' GROUP BY p.budget.currency")
	Double deviationOfBudgetsDeniedByCurrency();
	
	@Query("SELECT min(p.budget) FROM Patronage p WHERE p.status = 'PORPOSED' GROUP BY p.budget.currency")
	Double minBudgetProposedByCurrency();
	
	@Query("SELECT min(p.budget) FROM Patronage p WHERE p.status = 'ACCEPTED' GROUP BY p.budget.currency")
	Double minBudgetAcceptedByCurrency();
	
	@Query("SELECT min(p.budget) FROM Patronage p WHERE p.status = 'DENIED' GROUP BY p.budget.currency")
	Double minBudgetDeniedByCurrency();
	
	@Query("SELECT max(p.budget) FROM Patronage p WHERE p.status = 'PORPOSED' GROUP BY p.budget.currency")
	Double maxBudgetProposedByCurrency();
	
	@Query("SELECT max(p.budget) FROM Patronage p WHERE p.status = 'ACCEPTED' GROUP BY p.budget.currency")
	Double maxBudgetAcceptedByCurrency();
	
	@Query("SELECT max(p.budget) FROM Patronage p WHERE p.status = 'DENIED' GROUP BY p.budget.currency")
	Double maxBudgetDeniedByCurrency();
}
