package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitCreateTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/toolkit/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void positiveTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link, final String draftMode) {
			super.signIn("inventor1", "inventor1");

			super.clickOnMenu("Inventor", "List My Toolkits");
			super.checkListingExists();

			super.clickOnButton("Create");
			super.fillInputBoxIn("code", code);
			super.fillInputBoxIn("title", title);
			super.fillInputBoxIn("description", description);
			super.fillInputBoxIn("assemblyNotes", assemblyNotes);
			super.fillInputBoxIn("link", link);
			super.clickOnSubmit("Create");

			super.clickOnMenu("Inventor", "List My Toolkits");
			super.checkListingExists();
			super.sortListing(0, "asc");
			super.checkColumnHasValue(recordIndex, 0, title);
			super.checkColumnHasValue(recordIndex, 1, draftMode);
			super.checkColumnHasValue(recordIndex, 2, code);
			super.clickOnListingRecord(recordIndex);

			super.checkFormExists();
			super.checkInputBoxHasValue("code", code);
			super.checkInputBoxHasValue("title", title);
			super.checkInputBoxHasValue("description", description);
			super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
			super.checkInputBoxHasValue("link", link);

			super.clickOnButton("Components and Tools");

			super.checkListingExists();
			super.checkListingEmpty();

			super.signOut();
		}

		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/toolkit/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void negativeTest(final int recordIndex, final String code, final String title, final String description, final String assemblyNotes, final String link, final String draftMode) {

			super.signIn("inventor1", "inventor1");

			super.clickOnMenu("Inventor", "List My Toolkits");
			super.checkListingExists();

			super.clickOnButton("Create");
			super.fillInputBoxIn("code", code);
			super.fillInputBoxIn("title", title);
			super.fillInputBoxIn("description", description);
			super.fillInputBoxIn("assemblyNotes", assemblyNotes);
			super.fillInputBoxIn("link", link);
			super.clickOnSubmit("Create");

			super.checkErrorsExist();

			super.signOut();
		}

		@Test
		@Order(30)
		public void hackingTest() {
			super.checkNotLinkExists("Account");
			super.navigate("/inventor/toolkit/create");
			super.checkPanicExists();

			super.signIn("administrator", "administrator");
			super.navigate("/inventor/toolkit/create");
			super.checkPanicExists();
			super.signOut();

			super.signIn("patron1", "patron1");
			super.navigate("/inventor/toolkit/create");
			super.checkPanicExists();
			super.signOut();
		}

		// Ancillary methods ------------------------------------------------------


}
