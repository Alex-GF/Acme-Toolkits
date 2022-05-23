package acme.testing.administrator.announcement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorAnnouncementCreateTest extends TestHarness{
	
	 
    @ParameterizedTest
    @CsvFileSource(resources = "/administrator/announcement/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void positive(final int recordIndex, final String title, final String body,
            final String criticalFlag, final String link) {
        super.signIn("administrator", "administrator");

        super.clickOnMenu("Administrator", "Create an announcement");
        
        //final LocalDateTime now =LocalDateTime.now();
        //final String date = now.getYear()+"/"+(now.getMonthValue() < 10 ? "0"+ now.getMonthValue():now.getMonthValue())+"/"+now.getDayOfMonth()+" "+now.getHour()+":"+(now.getMinute() < 10 ? "0"+ now.getMinute():now.getMinute());
        super.fillInputBoxIn("title", title);
        super.fillInputBoxIn("body", body);
        super.fillInputBoxIn("criticalFlag", criticalFlag);
        super.fillInputBoxIn("link", link);
        super.fillInputBoxIn("confirmation", "true");
        super.clickOnSubmit("Create");
        
        super.clickOnMenu("Authenticated", "List the announcements");
        super.checkListingExists();
        super.sortListing(0, "asc");
        super.checkColumnHasValue(recordIndex, 0, title);
        //super.checkColumnHasValue(recordIndex, 1, date);
        super.checkColumnHasValue(recordIndex, 2, criticalFlag);
        super.clickOnListingRecord(recordIndex);
   
        
        super.checkFormExists();
        super.checkInputBoxHasValue("title", title);
        super.checkInputBoxHasValue("body", body);
        super.checkInputBoxHasValue("criticalFlag", criticalFlag);
        super.checkInputBoxHasValue("link", link);

        super.signOut();
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/administrator/announcement/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(20)
    public void negative(final int recordIndex, final String title, final String body,
            final String criticalFlag, final String link) {
        super.signIn("administrator", "administrator");

        super.clickOnMenu("Administrator", "Create an announcement");
        
        super.fillInputBoxIn("title", title);
        super.fillInputBoxIn("body", body);
        super.fillInputBoxIn("criticalFlag", criticalFlag);
        super.fillInputBoxIn("link", link);
        super.fillInputBoxIn("confirmation", "true");
        super.clickOnSubmit("Create");
    
        super.checkErrorsExist();
        super.signOut();
    }

    
    @Test
    @Order(30)
    public void hackingTest() {
    	// SUGERENCIA: el framework no proporciona suficiente soporte para implementar este caso de hacking,
		// SUGERENCIA+ por lo que debe realizarse manualmente:
		// SUGERENCIA+ d) crear un announcement si no estas autenticado;
		super.checkNotLinkExists("Account");
		super.navigate("/administrator/announcement/create");
		super.checkPanicExists();
		// SUGERENCIA+ e) crear announcement si no eres administrador;
		super.signIn("inventor1", "inventor1");
		super.navigate("/administrator/announcement/create");
		super.checkPanicExists();
		super.signOut();

		super.signIn("patron2", "patron2");
		super.navigate("/administrator/announcement/create");
		super.checkPanicExists();
		super.signOut();
    }
    
}
