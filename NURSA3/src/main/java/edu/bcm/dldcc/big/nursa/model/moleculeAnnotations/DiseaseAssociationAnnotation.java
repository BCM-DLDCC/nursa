package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.DiseaseAssociationAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.translational.Disease;


@Entity
public class DiseaseAssociationAnnotation extends MoleculeBaseAnnotation {

	
	private static final long serialVersionUID = -2707821612372424600L;
	
	@ManyToMany
	@JoinTable(name= "MBA_DISEASE")
	private List<Disease> diseases = new ArrayList<Disease>();
	
	public DiseaseAssociationAnnotation() {
		setAnnotationType("Disease");
		setLazyDataModelLoaderClass(DiseaseAssociationAnnotationLazyLoader.class);
	}

	/**
	 * @return the diseases
	 */
	public List<Disease> getDiseases() {
		return diseases;
	}

	/**
	 * @param diseases the diseases to set
	 */
	public void setDiseases(List<Disease> diseases) {
		this.diseases = diseases;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((diseases == null) ? 0 : diseases.hashCode());
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
		DiseaseAssociationAnnotation other = (DiseaseAssociationAnnotation) obj;
		if (diseases == null) {
			if (other.diseases != null)
				return false;
		} else if (!diseases.equals(other.diseases))
			return false;
		return true;
	}	
	
}
