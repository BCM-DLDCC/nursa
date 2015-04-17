package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.ComplexAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.Complex;


@Entity
public class ComplexAnnotation extends MoleculeBaseAnnotation {

	
	private static final long serialVersionUID = -5838272604038154615L;
	
	@OneToMany
	private List<Complex> complexes = new ArrayList<Complex>();
	
	public ComplexAnnotation() {
		setAnnotationType("Complex");
		setLazyDataModelLoaderClass(ComplexAnnotationLazyLoader.class);
	}

	public List<Complex> getComplexes() {
		return complexes;
	}

	public void setComplexes(List<Complex> complexes) {
		this.complexes = complexes;
	}
	
}
