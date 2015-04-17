package edu.bcm.dldcc.big.nursa.model.translational;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class Location implements Serializable {
	private static final long serialVersionUID = -4136879922759256585L;
	
	@Id
	@GeneratedValue(generator = "locationSequencer")
	@SequenceGenerator(name = "locationSequencer", sequenceName = "LOCATION_SEQ")
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
