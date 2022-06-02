package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportListTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronageReport/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String memorandum, final String creationMoment, final String automaticSequenceNumber, final String patronage){
		
		super.signIn("inventor2", "inventor2");

		super.clickOnMenu("Inventor", "Patronage report list");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, memorandum);
		super.checkColumnHasValue(recordIndex, 2, automaticSequenceNumber);
		super.checkColumnHasValue(recordIndex, 3, patronage);
		
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("automaticSequenceNumber", automaticSequenceNumber);
		
		super.checkButtonExists("Patronage");
		
		super.signOut();
		
	}
	@Test
	@Order(20)
	public void hackingTest() {
		// SUGERENCIA: el framework no proporciona suficiente soporte para implementar este caso de hacking,
		// SUGERENCIA+ por lo que debe realizarse manualmente:
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/patronage-report/list");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/inventor/patronage-report/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron2", "patron2");
		super.navigate("/inventor/patronage-report/list");
		super.checkPanicExists();
		super.signOut();
	}
		
	

}
