package acme.entities.currencyExchange;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CurrencyExchange extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
		
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}$")
	protected String sourceCurrency;
	
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}$")
	protected String targetCurrency;
	
	@NotNull
	@PastOrPresent
	@Temporal(TemporalType.TIMESTAMP)
	protected Date date;
	
	@Positive
	protected double rate;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	
}
