package acme.entities.tool;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tool extends Item {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotNull
	@Valid
	protected Money retailPrice;
	
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@ManyToOne
	protected Toolkit toolkit;
}
