package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.datatypes.Period;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter 
@Table(name = "patronage")
public class Patronage extends AbstractEntity{
	
	protected static final long	serialVersionUID	= 1L;
	
	
	protected Status status;
	
	
	@Column(unique=true)
	@Pattern(regexp ="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;
	
	
	@NotBlank
	@Length(min = 1,max = 256)
	protected String legalStuff;
	
	@Positive
	protected Double budget;
	
	
	protected Period periodOfTime;
	
	
	@URL
	protected String link;


	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationMoment;
	
	
	@PrePersist
	public void prePersis() {
		//this.creationMoment =LocalDate.now(); 
	}
}
