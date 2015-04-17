package edu.bcm.dldcc.big.nursa.model.translationalAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.bcm.dldcc.big.nursa.data.translationalAnnotations.AssociatedMoleculeAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.search.MoleculeResult;

@Entity
public class AssociatedMoleculesAnnotation extends TranslationalBaseAnnotation {

	
	private static final long serialVersionUID = -3421137590085506192L;
	
	@ManyToMany
	@JoinTable(name= "TBA_MOLECULES")
	private List<MoleculeResult> molecules = new ArrayList<MoleculeResult>();
	
	public AssociatedMoleculesAnnotation() {
		setAnnotationType("molecules");
		setLazyDataModelLoaderClass(AssociatedMoleculeAnnotationLazyLoader.class);
	}

	/**
	 * @return the molecules
	 */
	public List<MoleculeResult> getMolecules() {
		return molecules;
	}

	/**
	 * @param molecules the molecules to set
	 */
	public void setMolecules(List<MoleculeResult> molecules) {
		this.molecules = molecules;
	}

	

}
