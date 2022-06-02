package acme.testing.authenticated.patron;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedPatronUpdateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/patron/positive-update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final String username, final String password, final String company, final String  statement, final String link) {
		
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "Patron data");
		super.checkFormExists();
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Update");
		
		super.clickOnMenu("Account","Patron data");
		super.checkFormExists();
		
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
		super.checkLinkExists("Sign in");
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/patron/negative-update.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negative(final String username, final String password, final String company, final String  statement, final String link) {
		
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "Patron data");
		super.checkFormExists();
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
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
		
		super.checkNotLinkExists("Account");
		super.navigate("/authenticated/patron/update");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/authenticated/patron/update");
		super.checkPanicExists();
		super.signOut();

		super.signIn("inventor2", "inventor2");
		super.navigate("/authenticated/patron/update");
		super.checkPanicExists();
		super.signOut();
		
		
		
		
	}
	

}
