package edu.bcm.dldcc.big.nursa.model.translational;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Official implements Serializable {

	private static final long serialVersionUID = -8094954058522032152L;
	
	@Id
	@GeneratedValue(generator = "officialSequencer")
	@SequenceGenerator(name = "officialSequencer", sequenceName = "OFFICIAL_SEQ")
	private Long id;
	
	private String name;
	private String role;
	private Location affiliation;
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
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the affiliation
	 */
	public Location getAffiliation() {
		return affiliation;
	}
	/**
	 * @param affiliation the affiliation to set
	 */
	public void setAffiliation(Location affiliation) {
		this.affiliation = affiliation;
	}

	
}
