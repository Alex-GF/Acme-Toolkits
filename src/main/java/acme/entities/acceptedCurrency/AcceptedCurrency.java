package acme.entities.acceptedCurrency;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.configuration.Configuration;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AcceptedCurrency extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotBlank
	protected String currency;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@ManyToOne(optional=false)
	@NotNull
	@Valid
	protected Configuration configuration;
}
