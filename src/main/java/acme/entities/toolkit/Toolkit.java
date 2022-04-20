package acme.entities.toolkit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit extends AbstractEntity{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@NotBlank
	protected String code;
	
	@NotBlank
	@Length(min = 1, max = 100)
	protected String title;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String description;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String assemblyNotes;
	
	protected boolean published;
	
	@URL
	protected String link;
	
	// Derived attributes -----------------------------------------------------
	
	@Transient
	protected Money totalPrice;
	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@ManyToOne(optional = false)
	@Valid
	protected Inventor inventor;
}
