package edu.bcm.dldcc.big.nursa.model.ominer;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Tissue implements Serializable {

	private static final long serialVersionUID = -882849054286626732L;

	@Id
	@GeneratedValue(generator = "tissueSequencer")
	@SequenceGenerator(name = "tissueSequencer", sequenceName = "TISSUE_SEQ")
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
