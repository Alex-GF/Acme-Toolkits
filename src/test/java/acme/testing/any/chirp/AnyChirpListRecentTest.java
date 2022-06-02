package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyChirpListRecentTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String title, final String creationMoment, final String author, final String body, final String email) {
		
		super.clickOnMenu("Anonymous", "List the chirps");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);
		
	}
	
	@Test
    @Order(20)
    public void hackingTest() {
    	// Esto no se puede hackear puesto que los usuarios no registrados y registrados 
    	// pueden ver chirps.

    }
}
