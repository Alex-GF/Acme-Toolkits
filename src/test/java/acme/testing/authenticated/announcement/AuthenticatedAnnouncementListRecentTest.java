package acme.testing.authenticated.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedAnnouncementListRecentTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/announcement/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String title, final String creationMoment, final String criticalFlag, final String body, final String link) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Authenticated", "List the announcements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, creationMoment);
		super.checkColumnHasValue(recordIndex, 2, criticalFlag);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("criticalFlag", criticalFlag);
		super.checkInputBoxHasValue("body", body);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		// SUGERENCIA: el framework no proporciona suficiente soporte para implementar este caso de hacking,
		// SUGERENCIA+ por lo que debe realizarse manualmente:
		// SUGERENCIA+ a) mostrar un announcement anterior a un mes;
		// SUGERENCIA+ b) mostrar announcement si no estas autenticado;
    	// SUGERENCIA+ c) listar announcements si no estas autenticado;
		super.checkNotLinkExists("Account");
		super.navigate("/authenticated/announcement/list-recent");
		super.checkPanicExists();
	}
	
}
