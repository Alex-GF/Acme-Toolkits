package acme.entities.patronage;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.datatypes.Period;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter 
@Table(name = "patronage")
public class Patronage extends AbstractEntity{
	
	protected static final long	serialVersionUID	= 1L;
	
	@NotNull
	protected Status status;
	
	@Column(unique=true)
	@Pattern(regexp ="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@NotBlank
	protected String code;
	
	@NotBlank
	@Length(min = 1,max = 255)
	protected String legalStuff;
	
	@NotNull
	@Valid
	protected Money budget;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date creationMoment;
	
	@NotNull
	@Valid
	protected Period period;
	
	@URL
	protected String link;
}
