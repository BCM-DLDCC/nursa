package edu.bcm.dldcc.big.nursa.model.moleculeAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import edu.bcm.dldcc.big.nursa.data.moleculeAnnotations.MRNAAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.core.MRNA;

@Entity
public class MRNAAnnotation extends MoleculeBaseAnnotation {

	
	private static final long serialVersionUID = -5838272604038154615L;
	
	@OneToMany
	@Column(insertable=false, updatable=false)
	private List<MRNA> mrna = new ArrayList<MRNA>();
	
	public MRNAAnnotation() {
		setAnnotationType("mRNA");
		setLazyDataModelLoaderClass(MRNAAnnotationLazyLoader.class);
	}

	public List<MRNA> getMrna() {
		return mrna;
	}

	public void setMrna(List<MRNA> mrna) {
		this.mrna = mrna;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mrna == null) ? 0 : mrna.hashCode());
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
		MRNAAnnotation other = (MRNAAnnotation) obj;
		if (mrna == null) {
			if (other.mrna != null)
				return false;
		} else if (!mrna.equals(other.mrna))
			return false;
		return true;
	}

	
}
