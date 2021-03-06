package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageUpdateTest extends TestHarness{
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String code, 
							final String legalStuff, final String budget, final String creationMoment, 
							final String startDate, final String finishDate, final String link) {
		
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "Patronage list");
		super.checkListingExists();

		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, code);
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		
		super.fillInputBoxIn("status", status);
		super.clickOnSubmit("Update status");
		
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		
		
		super.checkColumnHasValue(recordIndex, 0, code);

		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("status", status);

		super.signOut();
			
		
		
	}
	
	
	@Test
	@Order(20)
	public void hackingTest() {
		// SUGERENCIA: el framework no proporciona suficiente soporte para implementar este caso de hacking,
		// SUGERENCIA+ por lo que debe realizarse manualmente:
		// SUGERENCIA+ a) No se pude actualizar el estado de los patronage que no son de mi propiedad.
		
		super.checkNotLinkExists("Account");
		super.navigate("/invertor/patronage/update");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/invertor/patronage/update");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron2", "patron2");
		super.navigate("/invertor/patronage/update");
		super.checkPanicExists();
		super.signOut();
	}

}
