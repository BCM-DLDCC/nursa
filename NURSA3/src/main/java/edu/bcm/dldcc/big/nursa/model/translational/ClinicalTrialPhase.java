package edu.bcm.dldcc.big.nursa.model.translational;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class ClinicalTrialPhase implements Serializable {

	private static final long serialVersionUID = 6313706567170504938L;
	
	@Id
	@GeneratedValue(generator = "ctPhaseSequencer")
	@SequenceGenerator(name = "ctPhaseSequencer", sequenceName = "CTPHASE_SEQ")
	private Long id;

	private String name;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
