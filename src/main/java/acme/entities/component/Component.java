package acme.entities.component;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.Item;
import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Component extends Item{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotNull
	@Valid
	protected Money retailPrice;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
}
