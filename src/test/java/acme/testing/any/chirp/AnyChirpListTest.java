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

public class AnyChirpListTest extends TestHarness{
	

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest	
	@CsvFileSource(resources = "/any/chirp/list-chirp.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestAnonyChirp(final int recordIndex, final String creationMoment, final String title, final String author, final String body, final String email) {

		super.clickOnMenu("Anonymous", "List Chirps");
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);
		
	}
	@ParameterizedTest	
	@CsvFileSource(resources = "/any/chirp/list-chirp.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveTestAdministratorChirp(final int recordIndex, final String creationMoment, final String title, final String author, final String body, final String email) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "List Chirps");
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);
		super.signOut();
	}
	@ParameterizedTest	
	@CsvFileSource(resources = "/any/chirp/list-chirp.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void positiveTestAnyChirp(final int recordIndex, final String creationMoment, final String title, final String author, final String body, final String email) {

		super.signIn("administrator", "administrator");
		super.clickOnMenu("Any", "List Chirps");
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);
		super.signOut();
	}
	
	
	@ParameterizedTest	
	@CsvFileSource(resources = "/any/chirp/list-chirp.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(40)
	public void positiveTestInventorChirp(final int recordIndex, final String creationMoment, final String title, final String author, final String body, final String email) {

		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List Chirps");
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);
		super.signOut();
	}
	

	@ParameterizedTest	
	@CsvFileSource(resources = "/any/chirp/list-chirp.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(50)
	public void positiveTestpatronChirp(final int recordIndex, final String creationMoment, final String title, final String author, final String body, final String email) {

		super.signIn("patron1", "patron1");
		super.clickOnMenu("Patron", "List Chirps");
		super.checkColumnHasValue(recordIndex, 0, creationMoment);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, author);
		super.checkColumnHasValue(recordIndex, 3, body);
		super.checkColumnHasValue(recordIndex, 4, email);
		super.signOut();
	}
} 
	


	// Ancillary methods ------------------------------------------------------

