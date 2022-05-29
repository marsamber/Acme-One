package acme.entities;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity {

	//Serialisation identifier ------------------------------

	protected static final long serialVersionUID = 1L; 
	
	//Attributes ---------------------------------------------
	
	@NotNull
	protected String systemCurrency;
	
	@NotBlank
	protected String acceptedCurrencies;
	
	@NotBlank
	protected String strongSpamTerms;
	
	@NotNull
	@Min(0)
	@Max(100)
	protected Double strongSpamThreshold;
	
	@NotBlank
	protected String weakSpamTerms;
	
	@NotNull
	@Min(0)
	@Max(100)
	protected Double weakSpamThreshold;
}
