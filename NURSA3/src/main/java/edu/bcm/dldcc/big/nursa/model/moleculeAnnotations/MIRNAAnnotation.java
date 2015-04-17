package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.MiRNAAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.MiRNA;

@Entity
public class MIRNAAnnotation extends MoleculeBaseAnnotation {

	
	private static final long serialVersionUID = -5838272604038154615L;
	
	@OneToMany
	private List<MiRNA> mirna = new ArrayList<MiRNA>();
	
	public MIRNAAnnotation() {
		setAnnotationType("miRNA");
		setLazyDataModelLoaderClass(MiRNAAnnotationLazyLoader.class);
	}

	public List<MiRNA> getMirna() {
		return mirna;
	}

	public void setMirna(List<MiRNA> mirna) {
		this.mirna = mirna;
	}
	
}
