/*
 * Consumer.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import acme.framework.roles.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Inventor extends UserRole {

	/*
	 * The principals may have the following project-specific roles: inventor and/or patron (in addition to the default anonymous, 
	 * authenticated, and administrator roles provided by the develop-ment framework). The project-specific roles must have a profile
	 * with the following data: a company (not blank, shorter than 101 characters), a statement (not blank, shorter than 256 characters),
	 * and an optional link with further information. 
	 * The system must be delivered with an account for an administrator principal 
	 * with credentials “administrator/administrator”.
	 * */
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(max=100)
	protected String company;

	@NotBlank
	@Length(max=255)
	protected String statement;
	
	protected String moreInfo;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
