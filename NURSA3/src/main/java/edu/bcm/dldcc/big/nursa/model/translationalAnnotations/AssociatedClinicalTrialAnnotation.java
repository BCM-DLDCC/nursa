package edu.bcm.dldcc.big.nursa.model.translationalAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.bcm.dldcc.big.nursa.data.translationalAnnotations.AssociatedClinicalTrialAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.translational.ClinicalTrial;

@Entity
@DiscriminatorValue("AssociatedClinicalTrialAnnotati")
public class AssociatedClinicalTrialAnnotation extends TranslationalBaseAnnotation {

	private static final long serialVersionUID = -6529157956264443670L;
	@ManyToMany
	@JoinTable(name= "TBA_CT")
	private List<ClinicalTrial> clinicalTrials = new ArrayList<ClinicalTrial>();
	
	public AssociatedClinicalTrialAnnotation() {
		setAnnotationType("Clinical Trial");
		setLazyDataModelLoaderClass(AssociatedClinicalTrialAnnotationLazyLoader.class);
	}

	/**
	 * @return the clinicalTrials
	 */
	public List<ClinicalTrial> getClinicalTrials() {
		return clinicalTrials;
	}

	/**
	 * @param clinicalTrials the clinicalTrials to set
	 */
	public void setClinicalTrials(List<ClinicalTrial> clinicalTrials) {
		this.clinicalTrials = clinicalTrials;
	}

	
	
	

}
