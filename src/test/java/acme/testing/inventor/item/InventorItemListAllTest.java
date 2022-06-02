package acme.testing.inventor.item;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorItemListAllTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String type, final String name, final String code, final String technology,
		final String description, final String retailPrice, final String link, final String published) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "My Items");
		super.checkListingExists();
		
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, technology);
		//super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.checkColumnHasValue(recordIndex, 4, type);
		super.checkColumnHasValue(recordIndex, 5, published);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		//super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("published", published);
		
		
		if(published.trim() == "false") {
			super.checkNotButtonExists("Toolkits");
		}
		if(published.trim() == "true") {
			super.checkButtonExists("Toolkits");
		}
		
		
		
	}
	@Test
	@Order(20)
	public void hackingTest() {
		// SUGERENCIA: el framework no proporciona suficiente soporte para implementar este caso de hacking,
		// SUGERENCIA+ por lo que debe realizarse manualmente:
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/item/list");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/inventor/item/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron2", "patron2");
		super.navigate("/inventor/item/list");
		super.checkPanicExists();
		super.signOut();
	}
	
}