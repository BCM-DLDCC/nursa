package edu.bcm.dldcc.big.nursa.model.reagents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import edu.bcm.dldcc.big.nursa.model.translational.Drug;

@Entity
public class Plasmid extends Reagent {

	private static final long serialVersionUID = -6008440485362135078L;
	
	private Drug bacterialResistance;
	
	private Cloning cloning;
	
	private String growthNotes;
	private String growthStrain;
	private Integer growthTemp;
	
	@OneToMany
	private List<PlasmidInsert> inserts = new ArrayList<PlasmidInsert>();
	
	private String origin;
	private String plasmidCopy;
	
	@OneToMany
	private List<Drug> resistantMarkers;
	
	@ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="plasmidInsert_tags")
	private Map<String, String> tags = new HashMap<String, String>();
	
	@ElementCollection
	private List<String> terms = new ArrayList<String>();
	
	
	public Plasmid() {
		setType("Plasmid");
	}


	/**
	 * @return the bacterialResistance
	 */
	public Drug getBacterialResistance() {
		return bacterialResistance;
	}


	/**
	 * @param bacterialResistance the bacterialResistance to set
	 */
	public void setBacterialResistance(Drug bacterialResistance) {
		this.bacterialResistance = bacterialResistance;
	}


	/**
	 * @return the cloning
	 */
	public Cloning getCloning() {
		return cloning;
	}


	/**
	 * @param cloning the cloning to set
	 */
	public void setCloning(Cloning cloning) {
		this.cloning = cloning;
	}


	/**
	 * @return the growthNotes
	 */
	public String getGrowthNotes() {
		return growthNotes;
	}


	/**
	 * @param growthNotes the growthNotes to set
	 */
	public void setGrowthNotes(String growthNotes) {
		this.growthNotes = growthNotes;
	}


	/**
	 * @return the growthStrain
	 */
	public String getGrowthStrain() {
		return growthStrain;
	}


	/**
	 * @param growthStrain the growthStrain to set
	 */
	public void setGrowthStrain(String growthStrain) {
		this.growthStrain = growthStrain;
	}


	/**
	 * @return the growthTemp
	 */
	public Integer getGrowthTemp() {
		return growthTemp;
	}


	/**
	 * @param growthTemp the growthTemp to set
	 */
	public void setGrowthTemp(Integer growthTemp) {
		this.growthTemp = growthTemp;
	}


	/**
	 * @return the inserts
	 */
	public List<PlasmidInsert> getInserts() {
		return inserts;
	}


	/**
	 * @param inserts the inserts to set
	 */
	public void setInserts(List<PlasmidInsert> inserts) {
		this.inserts = inserts;
	}


	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}


	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}


	/**
	 * @return the plasmidCopy
	 */
	public String getPlasmidCopy() {
		return plasmidCopy;
	}


	/**
	 * @param plasmidCopy the plasmidCopy to set
	 */
	public void setPlasmidCopy(String plasmidCopy) {
		this.plasmidCopy = plasmidCopy;
	}


	/**
	 * @return the resistantMarkers
	 */
	public List<Drug> getResistantMarkers() {
		return resistantMarkers;
	}


	/**
	 * @param resistantMarkers the resistantMarkers to set
	 */
	public void setResistantMarkers(List<Drug> resistantMarkers) {
		this.resistantMarkers = resistantMarkers;
	}


	/**
	 * @return the tags
	 */
	public Map<String, String> getTags() {
		return tags;
	}


	/**
	 * @param tags the tags to set
	 */
	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}


	/**
	 * @return the terms
	 */
	public List<String> getTerms() {
		return terms;
	}


	/**
	 * @param terms the terms to set
	 */
	public void setTerms(List<String> terms) {
		this.terms = terms;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((bacterialResistance == null) ? 0 : bacterialResistance
						.hashCode());
		result = prime * result + ((cloning == null) ? 0 : cloning.hashCode());
		result = prime * result
				+ ((growthNotes == null) ? 0 : growthNotes.hashCode());
		result = prime * result
				+ ((growthStrain == null) ? 0 : growthStrain.hashCode());
		result = prime * result
				+ ((growthTemp == null) ? 0 : growthTemp.hashCode());
		result = prime * result + ((inserts == null) ? 0 : inserts.hashCode());
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
		result = prime * result
				+ ((plasmidCopy == null) ? 0 : plasmidCopy.hashCode());
		result = prime
				* result
				+ ((resistantMarkers == null) ? 0 : resistantMarkers.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + ((terms == null) ? 0 : terms.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plasmid other = (Plasmid) obj;
		if (bacterialResistance == null) {
			if (other.bacterialResistance != null)
				return false;
		} else if (!bacterialResistance.equals(other.bacterialResistance))
			return false;
		if (cloning == null) {
			if (other.cloning != null)
				return false;
		} else if (!cloning.equals(other.cloning))
			return false;
		if (growthNotes == null) {
			if (other.growthNotes != null)
				return false;
		} else if (!growthNotes.equals(other.growthNotes))
			return false;
		if (growthStrain == null) {
			if (other.growthStrain != null)
				return false;
		} else if (!growthStrain.equals(other.growthStrain))
			return false;
		if (growthTemp == null) {
			if (other.growthTemp != null)
				return false;
		} else if (!growthTemp.equals(other.growthTemp))
			return false;
		if (inserts == null) {
			if (other.inserts != null)
				return false;
		} else if (!inserts.equals(other.inserts))
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		if (plasmidCopy == null) {
			if (other.plasmidCopy != null)
				return false;
		} else if (!plasmidCopy.equals(other.plasmidCopy))
			return false;
		if (resistantMarkers == null) {
			if (other.resistantMarkers != null)
				return false;
		} else if (!resistantMarkers.equals(other.resistantMarkers))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (terms == null) {
			if (other.terms != null)
				return false;
		} else if (!terms.equals(other.terms))
			return false;
		return true;
	}

	
}
