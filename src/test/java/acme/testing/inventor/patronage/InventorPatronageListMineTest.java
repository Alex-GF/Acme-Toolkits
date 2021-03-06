package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageListMineTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String status, final String code, 
						final String legalStuff, final String budget, final String creationMoment, 
						final String startDate, final String finishDate, final String link,
						final String name, final String surname, final String email,
						final String roleList) {
		
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "Patronage list");
		super.checkListingExists();
		super.sortListing(3, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		//super.checkColumnHasValue(recordIndex, 1, budget);
		super.checkColumnHasValue(recordIndex, 2, creationMoment);
		super.checkColumnHasValue(recordIndex, 3, status);


		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		//super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("link", link);
		
		super.clickOnButton("Patron");
		super.checkFormExists();
		super.checkInputBoxHasValue("identity.name", name);
		super.checkInputBoxHasValue("identity.surname", surname);
		super.checkInputBoxHasValue("identity.email", email);
		super.checkInputBoxHasValue("roleList", roleList);
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		// SUGERENCIA: el framework no proporciona suficiente soporte para implementar este caso de hacking,
		// SUGERENCIA+ por lo que debe realizarse manualmente:
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/patronage/list");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/inventor/patronage/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron2", "patron2");
		super.navigate("/inventor/patronage/list");
		super.checkPanicExists();
		super.signOut();
	}
}
