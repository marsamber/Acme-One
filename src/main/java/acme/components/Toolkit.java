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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit {

	/*
	 A toolkit is a bundle with components and tools that are expected to work as a whole.
	  The system must store the following data about them: a code (pattern “^[A-Z]{3}-[0-9]{3}(-[A-Z])?$”, unique),
      title (not blank, shorter than 101 characters), description (not blank, shorter than 256 characters),
	  assembly notes (not blank, shorter than 256 characters), and an optional link with further information.
	  A toolkit may have several instances of the same component, but only one instance of a given tool.
	 * */
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Id
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;

	@NotBlank
	@Length(min=1,max=101)
	protected String title;
	
	@NotBlank
	@Length(min=1,max=256)
	protected String description;
	
	@NotBlank
	@Length(min=1,max=256)
	protected String assemblyNotes;
	
	protected String moreInfo;

	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------

	@ManyToOne
    @JoinColumn(name="code")
	protected Tool tool;
}
