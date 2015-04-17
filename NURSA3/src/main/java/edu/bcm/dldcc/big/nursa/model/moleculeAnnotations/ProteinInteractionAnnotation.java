package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.PolypeptideInteractionAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.PolypeptideInteraction;

@Entity
public class ProteinInteractionAnnotation extends MoleculeBaseAnnotation {

	private static final long serialVersionUID = -7792736838151709158L;
	@OneToMany
	
	private List<PolypeptideInteraction> polypeptideInteractions = new ArrayList<PolypeptideInteraction>();
	
	public ProteinInteractionAnnotation() {
		setAnnotationType("Polypeptide Interaction");
		setLazyDataModelLoaderClass(PolypeptideInteractionAnnotationLazyLoader.class);
	}

	public List<PolypeptideInteraction> getPolypeptideInteractions() {
		return polypeptideInteractions;
	}

	public void setPolypeptideInteractions(List<PolypeptideInteraction> polypetideInteractions) {
		this.polypeptideInteractions = polypetideInteractions;
	}

	
}
