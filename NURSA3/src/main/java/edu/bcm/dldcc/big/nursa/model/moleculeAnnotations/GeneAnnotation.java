package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.GeneAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.Gene;

@Entity
public class GeneAnnotation extends MoleculeBaseAnnotation {

	private static final long serialVersionUID = -9172260565322385939L;
	
	@OneToMany
	@Column(updatable=false) 
	private List<Gene> genes = new ArrayList<Gene>();
	
	public GeneAnnotation() {
		setAnnotationType("Gene");
		setLazyDataModelLoaderClass(GeneAnnotationLazyLoader.class);
	}

	public List<Gene> getGenes() {
		return genes;
	}

	public void setGenes(List<Gene> genes) {
		this.genes = genes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((genes == null) ? 0 : genes.hashCode());
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
		GeneAnnotation other = (GeneAnnotation) obj;
		if (genes == null) {
			if (other.genes != null)
				return false;
		} else if (!genes.equals(other.genes))
			return false;
		return true;
	}

	
}
