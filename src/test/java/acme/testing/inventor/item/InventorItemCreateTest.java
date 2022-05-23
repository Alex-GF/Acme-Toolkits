/*
 * EmployerJobCreateTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemCreateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String type, final String name, final String technology, final String description, final String retailPrice, final String link, final String published) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My Items");
		super.checkListingExists();

		super.clickOnButton("Create");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("type", type);
		super.clickOnSubmit("Create");

		super.clickOnMenu("Inventor", "My Items");
		super.checkListingExists();
		
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 2, technology);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.checkColumnHasValue(recordIndex, 4, type);
		super.checkColumnHasValue(recordIndex, 5, published);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("published", published);
		
		
		super.checkNotButtonExists("Toolkits");

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String type, final String name, final String technology, final String description, final String retailPrice, final String link) {

		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My Items");
		super.checkListingExists();

		super.clickOnButton("Create");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("type", type);
		super.clickOnSubmit("Create");

		super.checkErrorsExist();

		super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/inventor/item/create");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/inventor/item/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron2", "patron2");
		super.navigate("/inventor/item/create");
		super.checkPanicExists();
		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
