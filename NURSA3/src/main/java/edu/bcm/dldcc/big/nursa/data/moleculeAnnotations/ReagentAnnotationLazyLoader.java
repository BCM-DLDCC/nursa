package edu.bcm.dldcc.big.nursa.data.moleculeAnnotations;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.primefaces.model.SortMeta;

import edu.bcm.dldcc.big.nursa.data.common.AnnotationLazyLoader;
import edu.bcm.dldcc.big.nursa.model.moleculeAnnotations.ReagentAnnotation;
import edu.bcm.dldcc.big.nursa.model.reagents.Reagent;


public class ReagentAnnotationLazyLoader extends
AnnotationLazyLoader<Reagent, ReagentAnnotation> {


	private static final long serialVersionUID = 3116542240884714636L;

	public ReagentAnnotationLazyLoader(EntityManager objectEntityManager,
			ReagentAnnotation annotation) {
		super(objectEntityManager, annotation);
	}
	
	public ReagentAnnotationLazyLoader(EntityManager objectEntityManager,
			ReagentAnnotation annotation, String distinctColumn) {
		super(objectEntityManager, annotation, distinctColumn);
	}
	
	public ReagentAnnotationLazyLoader(EntityManager objectEntityManager,
			ReagentAnnotation annotation, Map<String, String> filters) {
		super(objectEntityManager, annotation, filters);
	}
	
	public ReagentAnnotationLazyLoader(EntityManager objectEntityManager,
			ReagentAnnotation annotation, Map<String, String> filters, String distinctColumn) {
		super(objectEntityManager, annotation, filters, distinctColumn);
	}

	@Override
	public List<Reagent> load(int first, int pageSize,
			List<SortMeta> multiSortMeta, Map<String, String> filters) {
		if(this.getDistinctColumn() == null) {
			return createQuery(first, pageSize, multiSortMeta, filters, "reagents");
		} else {
			return createDistinctColumnQuery(first, pageSize, multiSortMeta, filters, "reagents");
		}
	}
}
