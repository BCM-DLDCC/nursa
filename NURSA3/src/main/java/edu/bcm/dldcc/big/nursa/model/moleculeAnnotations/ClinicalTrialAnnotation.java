package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.ClinicalTrialAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.translational.ClinicalTrial;

@Entity
public class ClinicalTrialAnnotation extends MoleculeBaseAnnotation {

	private static final long serialVersionUID = 7615020954489375198L;

	@ManyToMany
	@JoinTable(name = "MBA_CLINICALTRIAL")
	private List<ClinicalTrial> clinicalTrials = new ArrayList<ClinicalTrial>();

	public ClinicalTrialAnnotation() {
		setAnnotationType("Clinical Trials");
		setLazyDataModelLoaderClass(ClinicalTrialAnnotationLazyLoader.class);
	}

	/**
	 * @return the clinicalTrials
	 */
	public List<ClinicalTrial> getClinicalTrials() {
		return clinicalTrials;
	}

	/**
	 * @param clinicalTrials
	 *            the clinicalTrials to set
	 */
	public void setClinicalTrials(List<ClinicalTrial> clinicalTrials) {
		this.clinicalTrials = clinicalTrials;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((clinicalTrials == null) ? 0 : clinicalTrials.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		ClinicalTrialAnnotation other = (ClinicalTrialAnnotation) obj;
		if (clinicalTrials == null) {
			if (other.clinicalTrials != null)
				return false;
		} else if (!clinicalTrials.equals(other.clinicalTrials))
			return false;
		return true;
	}

}
