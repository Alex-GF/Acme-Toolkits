package acme.testing.authenticated.patron;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedPatronCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/sign-up/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positive(final String username, final String password, final String name, final String surname, final String email, final String company, final String  statement, final String link) {
		super.signUp(username, password, name, surname, email);
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "Become a patron");
		super.checkFormExists();
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Register");
		
		super.clickOnMenu("Account","Patron data");
		super.checkFormExists();
		
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("link", link);
	
		super.signOut();
		super.checkLinkExists("Sign in");
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/patron/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negative(final String username, final String password, final String name, final String surname, final String email, final String company, final String  statement, final String link) {
		
		super.signUp(username, password, name, surname, email);
		super.signIn(username, password);
		
		super.clickOnMenu("Account", "Become a patron");
		super.checkFormExists();
		
		super.fillInputBoxIn("company", company);
		super.fillInputBoxIn("statement", statement);
		super.fillInputBoxIn("link", link);
		
		super.clickOnSubmit("Register");
		super.checkErrorsExist();

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		
		
		
		super.checkNotLinkExists("Account");
		super.navigate("/authenticated/patron/create");
		super.checkPanicExists();

		super.signIn("patron2", "patron2");
		super.navigate("/authenticated/patron/create");
		super.checkPanicExists();
		super.signOut();
		
	}
	
	

}
