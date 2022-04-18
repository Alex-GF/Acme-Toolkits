package acme.entities.currencyExchange;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class RateExchange extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
		
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}$")
	protected String currency;
	
	@NotNull
	protected Double rate;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	@NotNull
	@ManyToOne(optional = false)
	@Valid
	protected CurrencyExchange currencyExchange;
	
}
