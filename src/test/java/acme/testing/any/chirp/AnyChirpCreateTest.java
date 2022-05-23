package acme.testing.any.chirp;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class AnyChirpCreateTest extends TestHarness{
	
	@ParameterizedTest
    @CsvFileSource(resources = "/any/chirp/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void positive(final int recordIndex, final String title, final String body,
            final String author, final String email) {
        

        super.clickOnMenu("Anonymous", "List the chirps");
        super.clickOnButton("Create");
        
        //final LocalDateTime now =LocalDateTime.now();
        //final String date = now.getYear()+"/"+(now.getMonthValue() < 10 ? "0"+ now.getMonthValue():now.getMonthValue())+"/"+now.getDayOfMonth()+" "+now.getHour()+":"+now.getMinute();
        super.fillInputBoxIn("title", title);
        super.fillInputBoxIn("body", body);
        super.fillInputBoxIn("author", author);
        super.fillInputBoxIn("email", email);
        super.fillInputBoxIn("confirmation", "true");
        super.clickOnSubmit("Create");
        
        super.clickOnMenu("Anonymous", "List the chirps");
        super.checkListingExists();
        super.sortListing(1, "asc");
        //super.checkColumnHasValue(recordIndex, 0, date);
        super.checkColumnHasValue(recordIndex, 1, title);
        super.checkColumnHasValue(recordIndex, 2, author);
        super.checkColumnHasValue(recordIndex, 3, body);
        super.checkColumnHasValue(recordIndex, 4, email);

        
    }
	
	@ParameterizedTest
    @CsvFileSource(resources = "/any/chirp/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(20)
    public void negative(final int recordIndex, final String title, final String body,
            final String author, final String email) {
        

        super.clickOnMenu("Anonymous", "List the chirps");
        super.clickOnButton("Create");
        
        super.fillInputBoxIn("title", title);
        super.fillInputBoxIn("body", body);
        super.fillInputBoxIn("author", author);
        super.fillInputBoxIn("email", email);
        super.fillInputBoxIn("confirmation", "true");
        super.clickOnSubmit("Create");
        
        super.checkErrorsExist();
 
        
    }
	

    @Test
    @Order(30)
    public void hackingTest() {
    	// Esto no se puede hackear puesto que los usuarios no registrados y registrados 
    	// pueden crear chirps y solo hay un list.

    }



}
