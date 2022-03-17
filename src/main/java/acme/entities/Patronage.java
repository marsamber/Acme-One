package acme.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	private enum Status {PROPOSED, ACCEPTED, DENIED};

	
	//Attributes ---------------------------------------------
	
	@NotNull
	protected Status status;

	
	@Column(unique = true)
	@Pattern(regexp ="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;
	
	@NotBlank
	@Length(max = 255)
	protected String legalStuff;
	
	protected Money budget;
	
	@URL
	protected String link;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	protected Date createdAt;
	
	@Temporal(TemporalType.DATE)
	protected Date startedAt;
	
	@Temporal(TemporalType.DATE)
	protected Date finishedAt;
	
	
	//Complex constraints -------------------------------------
	
	@AssertTrue(message="Budget must be positive")
    private boolean isMoneyPositive() {
        return this.budget.getAmount() > 0.;
    }
	
	@AssertTrue(message = "The patronage should start a month after the entity is created.")
	private boolean isValidStartedAt() {
		if(this.startedAt == null) {	return true;	}
		final LocalDateTime created = LocalDateTime.from(this.createdAt.toInstant());
		final LocalDateTime startedMinusOne = LocalDateTime.from(this.startedAt.toInstant()).minusMonths(1);
	    return startedMinusOne.isAfter(created) || startedMinusOne.isEqual(created);
	}
	
	@AssertTrue(message = "The patronage should last at least for a month.")
	private boolean isValidFinishedAt()	{
		final LocalDateTime started = LocalDateTime.from(this.startedAt.toInstant());
		final LocalDateTime finishedMinusOne = LocalDateTime.from(this.finishedAt.toInstant()).minusMonths(1);
		return finishedMinusOne.isAfter(started) || finishedMinusOne.isEqual(started);
	}

}
