package acme.testing.administrator.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorConfigurationShowTest extends TestHarness{

    @ParameterizedTest
    @CsvFileSource(resources = "/administrator/configuration/show.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void positive(final int recordIndex, final String defaultCurrency, final String acceptedCurrencies,
            final String strongSpamWords, final String strongSpamThreshold, final String weakSpamWords,
            final String weakSpamThreshold) {
        super.signIn("administrator", "administrator");

        super.clickOnMenu("Administrator", "System configuration");
        
        super.checkFormExists();
        super.checkInputBoxHasValue("defaultCurrency", defaultCurrency);
        super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
        super.checkInputBoxHasValue("strongSpamWords", strongSpamWords);
        super.checkInputBoxHasValue("strongSpamThreshold", strongSpamThreshold);
        super.checkInputBoxHasValue("weakSpamWords", weakSpamWords);
        super.checkInputBoxHasValue("weakSpamThreshold", weakSpamThreshold);

        super.signOut();
    }
    
    @Test
    @Order(20)
    public void hackingTest() {
    	// SUGERENCIA: el framework no proporciona suficiente soporte para implementar este caso de hacking,
		// SUGERENCIA+ por lo que debe realizarse manualmente:
		// SUGERENCIA+ d) ver la configuracion si no estas autenticado;
		super.checkNotLinkExists("Account");
		super.navigate("/administrator/configuration/show");
		super.checkPanicExists();
		// SUGERENCIA+ e) ver configuracion si no eres administrador;
		super.signIn("inventor1", "inventor1");
		super.navigate("/administrator/configuration/show");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron2", "patron2");
		super.navigate("/administrator/configuration/show");
		super.checkPanicExists();
		super.signOut();
		
		
    }
}
