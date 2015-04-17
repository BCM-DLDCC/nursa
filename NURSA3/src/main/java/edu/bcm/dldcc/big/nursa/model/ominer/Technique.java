package edu.bcm.dldcc.big.nursa.model.ominer;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Technique implements Serializable {

	private static final long serialVersionUID = -4649682868176265210L;

	@Id
	@GeneratedValue(generator = "techniqueSequencer")
	@SequenceGenerator(name = "techniqueSequencer", sequenceName = "TECHNIQUE_SEQ")
	private Long id;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
