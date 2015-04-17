package edu.bcm.dldcc.big.nursa.model.translationalAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import edu.bcm.dldcc.big.nursa.data.reagentAnnotations.DatasetReagentAnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.ominer.Dataset;

@Entity
public class DatasetTranslationalAnnotation extends TranslationalBaseAnnotation {

	private static final long serialVersionUID = 6108831859100212365L;
	
	@OneToMany
	private List<Dataset> datasets = new ArrayList<Dataset>();
	
	public DatasetTranslationalAnnotation() {
		setAnnotationType("dataset");
		setLazyDataModelLoaderClass(DatasetReagentAnnotationLazyLoader.class);
	}

	public List<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}

}
