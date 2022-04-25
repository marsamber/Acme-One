package acme.testing.authenticated.administrator;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorDashboardTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/administrator-dashboard.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(Integer patronagesProposed, Integer patronagesAccepted, Integer patronagesDenied) {
		
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Dashboard");
	
		super.checkFormExists();
		super.checkNotErrorsExist();
		
		
		super.signOut();	
	}
}
