package edu.bcm.dldcc.big.nursa.model.translationalAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.bcm.dldcc.big.nursa.data.translationalAnnotations.AssociatedDiseaseAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.translational.Disease;

@Entity
public class AssociatedDiseaseAnnotation extends TranslationalBaseAnnotation {

	private static final long serialVersionUID = 7942032576421817709L;
	@ManyToMany
	@JoinTable(name= "TBA_DISEASES")
	private List<Disease> diseases = new ArrayList<Disease>();
	
	public AssociatedDiseaseAnnotation() {
		setAnnotationType("Disease");
		setLazyDataModelLoaderClass(AssociatedDiseaseAnnotationLazyLoader.class);
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

	
	

}
