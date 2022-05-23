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

package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitUpdateTest extends TestHarness {
	
	private final Integer RECORD_INDEX_TOOLKIT_PRUEBA = 4;

	// Lifecycle management ---------------------------------------------------

	@BeforeEach
	public void setupBeforeUpdateInventorToolkitTest() {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();

		super.clickOnButton("Create");
		super.fillInputBoxIn("title", "UPDATE TEST TOOLKIT");
		super.fillInputBoxIn("description", "Descripción del update test");
		super.fillInputBoxIn("assemblyNotes", "Assembly notes del update test");
		super.fillInputBoxIn("link", "http:/linkdeprueba.com");
		super.clickOnSubmit("Create");
		
		super.signOut();
	}
	
	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String description, 
							final String assemblyNotes, final String link, final String inventor) {
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		
		super.sortListing(1, "asc");

		super.checkColumnHasValue(this.RECORD_INDEX_TOOLKIT_PRUEBA, 1, "UPDATE TEST TOOLKIT");
		super.clickOnListingRecord(this.RECORD_INDEX_TOOLKIT_PRUEBA);
		super.checkFormExists();
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Update");

		super.checkListingExists();
		
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, "false");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("inventor.fullName", inventor);
		super.checkInputBoxHasValue("published", "false");
		
		super.checkNotButtonExists("Publish");
		
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title, final String description, 
							final String assemblyNotes, final String link) {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();

		super.sortListing(1, "asc");

		super.checkColumnHasValue(this.RECORD_INDEX_TOOLKIT_PRUEBA, 1, "UPDATE TEST TOOLKIT");
		super.clickOnListingRecord(this.RECORD_INDEX_TOOLKIT_PRUEBA);
		super.checkFormExists();
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("assemblyNotes", assemblyNotes);
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
		// SUGERENCIA+ a) No se pude actualizar una toolkit cuyo inventor no sea su propietario.
		// SUGERENCIA+ b) No se puede actualizar una toolkit que este publicado.
		
		
	}

	// Ancillary methods ------------------------------------------------------

}