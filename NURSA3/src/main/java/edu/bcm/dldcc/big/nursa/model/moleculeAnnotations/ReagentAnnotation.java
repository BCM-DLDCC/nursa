package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.ReagentAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.reagents.Reagent;


@Entity
public class ReagentAnnotation extends MoleculeBaseAnnotation {

	
	private static final long serialVersionUID = -8642945191446472196L;
	
	@ManyToMany
	@JoinTable(name= "MBA_REAGENT")
	private List<Reagent> reagents = new ArrayList<Reagent>();
	
	public ReagentAnnotation() {
		setAnnotationType("Reagents");
		setLazyDataModelLoaderClass(ReagentAnnotationLazyLoader.class);
	}

	/**
	 * @return the reagents
	 */
	public List<Reagent> getReagents() {
		return reagents;
	}

	/**
	 * @param reagents the reagents to set
	 */
	public void setReagents(List<Reagent> reagents) {
		this.reagents = reagents;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((reagents == null) ? 0 : reagents.hashCode());
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
		ReagentAnnotation other = (ReagentAnnotation) obj;
		if (reagents == null) {
			if (other.reagents != null)
				return false;
		} else if (!reagents.equals(other.reagents))
			return false;
		return true;
	}	
	
	
}
