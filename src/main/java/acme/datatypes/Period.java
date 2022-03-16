package acme.datatypes;

import java.time.LocalDate;

import javax.persistence.Embeddable;

import acme.framework.datatypes.AbstractDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Period extends  AbstractDatatype {
	
	protected static final long	serialVersionUID	= 1L;

	
	
	protected LocalDate start;
	
	
	protected LocalDate finish;
	
	

}
