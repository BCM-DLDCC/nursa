package edu.bcm.dldcc.big.nursa.data.translationalAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.translational.Drug;
import edu.bcm.dldcc.big.nursa.model.translationalAnnotations.AssociatedDrugsAnnotation;

public class AssociatedDrugsAnnotationLazyLoader extends
		AnnotationLazyLoader<Drug, AssociatedDrugsAnnotation> {
	private static final long serialVersionUID = -435555714562578517L;

	public AssociatedDrugsAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedDrugsAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	public AssociatedDrugsAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedDrugsAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public AssociatedDrugsAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedDrugsAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public AssociatedDrugsAnnotationLazyLoader(EntityManager objectEntityManager,
			AssociatedDrugsAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}

	@Override
	public List<Drug> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "drugs");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "drugs");
		}
	}
}
