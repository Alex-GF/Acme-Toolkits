/*
 * EmployerJobPublishTest.java
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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.TestHarness;

public class InventorToolkitPublishTest extends TestHarness {

	private final Integer RECORD_INDEX_TOOLKIT_PRUEBA_POSITIVE = 1;
	private final Integer RECORD_INDEX_TOOLKIT_PRUEBA_NEGATIVE = 0;
	
	// Lifecycle management ---------------------------------------------------
	@BeforeAll
	public void createToolkitBeforeAllTests() {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();

		super.clickOnButton("Create");
		super.fillInputBoxIn("title", "PUBLISH TEST TOOLKIT");
		super.fillInputBoxIn("description", "Descripción del publish test");
		super.fillInputBoxIn("assemblyNotes", "Assembly notes del publish test");
		super.fillInputBoxIn("link", "http:/linkdeprueba.com");
		super.clickOnSubmit("Create");
		
		super.signOut();
	}
	// Test cases -------------------------------------------------------------

	@Test
	@Order(10)
	public void positiveTest() {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.checkColumnHasValue(this.RECORD_INDEX_TOOLKIT_PRUEBA_POSITIVE, 1, "Sample-Toolkit-01");

		super.clickOnListingRecord(this.RECORD_INDEX_TOOLKIT_PRUEBA_POSITIVE);
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		super.checkNotErrorsExist();

		super.signOut();
	}

	@Test
	@Order(20)
	public void negativeTest() {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.checkColumnHasValue(this.RECORD_INDEX_TOOLKIT_PRUEBA_NEGATIVE, 1, "PUBLISH TEST TOOLKIT");

		super.clickOnListingRecord(this.RECORD_INDEX_TOOLKIT_PRUEBA_NEGATIVE);
		super.checkFormExists();
		super.checkNotButtonExists("Publish");

		super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {
		/// SUGERENCIA: el framework no proporciona suficiente soporte para implementar este caso de hacking,
		// SUGERENCIA+ por lo que debe realizarse manualmente:
		// SUGERENCIA+ a) No se pude publicar una toolkit cuyo inventor no sea su propietario.
		// SUGERENCIA+ b) No se puede publicar una toolkit que este publicado.
	}

	// Ancillary methods ------------------------------------------------------

}