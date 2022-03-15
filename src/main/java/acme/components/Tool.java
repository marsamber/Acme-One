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

package acme.components;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tool extends AbstractEntity {

	/*
	 A tool is an artefact that allows to work with components. 
	 The system must store the following data about them: a name 
	 (not blank, shorter than 101 characters), a code (pattern “^[A-Z]{3}-[0-9]{3}(-[A-Z])?$”, unique), 
	 a technology (not blank, shorter than 101 characters), a description (not blank, shorter than 256 characters), 
	 a retail price (zero or positive), and an optional link with further information.
	 * */
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(min=1,max=101)
	protected String name;
	
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;

	@NotBlank
	@Length(max=100)
	protected String technology;
	
	@NotBlank
	@Length(max=255)
	protected String description;
	
	@Min(value=0)
	protected Integer retailPrice;
	@Url
	protected String moreInfo;

	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------

}
