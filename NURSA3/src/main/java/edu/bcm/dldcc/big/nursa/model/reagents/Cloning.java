package edu.bcm.dldcc.big.nursa.model.reagents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Cloning implements Serializable {

	private static final long serialVersionUID = -8907225889406845277L;
	
	@Id
	@GeneratedValue(generator = "cloningSequencer")
	@SequenceGenerator(name = "cloningSequencer", sequenceName = "CLONING_SEQ")
	private Long id;
	
	private String backbone;
	private String backboneMutation;
	private String backboneOrigin;
	private String backboneSize;
	
	private String promoter;
	private String sequencingPrimer3;
	private String sequencingPrimer5;
	
	private String cloneMethod;
	private String cloneSite3;
	private String cloneSite5;
	
	private Boolean site3Destroyed;
	private Boolean site5Destroyed;
	
	@ElementCollection
	private List<String> vectorTypes = new ArrayList<String>();

	

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
	 * @return the backbone
	 */
	public String getBackbone() {
		return backbone;
	}

	/**
	 * @param backbone the backbone to set
	 */
	public void setBackbone(String backbone) {
		this.backbone = backbone;
	}

	/**
	 * @return the backboneMutation
	 */
	public String getBackboneMutation() {
		return backboneMutation;
	}

	/**
	 * @param backboneMutation the backboneMutation to set
	 */
	public void setBackboneMutation(String backboneMutation) {
		this.backboneMutation = backboneMutation;
	}

	/**
	 * @return the backboneOrigin
	 */
	public String getBackboneOrigin() {
		return backboneOrigin;
	}

	/**
	 * @param backboneOrigin the backboneOrigin to set
	 */
	public void setBackboneOrigin(String backboneOrigin) {
		this.backboneOrigin = backboneOrigin;
	}

	/**
	 * @return the backboneSize
	 */
	public String getBackboneSize() {
		return backboneSize;
	}

	/**
	 * @param backboneSize the backboneSize to set
	 */
	public void setBackboneSize(String backboneSize) {
		this.backboneSize = backboneSize;
	}

	/**
	 * @return the promoter
	 */
	public String getPromoter() {
		return promoter;
	}

	/**
	 * @param promoter the promoter to set
	 */
	public void setPromoter(String promoter) {
		this.promoter = promoter;
	}

	/**
	 * @return the sequencingPrimer3
	 */
	public String getSequencingPrimer3() {
		return sequencingPrimer3;
	}

	/**
	 * @param sequencingPrimer3 the sequencingPrimer3 to set
	 */
	public void setSequencingPrimer3(String sequencingPrimer3) {
		this.sequencingPrimer3 = sequencingPrimer3;
	}

	/**
	 * @return the sequencingPrimer5
	 */
	public String getSequencingPrimer5() {
		return sequencingPrimer5;
	}

	/**
	 * @param sequencingPrimer5 the sequencingPrimer5 to set
	 */
	public void setSequencingPrimer5(String sequencingPrimer5) {
		this.sequencingPrimer5 = sequencingPrimer5;
	}

	/**
	 * @return the cloneMethod
	 */
	public String getCloneMethod() {
		return cloneMethod;
	}

	/**
	 * @param cloneMethod the cloneMethod to set
	 */
	public void setCloneMethod(String cloneMethod) {
		this.cloneMethod = cloneMethod;
	}

	/**
	 * @return the cloneSite3
	 */
	public String getCloneSite3() {
		return cloneSite3;
	}

	/**
	 * @param cloneSite3 the cloneSite3 to set
	 */
	public void setCloneSite3(String cloneSite3) {
		this.cloneSite3 = cloneSite3;
	}

	/**
	 * @return the cloneSite5
	 */
	public String getCloneSite5() {
		return cloneSite5;
	}

	/**
	 * @param cloneSite5 the cloneSite5 to set
	 */
	public void setCloneSite5(String cloneSite5) {
		this.cloneSite5 = cloneSite5;
	}

	/**
	 * @return the site3Destroyed
	 */
	public Boolean getSite3Destroyed() {
		return site3Destroyed;
	}

	/**
	 * @param site3Destroyed the site3Destroyed to set
	 */
	public void setSite3Destroyed(Boolean site3Destroyed) {
		this.site3Destroyed = site3Destroyed;
	}

	/**
	 * @return the site5Destroyed
	 */
	public Boolean getSite5Destroyed() {
		return site5Destroyed;
	}

	/**
	 * @param site5Destroyed the site5Destroyed to set
	 */
	public void setSite5Destroyed(Boolean site5Destroyed) {
		this.site5Destroyed = site5Destroyed;
	}

	/**
	 * @return the vectorTypes
	 */
	public List<String> getVectorTypes() {
		return vectorTypes;
	}

	/**
	 * @param vectorTypes the vectorTypes to set
	 */
	public void setVectorTypes(List<String> vectorTypes) {
		this.vectorTypes = vectorTypes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((backbone == null) ? 0 : backbone.hashCode());
		result = prime
				* result
				+ ((backboneMutation == null) ? 0 : backboneMutation.hashCode());
		result = prime * result
				+ ((backboneOrigin == null) ? 0 : backboneOrigin.hashCode());
		result = prime * result
				+ ((backboneSize == null) ? 0 : backboneSize.hashCode());
		result = prime * result
				+ ((cloneMethod == null) ? 0 : cloneMethod.hashCode());
		result = prime * result
				+ ((cloneSite3 == null) ? 0 : cloneSite3.hashCode());
		result = prime * result
				+ ((cloneSite5 == null) ? 0 : cloneSite5.hashCode());
		result = prime * result
				+ ((promoter == null) ? 0 : promoter.hashCode());
		result = prime
				* result
				+ ((sequencingPrimer3 == null) ? 0 : sequencingPrimer3
						.hashCode());
		result = prime
				* result
				+ ((sequencingPrimer5 == null) ? 0 : sequencingPrimer5
						.hashCode());
		result = prime * result
				+ ((site3Destroyed == null) ? 0 : site3Destroyed.hashCode());
		result = prime * result
				+ ((site5Destroyed == null) ? 0 : site5Destroyed.hashCode());
		result = prime * result
				+ ((vectorTypes == null) ? 0 : vectorTypes.hashCode());
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
		Cloning other = (Cloning) obj;
		if (backbone == null) {
			if (other.backbone != null)
				return false;
		} else if (!backbone.equals(other.backbone))
			return false;
		if (backboneMutation == null) {
			if (other.backboneMutation != null)
				return false;
		} else if (!backboneMutation.equals(other.backboneMutation))
			return false;
		if (backboneOrigin == null) {
			if (other.backboneOrigin != null)
				return false;
		} else if (!backboneOrigin.equals(other.backboneOrigin))
			return false;
		if (backboneSize == null) {
			if (other.backboneSize != null)
				return false;
		} else if (!backboneSize.equals(other.backboneSize))
			return false;
		if (cloneMethod == null) {
			if (other.cloneMethod != null)
				return false;
		} else if (!cloneMethod.equals(other.cloneMethod))
			return false;
		if (cloneSite3 == null) {
			if (other.cloneSite3 != null)
				return false;
		} else if (!cloneSite3.equals(other.cloneSite3))
			return false;
		if (cloneSite5 == null) {
			if (other.cloneSite5 != null)
				return false;
		} else if (!cloneSite5.equals(other.cloneSite5))
			return false;
		if (promoter == null) {
			if (other.promoter != null)
				return false;
		} else if (!promoter.equals(other.promoter))
			return false;
		if (sequencingPrimer3 == null) {
			if (other.sequencingPrimer3 != null)
				return false;
		} else if (!sequencingPrimer3.equals(other.sequencingPrimer3))
			return false;
		if (sequencingPrimer5 == null) {
			if (other.sequencingPrimer5 != null)
				return false;
		} else if (!sequencingPrimer5.equals(other.sequencingPrimer5))
			return false;
		if (site3Destroyed == null) {
			if (other.site3Destroyed != null)
				return false;
		} else if (!site3Destroyed.equals(other.site3Destroyed))
			return false;
		if (site5Destroyed == null) {
			if (other.site5Destroyed != null)
				return false;
		} else if (!site5Destroyed.equals(other.site5Destroyed))
			return false;
		if (vectorTypes == null) {
			if (other.vectorTypes != null)
				return false;
		} else if (!vectorTypes.equals(other.vectorTypes))
			return false;
		return true;
	}
	
	
}
