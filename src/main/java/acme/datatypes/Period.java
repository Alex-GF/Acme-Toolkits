package acme.datatypes;

import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.AbstractDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Period extends AbstractDatatype {
	
	protected static final long	serialVersionUID	= 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date start;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date finish;
	
	

}
