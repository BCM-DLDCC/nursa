package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.LigandAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.Ligand;


@Entity
public class LigandAnnotation extends MoleculeBaseAnnotation {

	private static final long serialVersionUID = -3372325327028113138L;
	
	@ManyToMany
	@JoinTable(name= "MBA_LIGAND")
	private List<Ligand> ligands = new ArrayList<Ligand>();
	
	public LigandAnnotation() {
		setAnnotationType("Ligands");
		setLazyDataModelLoaderClass(LigandAnnotationLazyLoader.class);
	}

	/**
	 * @return the ligands
	 */
	public List<Ligand> getLigands() {
		return ligands;
	}

	/**
	 * @param ligands the ligands to set
	 */
	public void setLigands(List<Ligand> ligands) {
		this.ligands = ligands;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ligands == null) ? 0 : ligands.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LigandAnnotation other = (LigandAnnotation) obj;
		if (ligands == null) {
			if (other.ligands != null)
				return false;
		} else if (!ligands.equals(other.ligands))
			return false;
		return true;
	}	
	
	
	
}
