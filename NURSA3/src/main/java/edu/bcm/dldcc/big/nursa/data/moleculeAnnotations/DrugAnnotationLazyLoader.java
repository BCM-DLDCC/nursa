package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.DrugAnnotation;
import edu.bcm.dldcc.big.nursa.model.translational.Drug;


public class DrugAnnotationLazyLoader extends
AnnotationLazyLoader<Drug, DrugAnnotation> {

	private static final long serialVersionUID = 4917757780611540007L;

	public DrugAnnotationLazyLoader(EntityManager objectEntityManager,
			DrugAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public DrugAnnotationLazyLoader(EntityManager objectEntityManager,
			DrugAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public DrugAnnotationLazyLoader(EntityManager objectEntityManager,
			DrugAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public DrugAnnotationLazyLoader(EntityManager objectEntityManager,
			DrugAnnotation annotation, Map<String, String> filters, String distinctColumn) {
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
