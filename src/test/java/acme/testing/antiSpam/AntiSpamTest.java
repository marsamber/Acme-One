package acme.testing.antiSpam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AntiSpamTest extends TestHarness{
	
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------



		@ParameterizedTest
		@CsvFileSource(resources = "/inventor/item/negative-spam.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void negativeTest(final int recordIndex, final String name, final String code, final String technology, final String description, final String retailPrice, final String link, final String type) {

			super.signIn("inventor1", "inventor1");

			super.clickOnMenu("Inventor", "List My Tools");
			super.checkListingExists();

			super.clickOnButton("Create");
			super.fillInputBoxIn("name", name);
			super.fillInputBoxIn("code", code);
			super.fillInputBoxIn("technology", technology);
			super.fillInputBoxIn("description", description);
			super.fillInputBoxIn("retailPrice", retailPrice);
			super.fillInputBoxIn("link", link);
			super.fillInputBoxIn("type", type);
			super.clickOnSubmit("Create");

			super.checkErrorsExist();

			super.signOut();
		}

		
}
