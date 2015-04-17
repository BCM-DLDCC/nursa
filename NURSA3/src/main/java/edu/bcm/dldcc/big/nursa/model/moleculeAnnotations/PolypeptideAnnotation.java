package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.PolypeptideAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.Polypeptide;


@Entity
public class PolypeptideAnnotation extends MoleculeBaseAnnotation {

	
	private static final long serialVersionUID = -5838272604038154615L;
	
	@OneToMany
	@JoinTable(name= "PolyAnnot_Poly")
	private List<Polypeptide> polypeptides = new ArrayList<Polypeptide>();
	
	public PolypeptideAnnotation() {
		setAnnotationType("Polypeptide");
		setLazyDataModelLoaderClass(PolypeptideAnnotationLazyLoader.class);
	}

	public List<Polypeptide> getPolypeptides() {
		return polypeptides;
	}

	public void setPolypeptides(List<Polypeptide> polypeptides) {
		this.polypeptides = polypeptides;
	}

	
	
}
