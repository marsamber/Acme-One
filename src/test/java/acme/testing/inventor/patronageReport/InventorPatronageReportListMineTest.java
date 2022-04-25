
package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportListMineTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/list-by-inventor.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String seqNumber, final String memorandum, final String link, final String creationMoment) {
		
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Inventor", "List My Patronage Reports");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, seqNumber);
		super.checkColumnHasValue(recordIndex, 1, creationMoment);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("seqNumber", seqNumber);
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		
		super.signOut();
		
	}
}
