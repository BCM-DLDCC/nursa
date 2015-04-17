package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.PolypeptideModificationAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.PolypeptideModification;


@Entity
public class PolypeptideModificationAnnotation extends MoleculeBaseAnnotation {

	
	private static final long serialVersionUID = -5838272604038154615L;
	
	@OneToMany
	private List<PolypeptideModification> polypeptideModifications = new ArrayList<PolypeptideModification>();
	
	public PolypeptideModificationAnnotation() {
		setAnnotationType("PolypeptideModification");
		setLazyDataModelLoaderClass(PolypeptideModificationAnnotationLazyLoader.class);
	}

	public List<PolypeptideModification> getPolypeptideModifications() {
		return polypeptideModifications;
	}

	public void setPolypeptideModifications(List<PolypeptideModification> polypeptideModifications) {
		this.polypeptideModifications = polypeptideModifications;
	}


	
}
