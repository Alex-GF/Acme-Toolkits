package acme.testing.any.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyToolkitListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/toolkit/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String code, final String title ,final String totalPrice, final String description, 
		final String assemblyNotes,final String link, final String inventor) {
		
		super.clickOnMenu("Anonymous", "List all toolkit");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		//super.checkColumnHasValue(recordIndex, 2, totalPrice);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		//super.checkInputBoxHasValue("totalPrice", totalPrice);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("inventor.fullName", inventor);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		
		
	}
	@Test
    @Order(20)
    public void hackingTest() {
    	// Esto no se puede hackear puesto que los usuarios no registrados y registrados 
    	// pueden ver todas las toolkits.

    }
}
