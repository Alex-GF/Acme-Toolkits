/*
 * EmployerJobUpdateTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageUpdateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,final String code,  final String legalStuff, final String budget, final String startDate, final String finishDate, final String link) {
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Patronage list");
		super.checkListingExists();

		super.sortListing(1, "desc");
		
		super.checkColumnHasValue(0, 0, code);
		super.checkColumnHasValue(0, 4, "false");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");

		super.checkListingExists();
		
		super.sortListing(1, "desc");
		
		super.checkColumnHasValue(0, 1, budget);
		super.checkColumnHasValue(0, 4, "false");
		
		super.clickOnListingRecord(0);
		super.checkFormExists();
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("link", link);


		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String legalStuff, final String budget, final String startDate, final String finishDate, final String link) {
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "Patronage list");
		super.checkListingExists();

		super.sortListing(1, "desc");
		
		super.checkColumnHasValue(0, 1, budget);
		super.checkColumnHasValue(0, 4, "false");
		super.clickOnListingRecord(0);
		super.checkFormExists();
		
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startDate", startDate);
		super.fillInputBoxIn("finishDate", finishDate);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");
		
		super.checkErrorsExist();

		super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {
		
		// SUGERENCIA: el framework no proporciona suficiente soporte para implementar este caso de hacking,
		// SUGERENCIA+ por lo que debe realizarse manualmente:
		// SUGERENCIA+ a) No se pude actualizar un item cuyo patron no sea su propietario.
		// SUGERENCIA+ b) No se puede actualizar un item que este publicado.
		
		
	}

	// Ancillary methods ------------------------------------------------------

}
