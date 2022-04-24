package acme.testing.any.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class AnyItemListAllTest extends TestHarness{
	
	@BeforeEach
	public void setUp() {
		super.clickOnMenu("Anonymous", "List all toolkit");
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String type, final String name, final String code, final String technology,
		final String description, final String retailPrice, final String link, final String inventor) {
		
		super.clickOnMenu("Anonymous", "List all items");
		super.checkListingExists();
		
		super.sortListing(0, "desc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, technology);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.checkColumnHasValue(recordIndex, 4, type);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("type", type);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("description", description);
		
		
		super.checkButtonExists("Toolkits");
		
		
		
	}
	
	
}