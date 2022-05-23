package acme.testing.inventor.patronageReport;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness {

    @ParameterizedTest
    @CsvFileSource(resources = "/inventor/patronageReport/positive.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(30)
    public void positive(final int recordIndex, final String memorandum, final String creationMoment,
            final String automaticSequenceNumber, final String patronage, final String link) {
        super.signIn("inventor1", "inventor1");

        super.clickOnMenu("Inventor", "Patronage report list");
        super.clickOnSubmit("Create");

        final LocalDateTime now = LocalDateTime.now();
        final String date = now.getYear() + "/"
                + (now.getMonthValue() < 10 ? "0" + now.getMonthValue() : now.getMonthValue()) + "/"
                + now.getDayOfMonth() + " " + now.getHour() + ":"
                + (now.getMinute() < 10 ? "0" + now.getMinute() : now.getMinute());
        super.fillInputBoxIn("memorandum", memorandum);
        super.fillInputBoxIn("link", link);
        super.fillInputBoxIn("automaticSequenceNumber", automaticSequenceNumber);
        super.fillInputBoxIn("patronage", patronage);
        super.fillInputBoxIn("confirmation", "true");
        super.clickOnSubmit("Create");

        super.clickOnMenu("Inventor", "Patronage report list");
        super.checkListingExists();
        super.sortListing(1, "asc");
        super.checkColumnHasValue(recordIndex, 0, date);
        super.checkColumnHasValue(recordIndex, 1, memorandum);
        super.checkColumnHasValue(recordIndex, 2, automaticSequenceNumber);
        super.checkColumnHasValue(recordIndex, 3, patronage);

        super.clickOnListingRecord(recordIndex);

        super.checkFormExists();
        super.checkInputBoxHasValue("memorandum", memorandum);
        super.checkInputBoxHasValue("automaticSequenceNumber", automaticSequenceNumber);
        super.checkInputBoxHasValue("patronage", patronage);
        super.checkInputBoxHasValue("link", link);

        super.signOut();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/inventor/patronageReport/negative.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(20)
    public void negative(final String recordIndex, final String memorandum, final String creationMoment,
            final String automaticSequenceNumber, final String patronage, final String link) {
        super.signIn("inventor1", "inventor1");

        super.clickOnMenu("Inventor", "Patronage report list");
        super.clickOnSubmit("Create");

        final LocalDateTime now = LocalDateTime.now();
        final String date = now.getYear() + "/"
                + (now.getMonthValue() < 10 ? "0" + now.getMonthValue() : now.getMonthValue()) + "/"
                + now.getDayOfMonth() + " " + now.getHour() + ":"
                + (now.getMinute() < 10 ? "0" + now.getMinute() : now.getMinute());
        super.fillInputBoxIn("memorandum", memorandum);
        super.fillInputBoxIn("automaticSequenceNumber", automaticSequenceNumber);
        super.fillInputBoxIn("patronage", patronage);
        super.fillInputBoxIn("link", link);
        super.fillInputBoxIn("confirmation", "true");
        super.clickOnSubmit("Create");

        super.checkErrorsExist();
        super.signOut();
    }

    @Test
    @Order(10)
    public void hackingTest() {

        super.checkNotLinkExists("Account");
        super.navigate("/inventor/patronage-report/list");
        super.checkPanicExists();

        super.checkNotLinkExists("Account");
        super.navigate("/inventor/patronage-report/create");
        super.checkPanicExists();

        super.signIn("patron1", "patron1");
        super.navigate("/inventor/patronage-report/create");
        super.checkPanicExists();
        super.signOut();
    }

}