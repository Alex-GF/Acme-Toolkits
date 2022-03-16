package acme.entities.spamWord;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.entities.configuration.Configuration;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpamWord extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotNull
	protected SpamType type;
	
	@NotBlank
	protected String word;
	
	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------
	
	@ManyToOne(optional=false)
	@NotNull
	protected Configuration configuration;
}
