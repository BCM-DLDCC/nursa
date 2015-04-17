package edu.bcm.dldcc.big.nursa.model.translational;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ClinicalTrialSponsor implements Serializable {


	private static final long serialVersionUID = -1036818139509687141L;

	@Id
	@GeneratedValue(generator = "ctSponsorSequencer")
	@SequenceGenerator(name = "ctSponsorSequencer", sequenceName = "CTSPONSOR_SEQ")
	private Long id;
	
	private String agencyName;
	
	@ManyToOne
	private ClinicalTrialSponsorClass agencyClass;
	
	private String sponsorType;
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
	 * @return the agencyName
	 */
	public String getAgencyName() {
		return agencyName;
	}
	/**
	 * @param agencyName the agencyName to set
	 */
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	/**
	 * @return the agencyClass
	 */
	public ClinicalTrialSponsorClass getAgencyClass() {
		return agencyClass;
	}
	/**
	 * @param agencyClass the agencyClass to set
	 */
	public void setAgencyClass(ClinicalTrialSponsorClass agencyClass) {
		this.agencyClass = agencyClass;
	}
	
	/**
	 * @return the sponsorType
	 */
	public String getSponsorType() {
		return sponsorType;
	}
	/**
	 * @param sponsorType the sponsorType to set
	 */
	public void setSponsorType(String sponsorType) {
		this.sponsorType = sponsorType;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((agencyClass == null) ? 0 : agencyClass.hashCode());
		result = prime * result
				+ ((agencyName == null) ? 0 : agencyName.hashCode());
		result = prime * result
				+ ((sponsorType == null) ? 0 : sponsorType.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClinicalTrialSponsor other = (ClinicalTrialSponsor) obj;
		if (agencyClass == null) {
			if (other.agencyClass != null)
				return false;
		} else if (!agencyClass.equals(other.agencyClass))
			return false;
		if (agencyName == null) {
			if (other.agencyName != null)
				return false;
		} else if (!agencyName.equals(other.agencyName))
			return false;
		if (sponsorType == null) {
			if (other.sponsorType != null)
				return false;
		} else if (!sponsorType.equals(other.sponsorType))
			return false;
		return true;
	}
	
	
	
}
