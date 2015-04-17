package edu.bcm.dldcc.big.nursa.model.translationalAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.bcm.dldcc.big.nursa.data.translationalAnnotations.AssociatedDrugsAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.translational.Drug;

@Entity
public class AssociatedDrugsAnnotation extends TranslationalBaseAnnotation {

	
	private static final long serialVersionUID = -3421137590085506192L;
	
	@ManyToMany
	@JoinTable(name= "TBA_DRUG")
	private List<Drug> drugs = new ArrayList<Drug>();
	
	public AssociatedDrugsAnnotation() {
		setAnnotationType("drugs");
		setLazyDataModelLoaderClass(AssociatedDrugsAnnotationLazyLoader.class);
	}

	/**
	 * @return the drugs
	 */
	public List<Drug> getDrugs() {
		return drugs;
	}

	/**
	 * @param drugs the drugs to set
	 */
	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}

	
	

}
