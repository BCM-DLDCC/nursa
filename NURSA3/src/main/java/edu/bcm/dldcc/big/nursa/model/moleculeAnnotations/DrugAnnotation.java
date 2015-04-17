package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.DrugAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.translational.Drug;


@Entity
public class DrugAnnotation extends MoleculeBaseAnnotation {

	
	private static final long serialVersionUID = -4425219504852316469L;
	
	@ManyToMany
	@JoinTable(name= "MBA_DRUG")
	private List<Drug> drugs = new ArrayList<Drug>();
	
	public DrugAnnotation() {
		setAnnotationType("Drug");
		setLazyDataModelLoaderClass(DrugAnnotationLazyLoader.class);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((drugs == null) ? 0 : drugs.hashCode());
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
		DrugAnnotation other = (DrugAnnotation) obj;
		if (drugs == null) {
			if (other.drugs != null)
				return false;
		} else if (!drugs.equals(other.drugs))
			return false;
		return true;
	}	
	
}
