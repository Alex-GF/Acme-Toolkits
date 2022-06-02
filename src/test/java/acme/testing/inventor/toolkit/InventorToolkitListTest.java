package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitListTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String code, final String title, 
						final String totalPrice, final String description, final String assemblyNotes,
						final String link, final String inventor, final String published) {
		
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, published);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		//super.checkInputBoxHasValue("totalPrice", totalPrice);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("inventor.fullName", inventor);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("published", published);
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		// SUGERENCIA: el framework no proporciona suficiente soporte para implementar este caso de hacking,
    	// SUGERENCIA+ por lo que debe realizarse manualmente:
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/toolkit/list-mine");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/inventor/toolkit/list-mine");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron2", "patron2");
		super.navigate("/inventor/toolkit/list-mine");
		super.checkPanicExists();
		super.signOut();
	}
}
