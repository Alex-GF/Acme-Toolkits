package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "patronage_report")
public class PatronageReport extends AbstractEntity{

	protected static final long	serialVersionUID	= 1L;
	
	
	
	
	
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creationMoment;
	
	
	@NotBlank
	@Length(min = 1, max = 256)
	protected String memorandum;
	
	@URL
	protected String link;
	
	
	/*@NotBlank
	@Pattern(regexp ="")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patronage-code")
	@SequenceGenerator(name = "patronage-code",sequenceName = "patronage.code", allocationSize = 1, initialValue = 0001)
	protected String automaticSequenceNumber;
	*/
	
	
	@OneToOne
	@JoinColumn(referencedColumnName = "code" )
	protected Patronage code;
	
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer serialNumber;
	
	
	@NotBlank
	protected String automaticSequenceNumber;
	
	
	@PrePersist
	public void prePersist() {
		final String sequenceNumber = ""+ this.serialNumber;
		String zeros = "";
		for(int i = sequenceNumber.length(); i <= 4; i++) {
			zeros+="0";
		}
		this.automaticSequenceNumber = this.code.getCode() + ":" + zeros + sequenceNumber;
		//this.creationMoment = LocalDate.now();
	}
	
}
