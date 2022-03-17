package acme.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;

public class Patronage extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	private enum State {PROPOSED, ACCEPTED, DENIED};
	
	//Attributes ---------------------------------------------
	
	@NotNull
	protected State state;
	
	@Column(unique = true)
	@Pattern(regexp ="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;
	
	@NotBlank
	@Length(max = 255)
	protected String legalStuff;
	
	protected Money budget;
	
	@URL
	protected String link;
	
	protected LocalDate createdAt;
	
	protected LocalDate startedAt;
	
	protected LocalDate finishedAt;
	
	
	//Complex constraints -------------------------------------
	
	@AssertTrue(message="Budget must be positive")
    private boolean isMoneyPositive() {
        return this.budget.getAmount() > 0.;
    }
	
	@AssertTrue(message = "The patronage should start a month after the entity is created.")
	private boolean isValidStartedAt() {
		final LocalDate startedMinusOne = this.startedAt.minusMonths(1L);
	    return startedMinusOne.isAfter(this.createdAt) || startedMinusOne.equals(this.createdAt);
	}
	
	@AssertTrue(message = "The patronage should last at least for a month.")
	private boolean isValidFinishedAt()	{
		final LocalDate finishedMinusOne = this.finishedAt.minusMonths(1L);
		return finishedMinusOne.isAfter(this.startedAt) || finishedMinusOne.equals(this.startedAt);
	}

}
