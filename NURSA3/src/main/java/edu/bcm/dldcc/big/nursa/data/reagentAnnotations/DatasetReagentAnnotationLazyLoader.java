package edu.bcm.dldcc.big.nursa.data.reagentAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.dataset.NURSADataset;
import edu.bcm.dldcc.big.nursa.model.reagentAnnotations.DatasetReagentAnnotation;


public class DatasetReagentAnnotationLazyLoader extends
AnnotationLazyLoader<NURSADataset, DatasetReagentAnnotation> {

	private static final long serialVersionUID = 6326526082696725102L;

	public DatasetReagentAnnotationLazyLoader(EntityManager objectEntityManager,
			DatasetReagentAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public DatasetReagentAnnotationLazyLoader(EntityManager objectEntityManager,
			DatasetReagentAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public DatasetReagentAnnotationLazyLoader(EntityManager objectEntityManager,
			DatasetReagentAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public DatasetReagentAnnotationLazyLoader(EntityManager objectEntityManager,
			DatasetReagentAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}

	@Override
	public List<NURSADataset> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "datasets");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "datasets");
		}
	}
}
