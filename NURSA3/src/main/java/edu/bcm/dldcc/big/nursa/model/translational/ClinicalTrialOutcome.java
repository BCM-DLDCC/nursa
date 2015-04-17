package edu.bcm.dldcc.big.nursa.model.translational;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class ClinicalTrialOutcome  implements Serializable {

	private static final long serialVersionUID = 2724356410016907088L;
	
	@Id
	@GeneratedValue(generator = "ctOutcomeSequencer")
	@SequenceGenerator(name = "ctOutcomeSequencer", sequenceName = "CTOUTCOME_SEQ")
	private Long id;
	
	private String outcomeType;
	private String outcomeTitle;
	private String timeFrame;
	private Boolean safetyissue;
	private String description;
	private String population;
	private String postingDate;
	
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
	 * @return the outcomeType
	 */
	public String getOutcomeType() {
		return outcomeType;
	}
	/**
	 * @param outcomeType the outcomeType to set
	 */
	public void setOutcomeType(String outcomeType) {
		this.outcomeType = outcomeType;
	}
	/**
	 * @return the outcomeTitle
	 */
	public String getOutcomeTitle() {
		return outcomeTitle;
	}
	/**
	 * @param outcomeTitle the outcomeTitle to set
	 */
	public void setOutcomeTitle(String outcomeTitle) {
		this.outcomeTitle = outcomeTitle;
	}
	/**
	 * @return the timeFrame
	 */
	public String getTimeFrame() {
		return timeFrame;
	}
	/**
	 * @param timeFrame the timeFrame to set
	 */
	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}
	/**
	 * @return the safetyissue
	 */
	public Boolean getSafetyissue() {
		return safetyissue;
	}
	/**
	 * @param safetyissue the safetyissue to set
	 */
	public void setSafetyissue(Boolean safetyissue) {
		this.safetyissue = safetyissue;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the population
	 */
	public String getPopulation() {
		return population;
	}
	/**
	 * @param population the population to set
	 */
	public void setPopulation(String population) {
		this.population = population;
	}
	/**
	 * @return the postingDate
	 */
	public String getPostingDate() {
		return postingDate;
	}
	/**
	 * @param postingDate the postingDate to set
	 */
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((outcomeTitle == null) ? 0 : outcomeTitle.hashCode());
		result = prime * result
				+ ((outcomeType == null) ? 0 : outcomeType.hashCode());
		result = prime * result
				+ ((population == null) ? 0 : population.hashCode());
		result = prime * result
				+ ((postingDate == null) ? 0 : postingDate.hashCode());
		result = prime * result
				+ ((safetyissue == null) ? 0 : safetyissue.hashCode());
		result = prime * result
				+ ((timeFrame == null) ? 0 : timeFrame.hashCode());
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
		ClinicalTrialOutcome other = (ClinicalTrialOutcome) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (outcomeTitle == null) {
			if (other.outcomeTitle != null)
				return false;
		} else if (!outcomeTitle.equals(other.outcomeTitle))
			return false;
		if (outcomeType == null) {
			if (other.outcomeType != null)
				return false;
		} else if (!outcomeType.equals(other.outcomeType))
			return false;
		if (population == null) {
			if (other.population != null)
				return false;
		} else if (!population.equals(other.population))
			return false;
		if (postingDate == null) {
			if (other.postingDate != null)
				return false;
		} else if (!postingDate.equals(other.postingDate))
			return false;
		if (safetyissue == null) {
			if (other.safetyissue != null)
				return false;
		} else if (!safetyissue.equals(other.safetyissue))
			return false;
		if (timeFrame == null) {
			if (other.timeFrame != null)
				return false;
		} else if (!timeFrame.equals(other.timeFrame))
			return false;
		return true;
	}

}
