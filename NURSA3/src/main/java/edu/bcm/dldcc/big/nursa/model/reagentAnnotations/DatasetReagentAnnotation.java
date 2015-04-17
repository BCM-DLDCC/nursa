package edu.bcm.dldcc.big.nursa.model.reagentAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import edu.bcm.dldcc.big.nursa.data.reagentAnnotations.DatasetReagentAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.dataset.NURSADataset;

@Entity
public class DatasetReagentAnnotation extends ReagentBaseAnnotation {

	private static final long serialVersionUID = 6159100883180212365L;
	
	@ManyToMany
	@JoinTable(name = "RBA_DATASET")
	private List<NURSADataset> datasets = new ArrayList<NURSADataset>();
	
	public DatasetReagentAnnotation() {
		setAnnotationType("Dataset");
		setLazyDataModelLoaderClass(DatasetReagentAnnotationLazyLoader.class);
	}
	
	/**
	 * @return the datasets
	 */
	public List<NURSADataset> getDatasets() {
		return datasets;
	}

	/**
	 * @param datasets the datasets to set
	 */
	public void setDatasets(List<NURSADataset> datasets) {
		this.datasets = datasets;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((getDatasets() == null) ? 0 : getDatasets().hashCode());
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
		DatasetReagentAnnotation other = (DatasetReagentAnnotation) obj;
		if (getDatasets() == null) {
			if (other.getDatasets() != null)
				return false;
		} else if (!getDatasets().equals(other.getDatasets()))
			return false;
		return true;
	}
	

}
