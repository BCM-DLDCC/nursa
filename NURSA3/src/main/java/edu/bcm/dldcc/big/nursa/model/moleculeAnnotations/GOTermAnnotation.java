package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.GoTermAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.GOTerm;

@Entity
public class GOTermAnnotation extends MoleculeBaseAnnotation {

	private static final long serialVersionUID = -5588911282643802012L;
	
	@OneToMany
	@JoinTable(name= "MBA_GOTERM")
	private List<GOTerm> goTerms = new ArrayList<GOTerm>();
	
	public GOTermAnnotation() {
		setAnnotationType("GO Term");
		setLazyDataModelLoaderClass(GoTermAnnotationLazyLoader.class);
	}

	public List<GOTerm> getGoTerms() {
		return goTerms;
	}

	public void setGoTerms(List<GOTerm> goTerms) {
		this.goTerms = goTerms;
	}

	
}
