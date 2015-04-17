package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.CrystalStructureAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.CrystalStructure;


@Entity
public class CrystalStructureAnnotation extends MoleculeBaseAnnotation {

	
	private static final long serialVersionUID = -5838272604038154615L;
	
	@OneToMany
	@JoinTable(name= "CSA_CS")
	private List<CrystalStructure> crystalStructures = new ArrayList<CrystalStructure>();
	
	public CrystalStructureAnnotation() {
		setAnnotationType("Crystal Structure");
		setLazyDataModelLoaderClass(CrystalStructureAnnotationLazyLoader.class);
	}

	public List<CrystalStructure> getCrystalStructures() {
		return crystalStructures;
	}

	public void setCrystalStructures(List<CrystalStructure> crystalStructures) {
		this.crystalStructures = crystalStructures;
	}
	
}
