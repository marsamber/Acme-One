/*
 * FavouriteLinkTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.any.chirp;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;


public class AnyChirpCreateTest extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String title, final String author,
		final String body, final String email, final String confirmation) {
		

	super.clickOnMenu("Anonymous", "List All Chirps");
	super.checkListingExists();
	super.clickOnButton("Create");
	super.fillInputBoxIn("title",title);
	super.fillInputBoxIn("author",author);
	super.fillInputBoxIn("body",body);
	super.fillInputBoxIn("email",email);
	super.fillInputBoxIn("confirmation", confirmation);
	super.clickOnSubmit("Create");
	
	super.clickOnMenu("Anonymous", "List All Chirps");
	super.checkListingExists();
	super.sortListing(1, "asc");
	super.checkColumnHasValue(recordIndex, 1, title);
	super.checkColumnHasValue(recordIndex, 2, author);
	super.checkColumnHasValue(recordIndex, 3, body);
	super.checkColumnHasValue(recordIndex, 4, email);
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/chirp/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String title, final String author,
		final String body, final String email, final String confirmation) {
		
		super.clickOnMenu("Anonymous", "List All Chirps");
		super.checkListingExists();
		super.clickOnButton("Create");
		super.fillInputBoxIn("title",title);
		super.fillInputBoxIn("author",author);
		super.fillInputBoxIn("body",body);
		super.fillInputBoxIn("email",email);
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
	}

}

