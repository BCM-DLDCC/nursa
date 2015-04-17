package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.PTMAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.PolypeptideModification;

@Entity
public class PTMAnnotation extends MoleculeBaseAnnotation {

	private static final long serialVersionUID = -6828232886599416158L;
	
	@OneToMany
	@JoinTable(name= "MBA_POLYPEPTIDEMOD")
	private List<PolypeptideModification> polypetideModifications = new ArrayList<PolypeptideModification>();
	
	public PTMAnnotation() {
		setAnnotationType("Polypeptide Modifications");
		setLazyDataModelLoaderClass(PTMAnnotationLazyLoader.class);
	}

	public List<PolypeptideModification> getPolypetideModifications() {
		return polypetideModifications;
	}

	public void setPolypetideModifications(List<PolypeptideModification> polypetideModifications) {
		this.polypetideModifications = polypetideModifications;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((polypetideModifications == null) ? 0
						: polypetideModifications.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PTMAnnotation other = (PTMAnnotation) obj;
		if (polypetideModifications == null) {
			if (other.polypetideModifications != null)
				return false;
		} else if (!polypetideModifications
				.equals(other.polypetideModifications))
			return false;
		return true;
	}

	
	
}
