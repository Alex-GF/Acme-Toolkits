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

public class InventorToolkitDeleteTest extends TestHarness {

	private final Integer RECORD_INDEX_TOOLKIT_PRUEBA = 0;
	
	// Lifecycle management ---------------------------------------------------
	
	@BeforeAll
	public void createToolkitBeforeAllTests() {
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "My Toolkits");
		super.checkListingExists();

		super.clickOnButton("Create");
		super.fillInputBoxIn("title", "DELETE TEST TOOLKIT");
		super.fillInputBoxIn("description", "Descripción del delete test");
		super.fillInputBoxIn("assemblyNotes", "Assembly notes del delete test");
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
		super.checkColumnHasValue(this.RECORD_INDEX_TOOLKIT_PRUEBA, 1, "DELETE TEST TOOLKIT");

		super.clickOnListingRecord(this.RECORD_INDEX_TOOLKIT_PRUEBA);
		super.checkFormExists();
		super.clickOnSubmit("Delete");
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
		super.checkColumnHasValue(this.RECORD_INDEX_TOOLKIT_PRUEBA, 1, "Sample-Toolkit-01");

		super.clickOnListingRecord(this.RECORD_INDEX_TOOLKIT_PRUEBA);
		super.checkFormExists();
		super.checkNotButtonExists("Delete");

		super.signOut();
	}

	@Test
	@Order(30)
	public void hackingTest() {
		// SUGERENCIA: el framework no proporciona suficiente soporte para implementar este caso de hacking,
		// SUGERENCIA+ por lo que debe realizarse manualmente:
		// SUGERENCIA+ a) No se pude borrar una toolkit cuyo inventor no sea su propietario.
		// SUGERENCIA+ b) No se puede borrar una toolkit que este publicado.
		
	}

	// Ancillary methods ------------------------------------------------------

}