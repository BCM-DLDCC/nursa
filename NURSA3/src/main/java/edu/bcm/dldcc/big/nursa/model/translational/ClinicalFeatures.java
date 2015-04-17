package edu.bcm.dldcc.big.nursa.model.translational;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class ClinicalFeatures implements Serializable {

	private static final long serialVersionUID = -4427024614208919163L;

	@Id
	@GeneratedValue(generator = "clinicalFeaturesSequencer")
	@SequenceGenerator(name = "clinicalFeaturesSequencer", sequenceName = "CLINICALFEATURES_SEQ")
	private Long id;
	
	@ManyToMany
	@JoinTable(name="CF_CFS")
	private List<ClinicalFeature> clinicalFeatures = new ArrayList<ClinicalFeature>();

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
	 * @return the clinicalFeatures
	 */
	public List<ClinicalFeature> getClinicalFeatures() {
		return clinicalFeatures;
	}

	/**
	 * @param clinicalFeatures the clinicalFeatures to set
	 */
	public void setClinicalFeatures(List<ClinicalFeature> clinicalFeatures) {
		this.clinicalFeatures = clinicalFeatures;
	}
	
	
	
}
