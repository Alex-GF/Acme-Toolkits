package acme.entities.configuration;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Configuration extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotBlank
	protected String defaultCurrency;
	
	@Min(0)
	protected double strongSpamThreshold;
	
	@Min(0)
	protected double weakSpamThreshold;
	
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
}
