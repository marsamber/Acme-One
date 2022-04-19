
package acme.testing.any.patron;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyPatronListAllTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/any/patron/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String name, final String surname, final String email, final String username, final String company, final String statement, final String moreInfo) {
		
		super.signIn("inventor1", "inventor1");

		super.clickOnMenu("Any", "List All Patrons");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, surname);
		super.checkColumnHasValue(recordIndex, 2, email);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("userAccount.identity.name", name);
		super.checkInputBoxHasValue("userAccount.identity.surname", surname);
		super.checkInputBoxHasValue("userAccount.identity.email", email);
		super.checkInputBoxHasValue("userAccount.username", username);
		super.checkInputBoxHasValue("company", company);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("moreInfo", moreInfo);
		
		super.signOut();
		
	}
}
